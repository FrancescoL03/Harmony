<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c"
   uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../components/header.jsp" />

	<div class="container">
		<br>
	
		<h2>Il mio profilo</h2>
		<h5>${notifica}</h5>
		<br>
			<div class="row pb-4">
				<label for="nome" class="col-sm-1 col-form-label">Nome</label>
    			<div class="col-sm-11">
      				<input type="text" class="form-control" id="nome" name="nome" value="${utente.nome}" readonly>
    			</div>
			</div>
			
						
			<div class="row pb-4">
				<label for="cognome" class="col-sm-1 col-form-label">Cognome</label>
    			<div class="col-sm-11">
      				<input type="text" class="form-control" id="cognome" name="cognome" value="${utente.cognome}" readonly>
    			</div>
			</div>
			
			<div class="row pb-4">
				<label for="email" class="col-sm-1 col-form-label">Email</label>
    			<div class="col-sm-11">
      				<input type="text" class="form-control" id="email" name="email" value="${utente.email}" readonly>
    			</div>
			</div>
			
			<div class="row pb-4">
				<label for="dataDiNascita" class="col-sm-1 col-form-label">Data di nascita</label>
    			<div class="col-sm-11">
      				<input type="text" id="dataDiNascita" name="dataDiNascita" class="form-control" value="${utente.dataDiNascita}" readonly>
    			</div>
			</div>
			
			<div class="row pb-4">
				<label for="sesso" class="col-sm-1 col-form-label">Sesso</label>
    			<div class="col-sm-11">
      				<input type="text" id="sesso" name="sesso" class="form-control" value="<%=request.getAttribute("sesso")%>" readonly>
    			</div>
			</div>
			
			<div class="pb-5 text-center">
				<form action="ProfiloModifica" method="post">
					<input type="hidden" name="utenteID" value="${utente.ID}">
					<input type="hidden" name="utente" value="${utente}">
					<input type="submit" class="btn btn-primary" id="myButton" name="modifica" value="Modifica">
					<input type="submit" class="btn btn-primary" id="myButton" name="rimuovi" value="Elimina account">
				</form>
			</div>
			
			<div class="text-center">
				<form action=".">
					<input type="hidden" value="${utente.ID}" name="utenteID">
					<button type="submit" class="btn btn-primary" id="myButton">Indietro</button>
				</form>
			</div>
			
			<br>		
	</div>

<%@include file="../components/footer.html"%>