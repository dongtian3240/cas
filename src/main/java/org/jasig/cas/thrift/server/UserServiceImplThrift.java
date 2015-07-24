package org.jasig.cas.thrift.server;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.thrift.TException;

/**
 * @author dongtian
 * @date   2015年5月27日 下午8:11:53
 */
public class UserServiceImplThrift implements UserServiceThrift.Iface{

	@NotNull
	private  QueryAccountThriftServiceHandler queryAccountThriftServiceHandler;

	public UserServiceImplThrift(
			QueryAccountThriftServiceHandler queryAccountThriftServiceHandler) {
		this.queryAccountThriftServiceHandler = queryAccountThriftServiceHandler;
	}

	@Override
	public User getUserByUserName(String userName) throws TimedOutException,
			TException {
		return this.queryAccountThriftServiceHandler.getUserByUserName(userName);
	}

	@Override
	public boolean checkUserName(String userName, int id)
			throws TimedOutException, TException {
		return this.queryAccountThriftServiceHandler.checkUserName(userName, id);
	}

	@Override
	public boolean checkMobile(String mobile, int id) throws TimedOutException,
			TException {
		return this.queryAccountThriftServiceHandler.checkMobile(mobile, id);
	}

	@Override
	public boolean checkEmail(String email, int id) throws TimedOutException,
			TException {
		return this.queryAccountThriftServiceHandler.checkEmail(email, id);
	}

	@Override
	public boolean checkIdCard(String idCard, int id) throws TimedOutException,
			TException {
		return this.queryAccountThriftServiceHandler.checkIdCard(idCard, id);
	}

	@Override
	public boolean checkJobId(String jobId, int id) throws TimedOutException,
			TException {
		return this.queryAccountThriftServiceHandler.checkJobId(jobId, id);
	}

	@Override
	public boolean addNewUser(User user) throws TimedOutException, TException {
		return this.queryAccountThriftServiceHandler.addNewUser(user);
	}

	@Override
	public boolean addNewUserList(List<User> userList)
			throws TimedOutException, TException {
		return this.queryAccountThriftServiceHandler.addNewUserList(userList);
	}

	@Override
	public boolean updateUser(User user) throws TimedOutException, TException {
		return this.queryAccountThriftServiceHandler.updateUser(user);
	}

	@Override
	public List<User> lookOnlineUserList() throws TimedOutException, TException {
		return null;
	}


	@Override
	public boolean checkPassword(String password, int id)
			throws TimedOutException, TException {
		return this.queryAccountThriftServiceHandler.checkPassword(password, id);
	}

	@Override
	public boolean checkNickName(String nickName, int id)
			throws TimedOutException, TException {
		return this.queryAccountThriftServiceHandler.checkNickName(nickName, id);
	}

	@Override
	public int findUserCount() throws TimedOutException, TException {
		return this.queryAccountThriftServiceHandler.findUserCount();
	}

	@Override
	public List<User> findUserList(int startIndex, int rowCount)
			throws TimedOutException, TException {
		return this.queryAccountThriftServiceHandler.findUserList(startIndex, rowCount);
	}


}
