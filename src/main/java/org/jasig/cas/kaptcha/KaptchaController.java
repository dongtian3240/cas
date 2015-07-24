package org.jasig.cas.kaptcha;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/***
 * 生成验证码
 * @author dongtian
 * @date   2015年5月26日 下午4:59:57
 */
public class KaptchaController  extends AbstractController {

	@NotNull
	private Producer captchaProducer;
	
	
	public KaptchaController(Producer captchaProducer) {
		
		this.captchaProducer = captchaProducer;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();  
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
        System.out.println("******************验证码是: " + code + "******************");  
          
        response.setDateHeader("Expires", 0);  
          
        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
          
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
          
        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");  
          
        // return a jpeg  
        response.setContentType("image/jpeg");  
          
        // create the text for the image  
        String capText = captchaProducer.createText();  
          
        // store the text in the session  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
        System.out.println("******************生成的新验证码是: " + capText + "******************"); 
        // create the image with the text  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
          
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
	}
	 
	 
}
