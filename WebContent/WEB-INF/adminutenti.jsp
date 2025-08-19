<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c"
   uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../components/header.jsp" />

	<div class="container">
		
		<c:if test="${not empty utenti}">
			<h2 class="center my-2">Utenti</h2>
			
			<div class="table-responsive">
				<table class="table table-striped table-hover my-5">
					<thead>
						<tr class="text-center">
							<th>ID</th>
							<th>Nome</th>
							<th>Email</th>
							<th>Ordini</th>
							<th>Amministratore</th>
							<th>Azione</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${utenti}" var="utente">
							<tr class="text-center">
								<td><c:out value="${utente.ID}"/></td>
								<td><c:out value="${utente.nome}"/></td>
								<td><c:out value="${utente.email}"/></td>
								<td>
									<form action="Ordini" method="POST">
			                  			<input type="hidden" value="${utente.ID}" name="utenteID">
			                  			<button type="submit" class="btn btn-link p-0 m-0" role="link">Vai</button>
			                  		</form>
		                  		</td>
								<td><c:out value="${utente.permessi}"/></td>
								<td>
									<c:choose>
										<c:when test="${not utente.permessi}">
											<form action="AdminUtenteDelete" method="post">
					                  			<input type="hidden" value="${utente.ID}" name="utenteID">
					                  			<button type="submit" class="btn btn-link p-0 m-0" role="link">Rimuovi</button>
					                  		</form>
										</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>

<%@include file="../components/footer.html"%>