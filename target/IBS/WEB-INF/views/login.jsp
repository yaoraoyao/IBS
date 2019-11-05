<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="zxx">
    <head>
        <title>Home</title>
        <!-- Meta-Tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <script>
            addEventListener("load", function () {
                setTimeout(hideURLbar, 0);
            }, false);

            function hideURLbar() {
                window.scrollTo(0, 1);
            }
        </script>
        <!-- //Meta-Tags -->
        <!-- Stylesheets -->
        <link href="/css/style.css" rel='stylesheet' type='text/css' />
        <!--// Stylesheets -->
        <!--online fonts-->
        <link href="/css/fonts.css" rel="stylesheet">
        <!--//online fonts-->
        <script type="text/javascript" src="/easyui/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="/easyui/common.js"></script>
        <script type="text/javascript">
            $(function () {
                /**
                 * 检查当前页面窗口对象是不是最高级窗口？
                 * window 表示当前窗口对象
                 * window.top 当前窗口对象的最顶级父窗口对象
                 * 如果他们不相等，就表示当前登录页面已经被嵌套了
                 */
                if(window != window.top){
                    window.top.location.href = "/login.do";
                }


                //绑定事件【AJAX异步登录】
                $("#btn-login").click(function () {
                    var params = $("#loginForm").toJson();
                    $.postJSON("/login.do",params,function(data){
                        if(data.status == 200){
                            //跳转到欢迎页面
                            location.href = "/index.do";
                        }else{
                            //登录失败
                            $("#errorMsg").text(data.msg);
                        }
                    });
                });
                //绑定键盘事件【实现回车键登录】
                $(document).keypress(function (event) {
                    if(event.keyCode == 13){
                        $("#btn-login").trigger("click");//触发一下btn-login按钮的点击事件
                    }
                });
            });
        </script>
    </head>
    <body>
        <h1>Xxx进销存管理系统登录</h1>
        <div class="w3ls-login box">
            <img src="/img/logo.png" alt="logo_img" />
            <!-- form starts here -->
            <form id="loginForm">
                <div class="agile-field-txt">
                    <input type="text" name="username" placeholder="用户名" required />
                </div>
                <div class="agile-field-txt">
                    <input type="password" name="password" placeholder="密码" required id="myInput" />
                    <div class="agile_label">
                        <input id="check3" name="check3" type="checkbox" value="show password">
                        <label class="check" for="check3">记住我</label>
                        <br/>
                        <span style="color: red;" id="errorMsg"></span>
                    </div>
                </div>
                <div class="w3ls-bot">
                    <input type="button" value="登录" id="btn-login" />
                </div>
            </form>
        </div>
        <!-- //form ends here -->
        <!--copyright-->
        <div class="copy-wthree">
            <p>Copyright &copy; 2018.Company name All rights reserved.</p>
        </div>
        <!--//copyright-->
    </body>
</html>
