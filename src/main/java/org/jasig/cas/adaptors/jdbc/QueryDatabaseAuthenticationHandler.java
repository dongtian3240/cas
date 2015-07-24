/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.adaptors.jdbc;

import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.entity.User;
import org.jasig.cas.entity.UserAttempts;
import org.jasig.cas.excption.EmptyPasswordException;
import org.jasig.cas.excption.EmptyUserNameException;
import org.jasig.cas.excption.FailedPasswordException;
import org.jasig.cas.util.DateUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class that if provided a query that returns a password (parameter of query
 * must be username) will compare that password to a translated version of the
 * password provided by the user. If they match, then authentication succeeds.
 * Default password translator is plaintext translator.
 *
 * @author Scott Battaglia
 * @author Dmitriy Kopylenko
 * @author Marvin S. Addison
 *
 * @since 3.0
 */
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

    @NotNull
    private String sql;

    /** {@inheritDoc} */
    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {

        final String username = credential.getUsername();
        final String encryptedPassword = this.getPasswordEncoder().encode(credential.getPassword());
        try {
        	if(StringUtils.isBlank(username)) {
        		throw new EmptyUserNameException("username does not null.");
        	}
        	if(StringUtils.isBlank(encryptedPassword)) {
        		throw new EmptyPasswordException("Password does not null");
        	}
        	
        	Date now = new Date();
        	Date begin = DateUtil.dayBeginTime(now);
        	Date end = DateUtil.dayEndTime(now);
        	
        	UserAttempts userAttempts = null;
			try {
				userAttempts = getJdbcTemplate().queryForObject("SELECT * FROM s_user_attempts WHERE user_name = ? AND last_update_time BETWEEN  ? AND  ?", new RowMapper<UserAttempts>() {

					@Override
					public UserAttempts mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						UserAttempts userAttempts = new UserAttempts();
						userAttempts.setAttempts(rs.getInt("attempts"));
						userAttempts.setId(rs.getInt("id"));
						userAttempts.setUserName(rs.getString("user_name"));
						return userAttempts;
					}
				}, username,begin,end);
			} catch (DataAccessException e) {
				
			}
        
        	//判断帐号是否已经被锁定
        	if(userAttempts != null && userAttempts.getAttempts() >= UserAttempts.MAX_ATTEMPTS) {
        		throw new AccountLockedException("account is locked");
        	}
        
        	final User user =getJdbcTemplate().queryForObject(sql,new Object[]{username}, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					User user = new User();
					user.setState(rs.getInt("state"));
					user.setUserName(rs.getString("user_name"));
					user.setPassword(rs.getString("password"));
					return user;
				}
			});
           // final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
            if (!user.getPassword().equals(encryptedPassword)) {
            	//增加用户登录失败尝试次数
            	if(userAttempts != null) {
            		String sql ="UPDATE s_user_attempts SET attempts= ?, last_update_time = NOW() WHERE user_name =? ";
            		this.getJdbcTemplate().update(sql, userAttempts.getAttempts() +1,userAttempts.getUserName());
            		
            	} else {
            		String insertSql ="INSERT INTO s_user_attempts(user_name,attempts,last_update_time) VALUES(?,?,now())";
            		this.getJdbcTemplate().update(insertSql, user.getUserName(),1);
            	}
            	if(userAttempts!= null && userAttempts.getAttempts() >= UserAttempts.MAX_ATTEMPTS) {
            		String updateUserSql ="update s_user set state =2 where user_name = ?";
            		this.getJdbcTemplate().update(updateUserSql, user.getUserName());
            	}
            	
                throw new FailedPasswordException("Password does not match value on record.");
            }
            
        } catch (final IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {
            throw new PreventedException("SQL exception while executing query for " + username, e);
        }
        return createHandlerResult(credential, new SimplePrincipal(username), null);
    }

    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }
    
    
}
