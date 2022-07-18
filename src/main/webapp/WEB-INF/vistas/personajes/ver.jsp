<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<jsp:include page="/WEB-INF/vistas/template_superior.jsp"></jsp:include>


<div class="card">
  <h5 class="card-header">Personaje id: ${personaje.id}</h5>
  <div class="card-body">
    <h5 class="card-title">${personaje.nombre}</h5>
    <p class="card-text">edad: <fmt:formatNumber type="number" value="${personaje.edad}" /> </p>   
     <p class="card-text">peso: <fmt:formatNumber type="number" value="${personaje.peso}" /> </p> 
	<p class="card-text">Historia: ${personaje.historia}</p>
	<p class="card-text">Peliculas y series Asociadas: ${personaje.peliculasAsociadas}</p>	
 	<img src="/personajes/recuperar-foto-personaje/${personaje.id}">
  </div>
</div>


<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>