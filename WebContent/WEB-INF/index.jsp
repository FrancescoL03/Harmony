<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
	
<jsp:include page="../components/header.jsp" />
	
<div class="container-fluid">
		<div class=" text-center my-5">
	        <h1 class="mt-4">Welcome in <a href="." style="text-decoration: none; color: #eda15e;"><em>Harmony!</em></a></h1>
	        <p class="mb-4">The biggest e-commerce site for <i class="fas fa-music"></i> music <i class="fas fa-music"></i></p>
		</div>
        
         <jsp:include page="../components/carousel.jsp"/>
         
        <h2 class="text-center mt-3 py-3">Prodotti in evidenza:</h2>
        
        <div class="row justify-content-md-center">
	        <div class="row justify-content-md-center" style="width:80%">
	        	<c:forEach items="${prodotti}" var="prodotto">
					<div class="col">
						<div class="card px-0 mb-5 bg-white rounded" style="float: none; margin-bottom: 10px; height: 700px;">
							<div class="card-header text-center">
								<h5 class="m-0"><a href="Prodotto?id=<c:out value="${prodotto.ID}"/>"><c:out value="${prodotto.nome}" /></a></h5>						
							</div>
							<div class="card-body p-3">
								<a href="Prodotto?id=<c:out value="${prodotto.ID}"/>"><img class="img-fluid" style="max-height: 550px" src="assets/img/prodotti/<c:out value="${prodotto.ID}"/>.jpg"></a>
							</div>
							<div class="card-footer text-center">
								<h6 class="m-0">Prezzo: <b><c:out value="${prodotto.prezzo}" /> &euro; </b></h6>
							</div>
						</div>
					</div>        	
	        	</c:forEach>
	        </div>
       	</div>
       	<br>
       	
       	<div class="map-responsive text-center">
       		<h4>Vieni a trovarci in negozio!</h4>
       		<h6>Ci trovi proprio qui.</h6>	
			<iframe width="80%" height="500px" src="https://www.google.com/maps/embed/v1/streetview?location=40.612610,15.051894&fov=80&heading=70&pitch=0&key=AIzaSyDAMTRDZduSUAj8jc6cTbMMO5q6j2HYHrs" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""></iframe>
		
		</div>
		<br>
		
		<div class="container">
			<hr>
		</div>
       	
       	<div class="text-center pb-4">
       		<h5><em>Senza musica la vita sarebbe un errore.</em> <br> <small class="text-muted">(Friedrich Nietzsche)</small></h5>
       	</div>
</div>

<%@include file="../components/footer.html" %>       	