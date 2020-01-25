<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="aplicacion.vista.NavBar"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.vista.Navegacion"%>
<%@page import="aplicacion.vista.Tag"%>
<%!Usuario usuario = null; %>
<%!String resul; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IMC</title>
<link rel="stylesheet" href="css/styleNocturno.css">
</head>
<body>
	<%
	usuario = (Usuario) request.getAttribute("usuario");
	resul = (String) request.getAttribute("imc");
	%>
	<h1>Calcular IMC</h1>
	<form method="POST" action="Principal">
		<p>Peso (kg)</p>
		<input name="peso" step="0.01" type="number">
		<p>Altura (m)</p>
		<input name="altura" step="0.01" type="number">
		<input type="hidden" name="modo" value="nocturno">
		<input type="submit" value="Calcular">
	</form>
	<div>
	<%
	if(resul != null){
		out.print(new Tag("p","Tu índice de mása corporal es de "+resul,true,true).toString());
		float imc = Float.parseFloat(resul.replace(",","."));
		if(imc<18.5){
			out.print(new Tag("p","Al ser infeiror a 18,5 se considera peso insuficiente para tu estatura",true,true).toString());
		} else if(imc>= 18.5 && imc<=24.9){
			out.print(new Tag("p","Al estar entre 18,5 y 24,9 se considera un peso adecuado para tu estatura",true,true).toString());
		} else if(imc>=25 && imc<=26.9){
			out.print(new Tag("p","Al estar entre 25 y 26,9 se considera Sobrepeso de grado I",true,true).toString());
		} else if(imc>=27 && imc<=29.9){
			out.print(new Tag("p","Al estar entre 27 y 29,9 se considera Sobrepeso de grado II",true,true).toString());
		} else if(imc>=30 && imc<=34.9){
			out.print(new Tag("p","Al estar entre 30 y 34,9 se considera Obesidad de tipo I",true,true).toString());
		} else if(imc>=35 && imc<=39.9){
			out.print(new Tag("p","Al estar entre 35 y 39,9 se considera Obesidad de tipo II",true,true).toString());
		} else if(imc>=40 && imc<=49.9){
			out.print(new Tag("p","Al estar entre 40 y 49,9 se considera Obesidad de tipo III",true,true).toString());
		} else{
			out.print(new Tag("p","Al ser igual o superior a 50 se considera Obesidad de tipo IV",true,true).toString());
		}
	}
	%>
	</div>
	<%
	out.print(new Navegacion(usuario,"nocturno").toString());
	%>
	<a href="Principal?modo=diurno">Modo diurno</a>
</body>
</html>