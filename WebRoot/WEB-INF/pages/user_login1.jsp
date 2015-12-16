<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>
<title>登录</title>
<meta charset="gb2312">
<link href="<%=path%>/css/home.css?v=2" rel="stylesheet" type="text/css" />
<style type="text/css">
.bottow { float: left; height: 30px; width: auto; }
</style>
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.js"></script>
</head>

<body>

<div class="wrap">
	<div class="banner-show" id="js_ban_content">
		<div class="cell bns-01">
			<div class="con">
			</div>
		</div>
		<div class="cell bns-02" style="display:none;">
			<div class="con">
				<a  target="_blank" class="banner-link">
				<i>圈子</i></a> </div>
		</div>
		<div class="cell bns-03" style="display:none;">
			<div class="con">
				<a  target="_blank" class="banner-link">
				<i>企业云</i></a> </div>
		</div>
	</div>
	<div class="banner-control" id="js_ban_button_box">
		<a href="javascript:;" class="left">左</a>
		<a href="javascript:;" class="right">右</a> </div>
	<script type="text/javascript">
                ;(function(){
                    
                    var defaultInd = 0;
                    var list = $('#js_ban_content').children();
                    var count = 0;
                    var change = function(newInd, callback){
                        if(count) return;
                        count = 2;
                        $(list[defaultInd]).fadeOut(400, function(){
                            count--;
                            if(count <= 0){
                                if(start.timer) window.clearTimeout(start.timer);
                                callback && callback();
                            }
                        });
                        $(list[newInd]).fadeIn(400, function(){
                            defaultInd = newInd;
                            count--;
                            if(count <= 0){
                                if(start.timer) window.clearTimeout(start.timer);
                                callback && callback();
                            }
                        });
                    }
                    
                    var next = function(callback){
                        var newInd = defaultInd + 1;
                        if(newInd >= list.length){
                            newInd = 0;
                        }
                        change(newInd, callback);
                    }
                    
                    var start = function(){
                        if(start.timer) window.clearTimeout(start.timer);
                        start.timer = window.setTimeout(function(){
                            next(function(){
                                start();
                            });
                        }, 8000);
                    }
                    
                    start();
                    
                    $('#js_ban_button_box').on('click', 'a', function(){
                        var btn = $(this);
                        if(btn.hasClass('right')){
                            //next
                            next(function(){
                                start();
                            });
                        }
                        else{
                            //prev
                            var newInd = defaultInd - 1;
                            if(newInd < 0){
                                newInd = list.length - 1;
                            }
                            change(newInd, function(){
                                start();
                            });
                        }
                        return false;
                    });
                    
                })();
            </script>
   <form action="admin/user/user_logon.action" name="dlform" method="post">         
	<div class="container">
		<div class="register-box">
			<div class="reg-slogan">
				<h3>用户登录</h3></div>
			<div class="reg-form" id="js-form-mobile">
				<br>
				<br>
				<div class="cell">
					<label for="js-mobile_ipt" ></label>
					<input type="text" name="account" value="用户名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '用户名';}"  id="js-mobile_ipt" class="text" maxlength="11" />
				</div>
				<div class="cell">
					<label for="js-mobile_pwd_ipt" ></label>
					<input type="password" name="password" value="请输入密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入密码';}" onmouseover="this.style.borderColor='#FF6600'" onmouseout="this.style.borderColor=''"  id="js-mobile_pwd_ipt"  maxlength="11" class="text" />
			  </div>
				<!-- !短信验证码 -->
				<div class="cell vcode">
					<label for="js-mobile_vcode_ipt"></label>
					<input type="text" name="code"  onmouseover="this.style.borderColor='#FF6600'" onmouseout="this.style.borderColor=''" value="输入验证码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '输入验证码';}" id="js-mobile_vcode_ipt" class="text" maxlength="6" />
					<a href="javascript:;" id="js-get_mobile_vcode" class="button btn-disabled">
					图片验证码</a> </div>
				<div class="bottom">
					<a id="js-mobile_btn" href="javascript:;" onclick="document.dlform.submit()" class="button btn-green">
					立即登录</a></div>
				<div class="lowBottom" style="margin-top: 40px;">
					<h2><font color=red><I><s:property value="errors.error1[0]"/></I></font></h2>
				</div>
					
			</div>
		</div>
	</div>
	</form>
</div>

<div class="bottow">
    <img src="<%=path%>/images/mid_banner/boot01.png">

</div>

	
					


</body>

</html>
