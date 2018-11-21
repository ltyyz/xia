$(function () {
    $("input[name=username]").focus();
    if (location.href.indexOf("error") != -1) {
        $(".login-tips").html("用户名或密码错误");
    }
});