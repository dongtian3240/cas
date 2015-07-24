package org.jasig.cas.kaptcha;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.excption.EmptyKaptchaException;
import org.jasig.cas.excption.KaptchaException;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.google.code.kaptcha.Constants;

/**
 * 提交表单验证码校验
 * @author dongtian
 * @date   2015年5月27日 上午10:01:15
 */
public class KaptchaValidaterAction {

   public static final String AUTHENTICATION_FAILURE = "authenticationFailure";
   
   private static final String CAPTCHA_PARAMTER_NAME="captcha";
   private static final String SUCCESS = "success";
   private static final String DEFAULT_MESSAGE_BUNDLE_PREFIX = "authenticationFailure.";
   private static final String ERROR = "error";

   public final Event submit(final RequestContext context, final Credential credential,
           final MessageContext messageContext) throws Exception {
       
	   
	   HttpServletRequest request = WebUtils.getHttpServletRequest(context);
	   String captcha = request.getParameter(CAPTCHA_PARAMTER_NAME);
	   String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
	   String messageCode = DEFAULT_MESSAGE_BUNDLE_PREFIX;
	   if(StringUtils.isBlank(code)||StringUtils.isBlank(captcha)) {
		   messageCode += EmptyKaptchaException.class.getSimpleName();
		   messageContext.addMessage(new MessageBuilder().error().code(messageCode).build());
		   return new Event(this, ERROR);
	   } else {
		   if(StringUtils.equals(code, captcha)) {
			   return new Event(this, SUCCESS);
		   }else {
			   messageCode += KaptchaException.class.getSimpleName();
			   messageContext.addMessage(new MessageBuilder().error().code(messageCode).build());
			   return new Event(this, ERROR);
		   }
	   }
   }

	
}
