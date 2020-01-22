<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<ul id="navegacion">
		<li>
			<a href="Principal">
				<img src="imgs/logo.png">
			</a>
		</li>
	</ul>
	<h1>Registrate</h1>
	<form enctype="multipart/form-data" method="POST" action="Registro">
		<p>Nombre de usuario</p>
		<input name="nombre" type="string" required="">
		<p>Correo</p>
		<input name="correo" type="email" required="">
		<p>Contraseña</p>
		<input name="paswd" type="password" required="">
		<p>Foto de perfil</p>
		<input name="avatar" type="file" accept="image/png,image/jpeg,image/jpg">
		<input type="submit" value="Registrarse">
	</form>
</body>
</html>