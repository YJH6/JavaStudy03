# AJAX

AJAX 异步js和xml

异步刷新:如果网页中某一地方需要修改,异步刷新可以使:只刷新该需要修改的

## 实现:

js:XMLHttpRequest对象

XMLHttpRequest对象的方法:

open(方法名(提交方式get|post),服务器地址,ture):与服务端建立连接

send():

​			get:send(null)

​	        post:send(参数值)

setRequestHeader(header,value):

​			get:不需要设置此方法

​			post:需要设置

​					a.如果请求元素中包含了文件上传;

​								setRequestHeader("Content-Type","multipart/form-data");

​					b.不包含文件上传

​								setRequestHeader("Content-Type","application/x-www-form-urlencoded");

XMLHttpRequest对象的属性:

readystate:请求状态		只有状态为4 代表请求完毕

status:响应状态		只有200 代表响应正常

onreadystatechange:回调函数

responseText:响应格式为String

responseXML:响应格式为XML