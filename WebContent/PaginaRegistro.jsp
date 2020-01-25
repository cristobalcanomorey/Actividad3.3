<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="aplicacion.vista.Tag"%>
<%!String error = null; %>
<%!String enviado; %>
<%!static final String FALTAN_DATOS = "1"; %>
<%!static final String USUARIO_EXISTE = "2"; %>
<%!static final String ERROR_CORREO = "3"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
	error = (String) request.getAttribute("error");
	enviado = (String) request.getAttribute("enviado");
	if (error != null) {
		if (error.equals(FALTAN_DATOS)) {
			error = "No puedes dejar campos en blanco";
		} else if (error.equals(USUARIO_EXISTE)) {
			error = "Ya existe un usuario con ese correo electrónico";
		} else if (error.equals(ERROR_CORREO)) {
			error = "No te hemos podido mandar el correo de validación, comprueba que has puesto un correo que exista e intentalo de nuevo o vuelve a intentarlo más tarde";
		}
	}
	%>
	<ul id="navegacion">
		<li>
			<a id="logo" href="Principal?modo=diurno">
				<img src="imgs/logo.png">
			</a>
		</li>
	</ul>
	<a href="Registro?modo=nocturno">Modo nocturno</a>
	<h1>Registrate</h1>
	<form enctype="multipart/form-data" method="POST" action="Registro">
		<p>Nombre de usuario</p>
		<input name="nombre" type="text" required="">
		<p>Correo</p>
		<input name="correo" type="email" required="">
		<p>Contraseña</p>
		<input name="paswd" type="password" required="">
		<p>Foto de perfil</p>
		<input name="avatar" type="file" accept="image/png,image/jpeg,image/jpg">
		<input type="hidden" name="modo" value="diurno">
		<input type="submit" value="Registrarse">
	</form>
	<%
	if(error != null){
		Tag pista = new Tag("p",error,true,true);
		pista.prepararAtributos();
		pista.addAtributo("class", "error");
		out.print(pista.toString());
	} else if(enviado != null){
		Tag resultado = new Tag("h2", "Te hemos enviado un correo para validar tu cuenta", true, true);
		Tag volver = new Tag("a", "Volver a la página principal", true, true);
		volver.prepararAtributos();
		volver.addAtributo("href", "Principal");
		out.print(resultado.toString());
		out.print(volver.toString());
	}
	%>
</body>
</html>