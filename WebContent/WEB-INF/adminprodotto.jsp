<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
	
<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (prodotto == null ? 'Aggiungi' : 'Modifica')}"/>
<jsp:include page="../components/header.jsp" />

<div class="container">
	
	<div class="py-3">
		<h2>${operazione} prodotto</h2>
		<h5>${notifica}</h5>
	</div>

	<c:if test="${param.rimuovi == null}">
		<form action="AdminProdotto" method="post" enctype="multipart/form-data">
			<input type="hidden" name="prodottoID" value="${prodotto.ID}">
			
			<div class="form-group row pb-4">
				<div class="col-lg-1">
					<label>Nome</label>
				</div>
				<div class="col-lg-11">
					<input type="text" class="form-control" name="nome" value="${prodotto.nome}">
				</div>
			</div>
			
			<div class="form-group row pb-4">
				<div class="col-lg-1">
					<label>Descrizione</label>
				</div>
				<div class="col-lg-11">
					<textarea name="descrizione" rows="4" class="form-control">${prodotto.descrizione}</textarea>
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-lg-1">
					<label>Prezzo</label>
				</div>
				<div class="col-lg-11">
					<input type="number" class="form-control" name="prezzo" value="${prodotto.prezzo}"> <br><br>
				</div>
			</div>
			
			<div class="form-group row pb-4">
				<div class="col-lg-1">
					<label>Categoria</label>
				</div>
				<div class="col-lg-11">
					<select name="categoria" class="form-control">
						<c:forEach items="${categorie}" var="categoria">
						<option value="${categoria.ID}">${categoria.nome}</option>	
						</c:forEach>
					</select>
				</div>
			</div>
			
			<c:if test="${operazione == 'Aggiungi' }">
			<div class="form-group row">
				<div class="col-lg-1">
					<label>Immagine</label>
				</div>
				<div class="col-lg-11">
					<input type="file" name="immagine">
				</div>
				
			</div>
			</c:if>
			
			<div class="text-center mb-5">
				<button type="submit" class="btn" style="background-color: #eda15e; color:white; border-radius: 16px;" value="${operazione}"><c:out value="${operazione}"/></button>
				
				<c:if test="${prodotto != null}">
					<button type="submit" class="btn" style="background-color: #eda15e; color:white; border-radius: 16px;" name="rimuovi" value="Rimuovi">Rimuovi</button>
				</c:if>
			</div>
		</form>
	</c:if>

</div>

<%@include file="../components/footer.html"%>