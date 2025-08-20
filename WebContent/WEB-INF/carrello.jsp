<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c"
   uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../components/header.jsp" />

	<div class="container">
		
		<div class="py-3">
			<h2>Carrello</h2>
		</div>
	  	
	  	<c:forEach items="${carrello.prodotti}" var="cart" varStatus="status">
	    	<div class="row">
	        	
	        	<div class="col-lg-6 text-center" id="product">
	            	<a href="Prodotto?id=<c:out value="${cart.prodotto.ID}"/>"><img class="img-fluid" src="assets/img/prodotti/<c:out value="${cart.prodotto.ID}"/>.jpg"></a>
	    		</div>
	    		
	    		<div class="col-lg-6 text-center" id="description">
					<h3><a href="Prodotto?id=<c:out value="${cart.prodotto.ID}"/>">${cart.prodotto.nome}</a></h3>
		        	<h3><b>Quantità selezionata:</b> <c:out value= "${cart.quantita}"/></h3>
		            
		            <c:choose>
		            	<c:when test="${cart.quantita == 1}">
		           			<h4>Prezzo: <c:out value="${cart.prodotto.prezzo}"/> &euro;</h4>
		            	</c:when>
		            	
		            	<c:otherwise>
		            		<h4>Prezzo singolo: <c:out value="${cart.prodotto.prezzo }"/> &euro;</h4>
		            		<h4>Prezzo totale prodotto: <c:out value="${cart.prezzoTot}"/> &euro;</h4>
		            	</c:otherwise>
		            </c:choose>
		            
		         	<hr>
		         	
		            <div class="text-left">
		            	<p>${cart.prodotto.descrizione}</p>
		         	</div>
		         	
		            <form action="Carrello" method="post" class="text-center py-3">
							<input type="hidden" name="prodottoID" value="${cart.prodotto.ID}">
							<input type="hidden" name="setNum" value="0">
							<input type="submit" class="btn btn-primary" id="myButton" value="Rimuovi">
					</form>
				</div>
			</div>
	      
	      <c:if test="${not status.last}"> <hr> </c:if>
	   </c:forEach>
	   
	   <c:if test="${empty carrello.prodotti}">
			<div class="text-center p-5">
				<h3>Nessun prodotto nel carrello.</h3>
			</div>
		</c:if>
		
		
		<c:if test="${carrello.prezzoTot gt 0 }">
			<hr>	
			<div class="text-center py-4">
				<h2>Totale carrello: <c:out value="${carrello.prezzoTot}"/>  &euro;</h2>
				<form action="Riepilogo" onsubmit="return checkIsLogged()" method="post">
					<input type="submit" id="myButton" class="btn btn-primary" value="Procedi al checkout">
					<input type="hidden" id="utente" name="utente" value="${utente}">
					<input type="hidden" name="carrello" value="${carrello}">
				</form>
			</div>
		</c:if>
	</div>

<%@include file="../components/footer.html"%>