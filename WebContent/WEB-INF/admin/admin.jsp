<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org   /TR/html4/loose.dtd">`
  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Admin Account!!!</title>
<h1>Enter Admin username and password</h1>
</head>
<body>
<script>
String adminaction = (String) request.getParameter("adminaction");
function validate()
{
if((document.f1.uname.value=="admin") && (document.f1.pass.value=="pass"))
{
alert("welcome");
    return true;
//response.sendRedirect("http://localhost:8080/Imayamweb/songs/radio/index.jsp");
}
else{
	alert("invalid");
	document.f1.uname.focus();
	return false;
}
	
}
</script>

<center><h1>Welcome to Admin Account!!!</h1><br><br>
<form name="f1" method="post" action="adminaction" onSubmit="return validate();" >
<input type="hidden" name="action" value="loginaction"/><br>
UserName<input type="text" name="uname"/><br>
Password<input type="password" name=pass><br><br><br>
<input type="submit" value="submit" /><br></center>
<input type="reset" value="Reset">
</form>
</body>
</html>