<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>考勤系统</title>
    <jsp:include page="/resource/base/resource-traditional.jsp"/>
</head>
<body>
    <input type="text" id="name" name="name" value="2222222222222222">
</body>
<script>
    $(function(){
       console.log($("#name").val());
    });
</script>
</html>
