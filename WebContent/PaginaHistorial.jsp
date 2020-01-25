<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="aplicacion.vista.NavBar"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.vista.Navegacion"%>
<%@page import="aplicacion.vista.Tag"%>
<%@page import="aplicacion.vista.Tabla"%>
<%@page import="aplicacion.vista.ObtenerTabla"%>
<%!Usuario usuario = null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Historial</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
	usuario = (Usuario) request.getAttribute("usuario");
	%>
	<%
	out.print(new Navegacion(usuario,"diurno").toString());
	%>
	<a href="Historial?modo=nocturno">Modo nocturno</a>
	<h1>Historial</h1>
	<%
	if(usuario != null){
		Tabla tabla = ObtenerTabla.crearTablaHistorial(usuario);
		if(tabla == null){
			out.print(new Tag("p", "No has realizado ningún calculo todavía", true, true));
		} else{
			out.print(tabla.toString());
		}
	}
	%>
</body>
</html>