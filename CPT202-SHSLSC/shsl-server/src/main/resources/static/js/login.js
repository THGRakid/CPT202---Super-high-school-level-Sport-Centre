// 获取元素
var btn = document.getElementById("btn");
var userNameInput = document.getElementById("userName");
var userNameInput = document.getElementById("password");
// 添加点击事件
btn.onclick =function(){
// 获取用户名密码
    var userName = userNameInput.value
    var password = userNameInput.value

// 发送ajax到登录接口
$.post("./php/login.php",{userName:userName, password:password},function(data){

}, "json" )


}



