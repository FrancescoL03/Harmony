<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../components/header.jsp" />
	<div class="container">
    	<div class="row">
    		
    		<div class="col-lg-6 text-center py-3" id="product">
         		<a href="Prodotto?id=${prodotto.ID}"><img class="img-fluid" src="assets/img/prodotti/${prodotto.ID}.jpg" alt=""></a>
         	</div>
         	
    		<div class="col-lg-6 text-center pt-4 pb-5" id="description">
      			
      			<h3><a href="#">${prodotto.nome}</a></h3>
     			<div class="text-left">
            		<p>${prodotto.descrizione}</p>
         		</div>
      			<h4>Prezzo:</h4>
      			<h5>${prodotto.prezzo}&euro;</h5>
      			<h4>Quantità:</h4>
      			
      			<div class="pb-4">
	      			<form action="Carrello">
	        			<div class="pb-4">
		        			<select name="quantita">
		            			<c:forEach begin="1" end="15" varStatus="count">
		            				<option value="${count.index}">${count.index}</option>
		            			</c:forEach>
		        			</select>
		        		</div>
	        			<input type="hidden" name="prodottoID" value="${prodotto.ID}">
	        			<input type="submit" class="btn btn-primary" id="myButton" value="Aggiungi al carrello">
	      			</form>
	      		</div>
      			<c:if test="${utente.permessi}">
      				<div class="pb-5">
						<form action="AdminProdotto" method="post">
							<input type="hidden" name="prodottoID" value="${prodotto.ID}">
							<input type="submit" class="btn btn-primary" id="myButton" name="modifica" value="Modifica">
							<input type="submit" class="btn btn-primary" id="myButton" name="rimuovi" value="Rimuovi">
						</form>
					</div>
				</c:if>
    		</div>
  		</div>
  	</div>
<%@include file="../components/footer.html"%>