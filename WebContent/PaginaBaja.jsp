<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Baja</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<ul id="navegacion">
		<li>
			<a id="logo" href="Principal?modo=diurno">
				<img src="imgs/logo.png">
			</a>
		</li>
	</ul>
	<a href="Baja?modo=nocturno">Modo nocturno</a>
	<h1>Darse de baja</h1>
	<form method="POST" action="Baja">
		<input type="hidden" name="modo" value="diurno">
		<input type="submit" value="Estoy seguro, dejame salír.">
	</form>
	<p>¿Seguro que quieres darte de baja? Se borrarán todos tus datos.</p>
</body>
</html>