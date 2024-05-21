register=function(){
    // 得到用户名，邮箱
    var_id = document.getElementById("adminname").value;
    // 得到密码
    var_password = document.getElementById("password").value;
    // 访问数据库
    // 第二个参数为数组，数组为 数据库地址，数据库名称，存储过程名称，存储过程参数。
    U.A.Request("",["","","",_adminname,_password],function(r){
        // 获取数据库数据
        var_data =r.value;
        // 获取放置内容的元素
        if(_data[0].admin_name){
            alert("Register success")
        }else{
            alert("Register failed")
        }
    })

}