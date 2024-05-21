// 获取 URL 中的参数
function getParameterByToken(token, url) {
    if (!url) url = window.location.href;  // 如果没有提供url参数，默认使用当前页面的URL
    token = token.replace(/[\[\]]/g, "\\$&");  // 对参数名进行一些处理，将一些特殊字符进行转义
    var regex = new RegExp("[?&]" + token + "(=([^&#]*)|&|#|$)");  // 创建一个正则表达式来匹配指定的参数名
    var results = regex.exec(url);  // 使用正则表达式来匹配URL中的参数
    if (!results) return null;  // 如果没有匹配到指定的参数，返回null
    if (!results[2]) return '';  // 如果匹配到的参数没有值，返回空字符串
    return decodeURIComponent(results[2].replace(/\+/g, " "));  // 返回解码后的参数值
}

// 获取名为 "Token" 的参数
var token = getParameterByToken('Token',null);  // 调用上面定义的函数来获取名为 "Token" 的参数值
console.log("Token is: " + token);  // 在控制台打印出获取到的token值