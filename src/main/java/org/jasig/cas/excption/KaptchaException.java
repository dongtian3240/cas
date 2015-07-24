package org.jasig.cas.excption;

import javax.security.auth.login.LoginException;

/**
 * 验证码异常
 * @author dongtian
 * @date   2015年5月27日 下午1:34:42
 */
public class KaptchaException extends LoginException {

	private static final long serialVersionUID = 2562069048367865470L;

	public KaptchaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KaptchaException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	
}
