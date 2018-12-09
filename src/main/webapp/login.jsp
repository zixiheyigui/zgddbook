<%@ page import="com.oraclewdp.ddbookmaket.model.Admin" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <!-- 告诉浏览器不要缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="bower_components/bootswatch/dist/united/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container-fluid" style="width: 80%">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">

                    <form method="post" autocomplete="off" action="login" id="loginForm">
                        <%
                            Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
                            Admin admin = (Admin) request.getAttribute("admin");
                            if (admin != null) {
                        %>
                        <div class="form-group row">
                            <label for="inputName" class="col-sm-2 col-form-label text-right">用户名</label>
                            <div class="col-sm-10">
                                <%
                                    if (errors != null && errors.get("name") != null) {
                                %>
                                <input type="text" class="form-control is-invalid" id="inputName" placeholder="用户名" name="name"
                                       value="<%=admin.getName()%>">
                                <div  class="invalid-feedback">
                                    <%=errors.get("name")==null?"":admin.getName()%>
                                </div>
                               <%
                                   }else{
                                        %>
                                <input type="text" class="form-control is-valid" id="inputName" placeholder="用户名" name="name"
                                       value="<%=admin.getName()%>">
                                <%
                                   }
                               %>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputPwd" class="col-sm-2 col-form-label text-right">密码</label>
                            <div class="col-sm-10">
                                <%
                                    if (errors != null && errors.get("pwd") != null) {
                                %>
                                <input type="password" class="form-control is-invalid" id="inputPwd" placeholder="密码" name="pwd">
                                <div  class="invalid-feedback">
                                    <%=errors.get("pwd")%>
                                </div>
                                    <%
                                    }else{
                                    %>
                                <input type="password" class="form-control" id="inputPwd" placeholder="密码" name="pwd">
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputVcode" class="col-sm-2 col-form-label text-right">验证码</label>
                            <div class="col-sm-3">
                                <%
                                    if (errors != null && errors.get("Vcode") != null) {
                                %>
                                <input class="form-control is-invalid" id="inputVcode" placeholder="验证码" name="Vcode">
                                <div  class="invalid-feedback">
                                    <%=errors.get("Vcode")%>
                                </div>
                                <%
                                }else{
                                %>
                                <input class="form-control" id="inputVcode" placeholder="验证码" name="Vcode">
                                <%
                                    }
                                %>

                            </div>
                            <div class="col-sm-7">
                                <img src="vcode.png" id="VcodeImg" title="看不清换一张">
                            </div>
                            <div class="valid-feedback">
                                <%
                                    if (request.getAttribute("msg") != null) {
                                %>
                                <h2><%=request.getAttribute("msg")%>
                                </h2>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-primary">登陆</button>
                            </div>
                        </div>

                        <%
                        } else {
                        %>
                        <div class="form-group row">
                            <label for="inputName" class="col-sm-2 col-form-label text-right">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputName" placeholder="用户名" name="name">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputPwd" class="col-sm-2 col-form-label text-right">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="inputPwd" placeholder="密码" name="pwd">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputVcode" class="col-sm-2 col-form-label text-right">验证码</label>
                            <div class="col-sm-3">
                                <input class="form-control" id="inputVcode" placeholder="验证码" name="Vcode">
                            </div>
                            <div class="col-sm-7">
                                <img src="vcode.png" id="VcodeImg" title="看不清换一张">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-primary">登陆</button>
                            </div>
                        </div>
                    </form>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="bower_components/jquery/dist/jquery.slim.js">
</script>
<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js">
</script>
<script type="text/javascript">
    $(function () {
        $("#VcodeImg").click(function () {
            $(this).attr("src", "vcode.png?t=" + Math.random())
        });
    });
</script>
<script type="text/javascript" src="bower_components/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript" src="bower_components/jquery-validation/dist/additional-methods.js"></script>
<script type="text/javascript"src="bower_components/jquery-validation/src/localization/messages_zh.js"></script>

<script type="text/javascript">
    $(function () {/*https://jqueryvalidation.org/accept-method/查官方文档*/
        $("#loginForm").validate({
            /*规则*/
            rules:{
                name:{/*错误提示可以自己定义，也可以不写，不写会使用默认的提示消息*/
                    required: true,
                    maxlength:45,
                    minlength:3
                },
                pwd:{/*错误提示可以自己定义，也可以不写，不写会使用默认的提示消息*/
                    required: true,
                    maxlength:45,
                    minlength:3
                },
                Vcode:{
                    required: true,
                    maxlength:4,
                    minlength:4
                }
            },
            /*错误信息设置*/
            messages:{

            },
            errorElement:"div",/*修改元素*/
            errorClass:"invalid-feedback",/*修改成无效的*/
            highlight:function (element,errorClass,validClass) {/*高亮显示*/
                $(element).addClass("is-invalid").removeClass(validClass)
            },
            unhighlight:function (element,errorClass,validClass) {/*取消高亮显示*/
                $(element).removeClass("is-invalid").addClass(validClass);
            },
            validClass:"is-valid"/*有效的*/
        });
    });
</script>

</body>
</html>