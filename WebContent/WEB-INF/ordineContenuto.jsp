<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c"
   uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../components/header.jsp"/>
<div class="container">
	<h5 class="text-center my-4">Riepilogo:</h5>
	<table class="table table-striped table-hover mt-2 mb-5">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Utente</th>
					<th scope="col">Data</th>
					<th scope="col">Prezzo</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><c:out value="${ordine.ID }"/></th>
					<td><c:out value="${utente.nome}"/></td>
					<td><c:out value="${ordine.data}"/></td>
					<td><c:out value="${ordine.prezzoTotale}"/>&euro;</td>
				</tr>
			</tbody>
	</table>
		
	<hr>
	
	<h5 class="text-center">Prodotti acquistati:</h5>
	
	<c:forEach items="${prodottiOrdine}" var="prodottoQuantita" varStatus="status">
		<div class="row">
			<div class="col-lg-6 text-center" id="product">
				<div>
                  <a href="Prodotto?id=${prodottoQuantita.prodotto.ID}"><img class="img-fluid" src="assets/img/prodotti/${prodottoQuantita.prodotto.ID}.jpg" alt=""></a>
            	</div>
			</div>
			<div class="col-lg-6" id="description">
	            <h3><a href="Prodotto?id=${prodottoQuantita.prodotto.ID}">${prodottoQuantita.prodotto.nome}</a></h3>
	            <p>${prodottoQuantita.prodotto.descrizione}</p>
	            <br>
	            <h4>Quantità: ${prodottoQuantita.quantita}</h4>
	            <c:choose>
	            	<c:when test="${prodottoQuantita.quantita == 1}">
	           			<h5>Prezzo: <c:out value="${prodottoQuantita.prodotto.prezzo}"/>&euro;</h5>
	            	</c:when>
	            	<c:otherwise>
	            		<h5>Prezzo singolo: <c:out value="${prodottoQuantita.prodotto.prezzo }"/>&euro;</h5>
	            		<h5>Prezzo totale prodotto: <c:out value="${prodottoQuantita.prodotto.prezzo * prodottoQuantita.quantita}"/>&euro;</h5>
	            	</c:otherwise>
            	</c:choose>
	            <br>
            </div>
		</div>
		
		<c:if test="${not status.last}"> <hr> </c:if>
	
	</c:forEach>

		<hr>
	
	<div class="text-center mt-4">
		<h5>Totale: <c:out value="${ordine.prezzoTotale }"/> &euro;</h5>
	</div>	
	
	<br>	
	
	<c:choose>
		<c:when test="${utente.permessi}">
			<div class="text-center mb-3">
				<form action="AdminUtenti" method="post">
					<input type="hidden" value="${utente.ID}" name="utenteID">
					<button type="submit" class="btn btn-primary" id="myButton">Gestione utenti</button>
				</form>
				
				<br>
				
				<form action="Ordini" method="post">
					<input type="hidden" value="${utente.ID}" name="utenteID">
					<button type="submit" class="btn btn-primary" id="myButton">Indietro</button>
				</form>
			</div>
		</c:when>
		
		<c:otherwise>
			<div class="text-center mb-3">
				<form action="Ordini" method="post">
					<input type="hidden" value="${utente.ID}" name="utenteID">
					<button type="submit" class="btn btn-primary" id="myButton">Indietro</button>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
	
</div>
<%@include file="../components/footer.html"%>