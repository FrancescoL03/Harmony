<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
	
<jsp:include page="../../components/header.jsp">
	<jsp:param name="pageTitle" value="Errore ${requestScope['javax.servlet.error.status_code']}"/>
</jsp:include>

	<div class="container p-5 text-center">
		<h1>Errore ${requestScope['javax.servlet.error.status_code']}</h1>
		<h4>${requestScope['javax.servlet.error.exception']}</h4>
	</div>
	
<jsp:include page="../../components/footer.html"/>