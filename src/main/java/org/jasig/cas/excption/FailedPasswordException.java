package org.jasig.cas.excption;

import javax.security.auth.login.LoginException;

/***
 * 密码错误异常
 * @author dongtian
 * @date   2015年5月25日 下午1:54:08
 */
public class FailedPasswordException  extends LoginException{

	private static final long serialVersionUID = 6165437558435533155L;

	public FailedPasswordException() {
		super();
	}

	public FailedPasswordException(String msg) {
		super(msg);
	}

	
	
}
