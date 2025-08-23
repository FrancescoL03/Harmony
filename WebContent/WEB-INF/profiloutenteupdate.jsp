<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"
  uri="http://java.sun.com/jsp/jstl/core"%>
  
<jsp:include page="../components/header.jsp" />

	<div class="container">
		<h1>Modifica account</h1>
		<h5>${notifica}</h5>
		<br>
		<form action="ProfiloUpdate" name="profiloModifica" method="post" autocomplete="off">
			<div class="form-group row  pb-4">
				<label for="nome" class="col-sm-1 col-form-label">Nome</label>
    			<div class="col-sm-11">
      				<input type="text" class="form-control" id="nome" name="nome" value="${utente.nome}" onkeyup="validaNomeMod()">
    			</div>
			</div>
			
						
			<div class="form-group row pb-4">
				<label for="cognome" class="col-sm-1 col-form-label">Cognome</label>
    			<div class="col-sm-11">
      				<input type="text" class="form-control" id="cognome" name="cognome" value="${utente.cognome}" onkeyup="validaCognomeMod()">
    			</div>
			</div>
			
			
			<div class="form-group row  pb-4">
				<label for="dataDiNascita" class="col-sm-1 col-form-label">Data di nascita</label>
    			<div class="col-sm-11">
      				<input type="date" id="dataDiNascita" name="dataDiNascita" class="form-control" placeholder="yyyy-mm-dd">
    			</div>
			</div>
			
			<div class="form-check form-check-inline  pb-4">	
			<div class="radio">
				<label class="radio-inline pr-3"><input type="radio" name="sesso" value="m" checked> Uomo </label>
			</div>
			<div class="radio">
				<label class="radio-inline px-3"><input type="radio" name="sesso" value="f"> Donna</label>
			</div>
			<div class="radio">
				<label class="radio-inline pl-3"><input type="radio" name="sesso" value="a"> Altro</label>
			</div>
			</div>			
			
			<div class="form-group row  pb-4">
				<label for="password" class="col-sm-1 col-form-label">Nuova password</label>
    			<div class="col-sm-11">
      				<input type="password" id="password" name="password" class="form-control" onkeyup="validaPasswordMod()" placeholder="Min. 8 caratteri, 1 maiuscola, 1 minuscola, 1 numero">
    			</div>
			</div>
			
			
			<div class="form-group row  pb-4">
				<label for="passwordConferma" class="col-sm-1 col-form-label">Conferma password</label>
    			<div class="col-sm-11">
      				<input type="password" id="passwordConferma" name="passwordConferma" class="form-control" onkeyup="validaPasswordMod()">
    			</div>
			</div>
			
			<div class="text-center">
				<button type="submit" class="btn btn-primary" style="background-color: red; border-color:red;" id="aggiornami" disabled>Aggiorna</button>	
			</div>
			<div class="text-center">
				<span id="aggiornamimessaggio">Verifica che tutti i campi siano in verde</span>			
			</div>
			
			<input type="hidden" value="${utente.ID}" name="utenteID">
			<br>
		</form>
	</div>
  
<%@include file="../components/footer.html"%>