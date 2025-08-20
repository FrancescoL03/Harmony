<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c"
   uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
   

<jsp:include page="../components/header.jsp" />

<div class="container">
<br>
	<h2>Riepilogo:</h2>
	<br>
	<form action="Checkout" method="post" name="checkout">
		<input type="hidden" value="${utente.ID}" name="utenteID">
	
		<div class="form-group row pb-4">
			<label for="nome" class="col-sm-1 col-form-label">Nome:</label>
    		<div class="col-sm-11">
      			<input type="text" class="form-control" name="nome" id="nome" value="${utente.nome}" disabled>
    		</div>
		</div>
		
		<div class="form-group row pb-4">
			<label for="cognome" class="col-sm-1 col-form-label">Cognome:</label>
    		<div class="col-sm-11">
      			<input type="text" class="form-control" name="cognome" id="cognome" value="${utente.cognome}" disabled>
    		</div>
		</div>
		
		<div class="form-group row pb-2">
			<label for="prodottiAcquistati" class="col-sm-1 col-form-label">Prodotti acquistati:</label>
    		<div class="col-sm-11">
				<c:forEach items="${carrello.prodotti}" var="cart">
					<input class="form-control" id="prodottiAcquistati" type="text" value="${cart.quantita}x ${cart.prodotto.nome}: ${cart.prezzoTot}&euro;" name="nome" disabled>
					<br>
				</c:forEach>
    		</div>
		</div>
		
		<div class="form-group row pb-4">
			<label for="metodoDiPagamento" class="col-sm-1 col-form-label">Tipo carta:</label>
    		<div class="col-sm-11">
				<select name="metodoPagamento" id="metodoDiPagamento" class="form-control">
					<option value="Visa">Visa</option>
					<option value="MasterCard">MasterCard</option>
					<option value="Maestro">Maestro</option>
				</select>
    		</div>
		</div>
		
		<div class="form-group row pb-4">
			<label for="numeroDiCarta" class="col-sm-1 col-form-label">Numero carta:</label>
    		<div class="col-sm-11">
				<input type="text" id="numeroDiCarta" class="form-control" name="numeroDiCarta" oninput="validaCarta()">
    		</div>
		</div>
		
		<div class="form-group row pb-4">
			<label for="indirizzo" class="col-sm-1 col-form-label">Indirizzo:</label>
    		<div class="col-sm-11">
				<input type="text" class="form-control" id="indirizzo" name="indirizzo" placeholder="Città, Via/Viale/Piazza 'nomeVia' (numero)" oninput="validaIndirizzo()">
    		</div>
		</div>
		
		<div class="form-group row pb-4">
			<label for="telefono" class="col-sm-1 col-form-label">Telefono:</label>
    		<div class="col-sm-11">
				<input type="text" name="telefono" id="telefono" class="form-control" oninput="validaTelefono()">
    		</div>
		</div>
		
		<fmt:parseNumber var ="tot" type="number" value="${carrello.prezzoTot}"/>	
		<c:choose>
			<c:when test="${tot gt 999}">
				<div class="form-group row pb-4">
		    		<div class="col">		    		
						<h5 style="color: #2ecc71">Il tuo ordine supera i 999&euro; e hai diritto alla spedizione gratuita!</h5>
						<input type="hidden" name="costoSpedizione" id="costoSpedizione" class="form-control" value="${costoSpedizione}" readonly>
		    		</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="form-group row pb-4">
					<label for="costoSpedizione" class="col-sm-1 col-form-label">Costo spedizione:</label>
		    		<div class="col-sm-11">
						<input type="text" name="costoSpedizione" id="costoSpedizione" class="form-control" value="${costoSpedizione}" readonly>
		    		</div>
				</div>
			</c:otherwise>
		</c:choose>
		
		<div class="form-group row pb-4">
			<label for="prezzoTotale" class="col-sm-1 col-form-label">Totale ordine:</label>
		    <div class="col-sm-11">
				<input type="text" name="prezzoTotale" id="prezzoTotale" class="form-control" value="${carrello.prezzoTot + costoSpedizione}" readonly>
		    </div>
		</div>
		
		<div class="text-center">
			<button type="submit" class="btn btn-primary" name="paga" value="Paga" style="background-color: red; border-color:red;" disabled>Paga</button>
			<br>
			<span id="pagaMessaggio">Assicurati di compilare tutti i campi correttamente</span>
		</div>
	</form>
	<br>
</div>
<%@include file="../components/footer.html"%>