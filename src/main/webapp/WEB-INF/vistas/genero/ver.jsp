<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<jsp:include page="/WEB-INF/vistas/template_superior.jsp"></jsp:include>


<div class="card">
  <h5 class="card-header">PeliculaSerie id: ${pelicula.idPelicula}</h5>
  <div class="card-body">
    <h5 class="card-title">${pelicula.titulo}</h5>
    <p class="card-text">calificacion: <fmt:formatNumber type="number" value="${pelicula.calificacion}" /> </p>   
     <p class="card-text">Fecha de creacion:<fmt:formatNumber type="date" value="${pelicula.fechaCreacion}" /> </p>
 	<img src="/peliculas/recuperar-foto-personaje/${pelicula.idPelicula}">
  </div>
</div>


<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>