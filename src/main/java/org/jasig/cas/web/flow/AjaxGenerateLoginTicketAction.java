package org.jasig.cas.web.flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.BooleanUtils;
import org.jasig.cas.util.UniqueTicketIdGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.execution.RequestContext;

public class AjaxGenerateLoginTicketAction {
	/** 3.5.1 - Login tickets SHOULD begin with characters "LT-". */
    private static final String PREFIX = "LT";

    /** Logger instance. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @NotNull
    private UniqueTicketIdGenerator ticketIdGenerator;

    public final String generate(final RequestContext context) {
        final String loginTicket = this.ticketIdGenerator.getNewTicketId(PREFIX);
        logger.debug("Generated login ticket {}", loginTicket);
        WebUtils.putLoginTicket(context, loginTicket);
        
        HttpServletRequest request = WebUtils.getHttpServletRequest(context);
       HttpServletResponse response =  WebUtils.getHttpServletResponse(context);
        System.err.println(request.getParameter("isAjax"));
        boolean isAjax = BooleanUtils.toBoolean(request.getParameter("isAjax"));
        //判断是否为ajax sso 获取
        if(!isAjax) {
        	return "generated";
        } else {
        	return "loginTicket";
        }
        
    }

    public void setTicketIdGenerator(final UniqueTicketIdGenerator generator) {
        this.ticketIdGenerator = generator;
    }
}
