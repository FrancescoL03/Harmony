<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%><%@taglib prefix="c"
   uri="http://java.sun.com/jsp/jstl/core"%>
   
<jsp:include page="../components/header.jsp" />
<div class="container">
   
   <c:choose>
   		<c:when test="<%=request.getParameter(\"q\") == \"\" %>">
			<div class="text-center p-5">
				<h3>Inserire un nome di un prodotto da cercare.</h3>
			</div>
		</c:when>
   		
   		<c:otherwise>
   			<br>
		   <h3>Hai cercato: <b> <em> <%=request.getParameter("q")%> </em> </b> </h3>
		   <br>
   
		   <div class="row">
		      <c:forEach items="${prodotti}" var="prodotto">
						<div class="col">
							<div class="card mb-5 bg-white rounded" style="width: 18rem; margin: 0 auto; float: none; margin-bottom: 10px; height: 700px;">
								<div class="card-header text-center">
									<h5><a href="Prodotto?id=<c:out value="${prodotto.ID}"/>"><c:out value="${prodotto.nome}" /></a></h5>						
								</div>
								<div class="card-body p-3">
									<a href="Prodotto?id=<c:out value="${prodotto.ID}"/>"><img class="img-fluid" style="max-height: 550px" src="assets/img/prodotti/<c:out value="${prodotto.ID}"/>.jpg"></a>
								</div>
								<div class="card-footer text-center">
									<h6>Prezzo: <c:out value="${prodotto.prezzo}" /> &euro; </h6>
								</div>
							</div>
						</div>        	
		        	</c:forEach>
		   </div>
		   
		   <c:if test="${empty prodotti}">
				<div class="text-center p-5">
					<h3>Siamo spiacenti. Non abbiamo prodotti con questo nome.</h3>
				</div>
   			</c:if>
   		</c:otherwise>
   </c:choose>
   
   
</div>
<%@include file="../components/footer.html"%>