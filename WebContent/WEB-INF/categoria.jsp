<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%><%@taglib prefix="c"
   uri="http://java.sun.com/jsp/jstl/core"%>
   
<!-- HEADER -->   
<jsp:include page="../components/header.jsp" />

<!-- BODY -->
	<div class="container">
	
		<div class="py-3">
			<h2>${categoria.nome}</h2>
			
			<c:if test="${utente.permessi}">
				<form action="AdminCategoria" method="post">
					<input type="hidden" name="id" value="${categoria.ID}">
					<input type="submit" class="btn btn-primary" id="myButton" value="Modifica">
					<input type="submit" class="btn btn-primary" id="myButton" name="rimuovi" value="Rimuovi">
				</form>
			</c:if>
		</div>
		
		<c:forEach items="${prodotti}" var="prodotto" varStatus="status">
	    	<div class="row">
	    	 
		    	<div class="col-lg-6 text-center" id="product">
					<a href="Prodotto?id=${prodotto.ID}"><img class="img-fluid" src="assets/img/prodotti/${prodotto.ID}.jpg" alt=""></a>
				</div>
		         
				<div class="col-lg-6 text-center"id="description">
					<h3><a href="Prodotto?id=${prodotto.ID}">${prodotto.nome}</a></h3>
		         	<div class="text-left">
						<p>${prodotto.descrizione}</p>
		         	</div>
		            <br>
		            <h4>Prezzo:</h4>
		            <h5>${prodotto.prezzo}&euro;</h5>
		            <div class="py-3">
						<form action="Carrello">
							<input type="hidden" name="prodottoID" value="${prodotto.ID}">
							<input type="hidden" name="quantita" value="1">
		        			<input type="submit" class="btn btn-primary" style="background-color: #eda15e; border: none;" value="Aggiungi al carrello">
		      			</form>
					</div>
				</div>
			</div>
	      
		<c:if test="${not status.last}"> <hr> </c:if>
	      
		</c:forEach>
	   
		<c:if test="${empty prodotti}">
			<div class="text-center p-5">
				<h3>Nessun prodotto nel carrello.</h3>
			</div>
		</c:if>
	</div>

<!-- FOOTER -->
<%@include file="../components/footer.html"%>