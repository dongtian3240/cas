package org.jasig.cas.excption;

import javax.security.auth.login.LoginException;

/***
 * 输入的空密码错误异常
 * @author dongtian
 * @date   2015年5月25日 下午1:54:08
 */
public class EmptyPasswordException  extends LoginException{

	private static final long serialVersionUID = 6165437558435533155L;

	public EmptyPasswordException() {
		super();
	}

	public EmptyPasswordException(String msg) {
		super(msg);
	}

	
	
}
