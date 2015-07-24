package org.jasig.cas.thrift.server;

import java.util.List;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.jasig.cas.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * cas 单点登录中用户信息校验
 * 用户信息同步thrift 接口实现
 * @author dongtian
 * @date   2015年6月1日 上午10:13:03
 */
public class QueryAccountThriftServiceHandler  {

	@NotNull
	private JdbcTemplate jdbcTemplate;
	@NotNull
	private DataSource dataSource;
	
	@Autowired
	private UserMapper userMapper;
	
	private static final Logger logger = Logger.getLogger(QueryAccountThriftServiceHandler.class);
	

	public QueryAccountThriftServiceHandler(DataSource dataSource) {
		 this.jdbcTemplate = new JdbcTemplate(dataSource);
	     this.dataSource = dataSource;
	}

	public User getUserByUserName(String userName) {
		User user = this.userMapper.findUserByUserName(userName);
		return  user== null ?new User():user;
	}

	public boolean checkUserName(String userName, int id) {
		return this.userMapper.checkUserName(userName, Integer.valueOf(id)) >0?true:false;
	}

	public boolean checkMobile(String mobile, int id) {
		int count =  this.userMapper.checkMobile(mobile, Integer.valueOf(id));
		 boolean has = count > 0?true:false;
		 return has;
	}

	public boolean checkEmail(String email, int id) {
		 return this.userMapper.checkEmail(email, Integer.valueOf(id)) >0?true:false;
	}

	public boolean checkIdCard(String idCard, int id) {
		return false;
	}

	public boolean checkJobId(String jobId, int id) {
		return false;
	}

	public boolean addNewUser(User user) {
		try {
			this.userMapper.addUser(user);
		} catch (Exception e) {
			logger.info("insert  new user error ",e);
			return false;
		}
		return true;
	}

	public boolean updateUser(User user) {
		try {
			this.userMapper.editUser(user);
		} catch (Exception e) {
			logger.info("update user is error" ,e);
		}
		return true;
	}

	public boolean addNewUserList(List<User> userList) {
		
		return false;
	}

	public boolean checkPassword(String password, int id) {
		return this.userMapper.checkPassword(password, id);
	}

	public boolean checkNickName(String nickName, int id) {
		 return this.userMapper.checkNickName(nickName, Integer.valueOf(id)) >0?true:false;
	}

	public int findUserCount() {
		return this.userMapper.findUserCount();
	}

	public List<User> findUserList(int startIndex, int rowCount) {
		return this.userMapper.findUserList(startIndex, rowCount, null, null, null, null);
	}

	
	
}
