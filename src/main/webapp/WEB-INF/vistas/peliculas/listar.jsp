<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/WEB-INF/vistas/template_superior.jsp"></jsp:include>

<h1>Listado de peliculas</h1>

<script type="text/javascript">
	$(document).ready(function() {
		$('.btn-borrar').on('click', function(event) {
			event.preventDefault();
			var hrefOriginal = $(this).attr('href');
			bootbox.confirm("Borramos  la pelicula?", function(result){ 
				if(result) {
					window.location = hrefOriginal;
				}
			});
			
		});
	});	
</script>
<table class="table table-striped table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>Id</th>
			<th>Titulo</th>
			<th>Fecha de creacion</th>
			<th>Calificacion</th>
			<th></th>
		</tr>
	</thead>
	<c:forEach items="${peliculas}" var="p">
		<tr>
			<td>${p.idPelicula}</td>
			<td>${p.titulo}</td>
			<td>${p.fechaCreacion}</td>
			<td>${p.calificacion}</td>
			<td>
				<a href="/peliculas/${p.idPelicula}" class="btn btn-primary">Ver</a>&nbsp;
				<a href="/peliculas/${p.idPelicula}/editar" class="btn btn-success">Editar</a>&nbsp;
				<a href="/peliculas/${p.idPelicula}/borrar" class="btn btn-danger btn-borrar">Borrar</a></td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>