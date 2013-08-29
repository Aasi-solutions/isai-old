<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
</head>

		<center>			 File Upload</center>
					</br></br>    
      <form method="post" name="f2" action="adminaction?action=uploadaction" enctype="multipart/form-data">
     Enter Movie Name     : <input type="textbox"  name="fname" id="fid" ></br>
     			Select File to upload:
 <input type="file" name="uploadFile" /> 
            <br/><br/> 
 
            <input type="submit" value="Upload" /></br></br>      
         


Or GoTo Edit:
</br>
</br>
<a href="/isai/adminaction?action=adminedit">Edit</a> </form>  

<body>

</body>
</html>