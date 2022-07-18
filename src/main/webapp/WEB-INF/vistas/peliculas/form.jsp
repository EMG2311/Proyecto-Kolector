<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/WEB-INF/vistas/template_superior.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#form-personaje').validate();
		});
	</script>

	<h1>Formulario de peliculas</h1>

	<form:form method="post" action="/peliculas/guardar" modelAttribute="peliculaForm" id="form-pelicula" enctype="multipart/form-data">

		<div class="form-group">
			<label>Id</label>
			<form:input path="idPelicula" readonly="true" cssClass="form-control"/>
		</div>


		<div class="form-group">
			<label>Titulo</label>
			<form:input path="titulo" cssClass="form-control required" />
			<form:errors path="titulo" cssClass="error"/>
		</div>

		<div class="form-group">
			<label>Calificacion</label>
			<form:input type="number" max="5" min="0" path="calificacion" cssClass="form-control required number" />
			<form:errors path="calificacion" cssClass="error"/>
		</div>

		<div class="form-group">
			<label>Fecha de creacion</label>
			<form:input type="date" path="fechaCreacion" cssClass="form-control required number" />
			<form:errors path="fechaCreacion" cssClass="error"/>
		</div> 
		<div class="form-group">
			<label>Personajes Asociados</label>
			<form:input type="list"  path="personajeAsociados" cssClass="form-control required" />
			<form:errors path="personajeAsociados" cssClass="error"/>
		</div>


		<div class="form-group">
			<label>Foto de la Pelicula</label>
			<input type="file" name="fotoPelicula" class="form-control">
		</div>
		

		<button type="submit" class="btn btn-primary">Enviar datos</button>
	</form:form>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>