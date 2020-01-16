<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IMC</title>
</head>
<body>
	<ul id="navegacion">
		<li>
			<a href="Principal">
				<img src="imgs/logo.png">
			</a>
		</li>
		<li>
			<a href="Login">Login</a>
		</li>
		<li>
			<a href="Registro">Registro</a>
		</li>
	</ul>
	<h1>Calcular IMC</h1>
	<form method="POST" action="Principal">
		<p>Peso</p>
		<input name="peso" step="0.01" type="number">
		<p>Altura</p>
		<input name="altura" step="0.01" type="number">
		<input type="submit" value="Calcular">
	</form>
</body>
</html>