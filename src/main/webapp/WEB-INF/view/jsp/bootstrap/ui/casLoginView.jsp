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
<title>用户登录</title>

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

		<img src="<c:url value='/bootstrap/media/image/logo-big.png'/>" alt="logo" /> 

	</div>

	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->

	<div class="content">

		<!-- BEGIN LOGIN FORM -->

		<form:form cssClass="form-vertical login-form" commandName="${commandName}" htmlEscape="true"  method="post">

			<h3 class="form-title">请登录</h3>
		<form:errors path="*" id="msg" cssClass="divError" element="div" htmlEscape="false" />
			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">用户名</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-user"></i>

						<form:input cssClass="m-wrap placeholder-no-fix" accesskey="${userNameAccessKey}"  placeholder="用户名" path="username" autocomplete="off" htmlEscape="true"/>
						
					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="password"  path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
						 <spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
						<input type="hidden" name="lt" value="${loginTicket}" />
      					<input type="hidden" name="execution" value="${flowExecutionKey}" />
     				 	<input type="hidden" name="_eventId" value="submit" />
     				 	<input type="hidden" name="isajax" value="true" />  
            			<input type="hidden" name="isframe" value="true" />  
            			<input type="hidden" id="service" name="sorceService" value="${service}" />  
					</div>

				</div>

			</div>
			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">验证码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-camera-retro"></i>
						<input type="text" class="m-wrap small" id="captcha" name="captcha" placeholder="验证码">
						<img alt="验证码" style="cursor: pointer;" id="imgCaptcha" width="120" height="40" src="<c:url value='/captcha.png'/>">
					</div>

				</div>

			</div>

			<div class="form-actions">

				<label class="checkbox">

				<input type="checkbox" name="rememberMe" value="true" checked="checked"/> 记住我(2周)</label>

				<button type="submit" class="btn green pull-right">

				登录 <i class="m-icon-swapright m-icon-white"></i>

				</button>            

			</div>

			<div class="forget-password">

				<h4>忘记密码 ?</h4>

				<p>

					不要担心,点击 <a href="javascript:;" class="" id="forget-password">这</a>

					重置你的密码.

				</p>

			</div>

			<div class="create-account">

				<p>

					还没有帐号 ?&nbsp; 

					<a href="javascript:;" id="register-btn" class="">创建一个新的帐号</a>
					

				</p>

			</div>

		</form:form>

		<!-- END LOGIN FORM -->        

		<!-- BEGIN FORGOT PASSWORD FORM -->

		<form class="form-vertical forget-form" action="index.html">

			<h3 class="">忘记密码 ?</h3>

			<p>输入你注册时候的有效邮箱地址进行重置你的密码.</p>

			<div class="control-group">

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-envelope"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="邮箱" name="email" />

					</div>

				</div>

			</div>

			<div class="form-actions">

				<button type="button" id="back-btn" class="btn">

				<i class="m-icon-swapleft"></i> 返回

				</button>

				<button type="submit" class="btn green pull-right">

				提交 <i class="m-icon-swapright m-icon-white"></i>

				</button>            

			</div>
	  
		</form>

		<!-- END FORGOT PASSWORD FORM -->

		<!-- BEGIN REGISTRATION FORM -->

		<form class="form-vertical register-form" action="index.html">

			<h3 class="">新注册</h3>

			<p>输入你的新帐号信息:</p>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">用户名</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-user"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" name="username"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input class="m-wrap placeholder-no-fix" type="password" id="register_password" placeholder="密码" name="password"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">确认密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-ok"></i>

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="Re-type Your Password" name="rpassword"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">邮箱</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-envelope"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<div class="controls">

					<label class="checkbox">

					<input type="checkbox" name="tnc"/> 我同意 <a href="#">服务条款</a> 和 <a href="#">隐身保护</a>

					</label>  

					<div id="register_tnc_error"></div>

				</div>

			</div>

			<div class="form-actions">

				<button id="register-back-btn" type="button" class="btn">

				<i class="m-icon-swapleft"></i>  返回

				</button>

				<button type="submit" id="register-submit-btn" class="btn green pull-right">

				注册 <i class="m-icon-swapright m-icon-white"></i>

				</button>            

			</div>

		</form>

		<!-- END REGISTRATION FORM -->

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
				Login.ctrl.initEvent();
				Login.ctrl.initValidateLoginForm();
			},
			ctrl:{
				
				 initEvent:function() {
					
					 $("#imgCaptcha").click(function(){
						 $("#imgCaptcha").attr("src",basePath +"captcha.png?date" + new Date());
						 return false;
					 });
				 },
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
			                },
			                captcha:{
			                	required:true
			                }
			            },

			            messages: {
			                username: {
			                    required: "用户名不能为空"
			                },
			                password: {
			                    required: "密码不能为空"
			                },
			                captcha:{
			                	required:'验证码不能为空'
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