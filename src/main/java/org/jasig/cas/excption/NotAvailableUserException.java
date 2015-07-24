package org.jasig.cas.excption;

import javax.security.auth.login.LoginException;

/***
 * 当前用户不可用异常
 * @author dongtian
 * @date   2015年5月25日 下午3:24:22
 */
public class NotAvailableUserException extends LoginException {

	private static final long serialVersionUID = 3258860593860541432L;

	public NotAvailableUserException() {
		super();
	}

	public NotAvailableUserException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	
}
