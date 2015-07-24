package org.jasig.cas.excption;

import javax.security.auth.login.LoginException;

/**
 * 空的验证码异常
 * @author dongtian
 * @date   2015年5月27日 下午1:35:29
 */
public class EmptyKaptchaException extends LoginException {

	private static final long serialVersionUID = 8831505787585328599L;

	public EmptyKaptchaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmptyKaptchaException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
