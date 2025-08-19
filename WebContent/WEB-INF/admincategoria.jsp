<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="operazione"
	value="${param.rimuovi != null ? 'Rimozione' : (categoria == null ? 'Aggiungi' : 'Modifica')}" />

<jsp:include page="../components/header.jsp" />

<div class="container">

	<div class="py-3">
		<h2>${operazione} categoria</h2>
		<h5>${notifica}</h5>
	</div>

	<c:if test="${param.rimuovi == null}">
		<form action="AdminCategoria" method="post">
			<input type="hidden" name="id" value="${categoria.ID}">

			<div class="form-group row">
				<div class="col-lg-1">
					<label>Nome</label>
				</div>
				<div class="col-lg-11">
					<input type="text" class="form-control" name="nome"
						value="${categoria.nome}"><br>
				</div>
			</div>

			<div class="text-center mb-5">
				<button type="submit" class="btn" style="background-color: #eda15e; color: white; border-radius: 16px;"><c:out value="${operazione}" /></button>

				<c:if test="${categoria != null}">
					<button type="submit" class="btn" style="background-color: #eda15e; color: white; border-radius: 16px;" name="rimuovi" value="Rimuovi">Rimuovi</button>
				</c:if>
			</div>
		</form>
	</c:if>
</div>

<%@include file="../components/footer.html"%>