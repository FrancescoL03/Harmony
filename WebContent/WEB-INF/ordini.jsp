<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c"
   uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../components/header.jsp"/>
 
	<div class="container">
		
		<c:if test="${not empty ordini}">
		<h2 class="center my-2">I miei ordini</h2>
		
		<div class="table-responsive">
		<table class="table table-striped table-hover my-5">
			<thead>
				<tr>
					<th>#</th>
					<th>Utente</th>
					<th>Data</th>
					<th>Prezzo</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ordini}" var="ordine">
					<tr>
						<th>
							<form action="OrdineContenuto" method="post">
    							<button type="submit" name="ordineID" value="${ordine.ID}" class="btn btn-link" style="color: #eda15e; margin: 0; padding: 0;">${ordine.ID}</button>
							</form>
						</th>
						<td><c:out value="${utente.nome}"/></td>
						<td><c:out value="${ordine.data}"/></td>
						<td><c:out value="${ordine.prezzoTotale}"/>&euro;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		
		</c:if>
		
		<c:if test="${empty ordini}">
			<div class="container text-center p-5">
				<h3>Non ci sono ordini effettuati.</h3>
			</div>
		</c:if>
	</div>
 
<%@include file="../components/footer.html"%>