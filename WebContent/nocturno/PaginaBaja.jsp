<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Baja</title>
<link rel="stylesheet" href="css/styleNocturno.css">
</head>
<body>
	<h1>Darse de baja</h1>
	<form method="POST" action="Baja">
		<input type="hidden" name="modo" value="nocturno">
		<input type="submit" value="Estoy seguro, dejame salír.">
	</form>
	<p>¿Seguro que quieres darte de baja? Se borrarán todos tus datos.</p>
	<ul id="navegacion">
		<li>
			<a href="Principal?modo=nocturno">
				<img src="imgs/logo.png">
			</a>
		</li>
	</ul>
	<a href="Baja?modo=diurno">Modo diurno</a>
</body>
</html>