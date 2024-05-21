<?php
//  接受前端传输过来的数据
$userName = $_POST["userName"];
$password = $_POST["password"];

// 连接数据库
$conn = mysqli_connect()

// 定义查询语句
$selectSQL = "SELECT * FROM user WHERE userName='$user_name' and password= '$password' "

// 是否查询到
$result = mysqli_query($coon, $selectSQL);

// 获取结果集中的数据条数
$num= mysqli_num_rows($result)

// 根据条数做判断
if($num ==1){
    // 查到数据允许登录
   echo json_encode(array("error"=>0, "data"=>"Login success") );
}else{
    // 没有查到数据不允许登录
    echo json_encode(array("error"=>1, "data"=>"Login failed") );
}
?>