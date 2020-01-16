<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
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
</body>
</html>