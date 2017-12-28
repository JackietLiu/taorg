<%@page import="java.sql.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><%-- ${fns:getConfig('productName')} 登录 --%></title>
	<meta name="decorator" content="blank"/>
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/main.css" rel="stylesheet" type="text/css">
	<script src="${ctxStatic}/js/index.js" type="text/javascript"></script>
	<script src="${ctxStatic}/js/main.js" type="text/javascript"></script>
	<style type="text/css">
	#footer{color:#fff;}
    /*   html,body,table{background-color:#f5f5f5;width:100%;text-align:center;} */.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
/*  	window.onload = function(){
		window.location.href="${fns:getFrontPath()}";
	};  */
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
	$("#show").click(function(){
		$("#mainbody").removeClass("hide");
		
	});

		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
   	 if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		} 
	/* 	function toEnter(){
			 top.$.jBox.open(
					"iframe:${ctx}/taorg/registe/sysUser/form" , 
					"注册",
					1000,
					$(top.document).height()-70,
					{
						top : "20px",
						iframeScrolling: "no",
						buttons: { '关闭': true} 
					}
				);
				
		} */
		 
			function getNowFormatDate() {
				var time = document.getElementById("time");
			    var date = new Date();
			    var seperator1 = "-";
			    var seperator2 = ":";
			    var seperator3 = " ";
			    var month = date.getMonth() + 1;
			    var strDate = date.getDate();
			    var year = date.getFullYear();
			    var hour = date.getHours();			   
			    hour = hour > 9 ? hour :"0" + hour;
			    
			    var minute = date.getMinutes();
			    minute = minute > 9 ? minute :"0" + minute;
			 
			    var second = date.getSeconds();
			    second = second > 9 ? second:"0" + second;
			    
			    if (month >= 1 && month <= 9) {
			        month = "0" + month;
			    }
			    if (strDate >= 0 && strDate <= 9) {
			        strDate = "0" + strDate;
			    }
			   
			    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate +seperator3 + hour + seperator2 + minute + seperator2 + second;			   
			    time.innerHTML=currentdate;
			  
			}
			setInterval("getNowFormatDate()",1000);
	
	</script>
	<style type="text/css">
	.hide{display:none;}
	.show{position:absolute;right:100px;top:20px;border:none;background:none;color:blue;font-size:30px;}
	#mainbody{position:absolute;left:40%;top:20%;padding:10px;}
	</style>
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="body-home">

    <div class="head">
        <div class="head-inner">

            <div class="mc-t">

                <div class="logo"><img height="25px" src="${ctxStatic}/images/logo.png"></div>

                <div id="searchHead" class="select-city">
                    <span class="txt">上海</span>
                    <select name="" class="sel">
                        <option value="上海">上海</option>
                        <option value="北京">北京</option>
                        <option value="广州">广州</option>
                    </select>
                </div>

                <div id="menuHead" class="menu">
                    <ul>
                        <li class="home on"><a href="#">首页</a></li>
                        <li class="ser-workman"><a href="#">找工人</a></li>
                        <li class="ser-work"><a href="#">找工作</a></li>
                        <li class="services"><a href="#">平台服务</a></li>
                    </ul>
                </div>

                <div class="userinfo">
                    <a class="btn-reg trans" href="${ctx}/taorg/registe/sysUser/form"><span>注册</span></a>
                    <a class="btn-login trans" href="#" id="show"><span>登录</span></a>
                </div>

            </div>

            <div class="mc">
                <div class="mc-inner">
                    <div style="position:absolute;left:40%;top:35%;padding:10px; font-size:35px;color:#fff;line-height:45px;">
                    <div>上海宝冶集团有限公司<%-- <img src="${ctxStatic}/images/home-head-desc-txt.png" alt="上海宝冶集团有限公司，劳务实名制管理平台"> --%></div>
                    <div>劳务实名制管理平台</div>
                    </div>
                    <div class="search-con">
                        <div id="searchHome" class="select-search">
                            <span class="txt">找工人</span>
                            <select name="" class="sel">
                                <option value="找工人">找工人</option>
                                <option value="找老板">找老板</option>
                            </select>
                        </div>
                        <input type="text" class="input" placeholder="搜索你想要的">
                        <input type="button" value="" class="btn-submit">
                    </div>

                    <div class="search-hot">热门搜索：木工，水电工，实名认证，劳模会员，托管</div>

                </div>
            </div>

        </div>
    </div>

    <div class="home-recommend">

        <h2><span>为您推荐</span></h2>

        <div id="recommendList" class="list loaded">
            <div class="list-inner fix">

                <div class="box b-p p1" style="height: 400px;">
                    <div class="pic"><img src="${ctxStatic}/images/home-recommend-p1.jpg"></div>
                </div>

                <div class="box b-c c1" style="height: 400px;">
                    <div class="con">
                        <div class="con-inner">
                            <div class="tit">金牌劳模会员</div>
                            <div class="name">李静</div>
                            <div class="desc">
                                <ul>
                                    <li>会员级别：普通会员</li>
                                    <li>籍贯：江苏<span class="ml25">从业年限：26年</span></li>
                                    <li>工种：水电工</li>
                                    <li>基础工价：300.0元/m<sup>2</sup></li>
                                    <li>成交量：3单</li>
                                </ul>
                            </div>
                            <div class="amount">
                                <span class="name-a">奖金</span>
                                <span class="nums">￥3000</span>
                            </div>
                            <div class="like">点赞数：<span class="nums">45</span></div>
                        </div>
                    </div>
                </div>

                <div class="box b-p p2" style="height: 400px;">
                    <div class="pic"><img src="${ctxStatic}/images/home-recommend-p2.jpg"></div>
                </div>

                <div class="box b-c c3" style="height: 400px;">
                    <div class="con">
                        <div class="con-inner">
                            <div class="tit">铜牌劳模会员</div>
                            <div class="name">王海</div>
                            <div class="desc">
                                <ul>
                                    <li>会员级别：普通会员</li>
                                    <li>籍贯：江苏<span class="ml25">从业年限：26年</span></li>
                                    <li>工种：水电工</li>
                                    <li>基础工价：300.0元/m<sup>2</sup></li>
                                    <li>成交量：3单</li>
                                </ul>
                            </div>
                            <div class="amount">
                                <span class="name-a">奖金</span>
                                <span class="nums fa">￥1000</span>
                            </div>
                            <div class="like">点赞数：<span class="nums">45</span></div>
                        </div>
                    </div>
                </div>

                <div class="box b-p p3" style="height: 400px;">
                    <div class="pic"><img src="${ctxStatic}/images/home-recommend-p3.jpg"></div>
                </div>

                <div class="box b-c c2" style="height: 400px;">
                    <div class="con">
                        <div class="con-inner">
                            <div class="tit">银牌劳模会员</div>
                            <div class="name">陈晓亮</div>
                            <div class="desc">
                                <ul>
                                    <li>会员级别：普通会员</li>
                                    <li>籍贯：江苏<span class="ml25">从业年限：26年</span></li>
                                    <li>工种：水电工</li>
                                    <li>基础工价：300.0元/m<sup>2</sup></li>
                                    <li>成交量：3单</li>
                                </ul>
                            </div>
                            <div class="amount">
                                <span class="name-a">奖金</span>
                                <span class="nums">￥2000</span>
                            </div>
                            <div class="like">点赞数：<span class="nums">45</span></div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>

    <div class="foot">
        <div class="foot-inner">

            <div class="nav fix">
                <div class="box">
                    <h3><a href="#">首页</a></h3>
                    <ul>
                        <li><a href="#">关于</a></li>
                        <li><a href="#">新手入门</a></li>
                    </ul>
                </div>
                <div class="box">
                    <h3><a href="#">联系与合作</a></h3>
                    <ul>
                        <li><a href="#">联系我们</a></li>
                        <li><a href="#">用户反馈</a></li>
                    </ul>
                </div>
                <div class="box">
                    <h3><a href="#">移动客户端</a></h3>
                    <ul>
                        <li><a href="#"> iPhone 版</a></li>
                        <li><a href="#"> Android 版</a></li>
                        <li><a href="#"> HD</a></li>
                    </ul>
                </div>
                <!-- <div class="box">
                    <h3><a href="#">关注我们</a></h3>
                    <ul>
                        <li><a href="#">新浪微博：@</a></li>
                        <li><a href="#">官方 QQ：188126952</a></li>
                        <li><img src="res/images/credit-lianmeng.png" alt="安全联盟实名认证"></li>
                    </ul>
                </div> -->
            </div>

            <div class="copyright">2016 上海齐和网络科技有限公司.版权所有 沪ICP备15049659号</div>

        </div>
    </div>
	
		
</div>
	<div id="mainbody" class="hide">
	<div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>
	
	<h1 class="form-signin-heading"></h1> 
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
		<label class="input-label" for="username">登录名</label>
		<input type="text" id="username" name="username" class="input-block-level required" value="${username}">
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required">
		<c:if test="${isValidateCodeLogin}"><div class="validateCode">
			<label class="input-label mid" for="validateCode">验证码</label>
			<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
		</div></c:if><%--
		<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
		<input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;
	    <%-- <a href="${ctx}/taorg/registe/sysUser/form" onClick="">注册</a> --%>
	    
		<label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label>
		<div id="themeSwitch" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
			<ul class="dropdown-menu">
			  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
			</ul>
			<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
		</div>
	</form>
	<div class="footer">
	
		<font color="#fff">Copyright &copy; 2016-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - ${fns:getConfig('version')} </font>
		<font color="#fff">当前时间：</font><span id="time" style="color:red"></span><time ></time>
	</div>
	<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
	</div>
</body>
</html>