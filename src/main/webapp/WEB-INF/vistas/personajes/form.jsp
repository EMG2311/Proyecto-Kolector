<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/WEB-INF/vistas/template_superior.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#form-personaje').validate();
		});
	</script>

	<h1>Formulario de personaje</h1>

	<form:form method="post" action="/personajes/guardar" modelAttribute="personajeForm" id="form-personaje" enctype="multipart/form-data">

		<div class="form-group">
			<label>Id</label>
			<form:input path="idPersonaje" readonly="true" cssClass="form-control"/>
		</div>


		<div class="form-group">
			<label>Nombre</label>
			<form:input path="nombre" cssClass="form-control required" />
			<form:errors path="nombre" cssClass="error"/>
		</div>

		<div class="form-group">
			<label>edad</label>
			<form:input path="edad" cssClass="form-control required number" />
			<form:errors path="edad" cssClass="error"/>
		</div>

		<div class="form-group">
			<label>Peso</label>
			<form:input path="peso" cssClass="form-control required number" />
			<form:errors path="peso" cssClass="error"/>
		</div>
		<div class="form-group">
			<label>Peliculas asociadas</label>
			<form:input path="peliculasAsociadas" cssClass="form-control required" />
			<form:errors path="peliculasAsociadas" cssClass="error"/>
		</div>
		<div class="form-group">
			<label>Historia</label>
			<form:input path="historia" cssClass="form-control required" />
			<form:errors path="historia" cssClass="error"/>
		</div>	

		<div class="form-group">
			<label>Foto Personaje</label>
			<input type="file" name="fotoPersonaje" class="form-control">
		</div>
		

		<button type="submit" class="btn btn-primary">Enviar datos</button>
	</form:form>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>