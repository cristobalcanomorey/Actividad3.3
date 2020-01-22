<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="aplicacion.vista.Tag"%>
<%@page import="aplicacion.vista.NavBar"%>
<%@ page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.modelo.ejb.UsuariosEJB"%>
<%! Usuario usuario = null; %>
<%!UsuariosEJB usuariosEJB; %>
<%!String resul; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IMC</title>
<link href="css/style.css">
</head>
<body>
	<%
		usuario = (Usuario) request.getAttribute("usuario");
		resul = (String) request.getAttribute("imc");
	%>
	<%
	NavBar navBar;
	if(usuario == null){
		navBar = new NavBar(null,null);
		navBar.addRegistro();
		navBar.addLogin();
	} else{
		Context context = null;
		try{
			context = new InitialContext();
			usuariosEJB = (UsuariosEJB) context.lookup("java:global/Actividad3.3/UsuariosEJB");
		}catch(Exception e){
			
		}
		navBar = new NavBar(usuario.getNombre(),UsuariosEJB.getRutaFotoCompleta(usuario));
		navBar.addLogout();
		navBar.addHistorial();
		navBar.addDarseDeBaja();
	}
	out.print(navBar.toString());
	%>
	<h1>Calcular IMC</h1>
	<form method="POST" action="Principal">
		<p>Peso</p>
		<input name="peso" step="0.01" type="number">
		<p>Altura</p>
		<input name="altura" step="0.01" type="number">
		<input type="submit" value="Calcular">
	</form>
	<div>
	<%
	if(resul != null){
		out.print(resul);
	}
	%>
	</div>
</body>
</html>