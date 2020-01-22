<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="aplicacion.vista.Tag"%>
<%!String error = null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
	error = (String) request.getParameter("e");
	%>
	<ul id="navegacion">
		<li>
			<a href="Principal">
				<img src="imgs/logo.png">
			</a>
		</li>
	</ul>
	<h1>Iniciar sesión</h1>
	<form method="POST" action="Login">
		<p>Correo</p>
		<input name="correo" type="email">
		<p>Contraseña</p>
		<input name="paswd" type="password">
		<input type="submit" value="Iniciar sesión">
	</form>
	<%
	if(error != null){
		Tag pista = new Tag("p",error,true,true);
		pista.prepararAtributos();
		pista.addAtributo("class", "error");
		out.print(pista.toString());
	}
	%>
</body>
</html>