<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注销登录</title>

<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="<c:url value='/bootstrap/media/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css"/>

	<link href="<c:url value='/bootstrap/media/css/bootstrap-responsive.min.css'/>" rel="stylesheet" type="text/css"/>

	<link href="<c:url value='/bootstrap/media/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css"/>

	<link href="<c:url value='/bootstrap/media/css/style-metro.css'/>" rel="stylesheet" type="text/css"/>

	<link href="<c:url value='/bootstrap/media/css/style.css'/>" rel="stylesheet" type="text/css"/>

	<link href="<c:url value='/bootstrap/media/css/style-responsive.css'/>" rel="stylesheet" type="text/css"/>

	<link href="<c:url value='/bootstrap/media/css/default.css'/>" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="<c:url value='/bootstrap/media/css/uniform.default.css'/>" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="<c:url value='/bootstrap/media/css/login.css'/>" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="<c:url value='/bootstrap/media/image/favicon.ico'/>" />
	<style type="text/css">
	 .divError{
	  color: #B94A48;
	 }
	</style>
</head>
<body class="login">

	<!-- BEGIN LOGO -->

	<div class="logo">

		<img src="<c:url value='/bootstrap/media'/>/image/logo-big.png" alt="" /> 

	</div>

	<!-- END LOGO -->
	<div style="margin: 0 auto;padding: 20px 30px 15px;width: 291px;">
	
	<div class="alert alert-error">

				<button class="close" data-dismiss="alert"></button>

				 <h2><spring:message code="screen.logout.header" /></h2>
				    <p><spring:message code="screen.logout.success" /></p>
				    <p><spring:message code="screen.logout.security" /></p>
					 正在帮您跳转,请稍后...
					 <script type="text/javascript">
					 <% String service = request.getParameter("service");%>
					 
					  window.location.href="<%=service%>";	
					 </script>

			</div>
	</div>
	

	


	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->

	<div class="copyright">

		2015 &copy; 冬天.

	</div>

	<!-- END COPYRIGHT -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<c:url value='/bootstrap/media/js/jquery-1.10.1.min.js'/>" type="text/javascript"></script>

	<script src="<c:url value='/bootstrap/media/js/jquery-migrate-1.2.1.min.js'/>" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="<c:url value='/bootstrap/media/js/jquery-ui-1.10.1.custom.min.js'/>" type="text/javascript"></script>      

	<script src="<c:url value='/bootstrap/media/js/bootstrap.min.js'/>" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<c:url value='/bootstrap/media'/>/js/excanvas.min.js"></script>

	<script src="<c:url value='/bootstrap/media'/>/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="<c:url value='/bootstrap/media/js/jquery.slimscroll.min.js'/>" type="text/javascript"></script>

	<script src="<c:url value='/bootstrap/media/js/jquery.blockui.min.js'/>" type="text/javascript"></script>  

	<script src="<c:url value='/bootstrap/media/js/jquery.cookie.min.js'/>" type="text/javascript"></script>

	<script src="<c:url value='/bootstrap/media/js/jquery.uniform.min.js'/>" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="<c:url value='/bootstrap/media/js/jquery.validate.min.js'/>" type="text/javascript"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<c:url value='/bootstrap/media/js/app.js'/>" type="text/javascript"></script>

	<script src="<c:url value='/bootstrap/media/js/login.js'/>" type="text/javascript"></script>      

	<!-- END PAGE LEVEL SCRIPTS --> 

	<script>
	
	var basePath = "<c:url value='/'/>";
	
	var Login = {
			init:function(){
				Login.ctrl.initValidateLoginForm();
			},
			ctrl:{
				/***
				初始化登录表单验证
				**/
				initValidateLoginForm:function() {
					$('.login-form').validate({
			            errorElement: 'label', //default input error message container
			            errorClass: 'help-inline', // default input error message class
			            focusInvalid: false, // do not focus the last invalid input
			            rules: {
			                username: {
			                    required: true
			                },
			                password: {
			                    required: true
			                },
			                remember: {
			                    required: false
			                }
			            },

			            messages: {
			                username: {
			                    required: "用户名不能为空"
			                },
			                password: {
			                    required: "密码不能为空"
			                }
			            },

			            invalidHandler: function (event, validator) { //display error alert on form submit   
			                $('.alert-error', $('.login-form')).show();
			            },

			            highlight: function (element) { // hightlight error inputs
			                $(element)
			                    .closest('.control-group').addClass('error'); // set error class to the control group
			            },

			            success: function (label) {
			                label.closest('.control-group').removeClass('error');
			                label.remove();
			            },

			            errorPlacement: function (error, element) {
			                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
			            },

			            submitHandler: function (form) {
			            	form.submit();
			                 return true;  
			            }
			        });
				}
			}
	};

		jQuery(document).ready(function() {     

		  App.init();
		  Login.init();

		});

	</script>



</html>