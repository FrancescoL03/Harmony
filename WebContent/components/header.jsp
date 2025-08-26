<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib prefix="c"
  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Harmony</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="assets/img/logo.png" type="image/jpg" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/css/myCSS.css">
    <link rel="stylesheet" href="assets/css/footer.css">
  </head>
  
  <body>
  	<!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #eda15e;">
      <div class="container">
        <a style="color: white;" class="navbar-brand" href="."><img id="logo" src="assets/img/logo.png"><b>Harmony</b></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <!-- Vari link in navbar (prima parte)-->
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="."><i class="fas fa-home"></i> Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fas fa-link"></i> Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-folder"></i> Categorie</a>
              <div class="dropdown-menu px-3" aria-labelledby="navbarDropdown">
              	<!-- Categorie -->
              	<div class="dropdown-header text-center">Lista categorie</div>
                <c:forEach items="${categorie}" var="categoria">
                  <a class="dropdown-item text-center" href="Categoria?id=<c:out value="${categoria.ID} "/>">
	                 <button class="btn btn-link" role="link"><c:out value="${categoria.nome}" /></button>
				  </a>
                </c:forEach>
                <!-- Fine categorie -->
              </div>
            </li>
          </ul>
          <!-- Fine vari link (prima parte) -->
          
          <!-- Ricerca -->
          <form action="Ricerca" class="form-inline my-2 my-lg-0" onkeyup="loadSuggerimenti(this.q.value)">
            <input type="text" style="border-radius: 10px" class="form-control mr-sm-2" list="product" placeholder="Search" aria-label="Search" name="q" autocomplete="off">
            <datalist id="product">
            </datalist>
            <button style="border-radius: 10px" class="btn btn-light my-2 my-sm-0" type="submit">Search</button>
          </form>
          <!-- Fine ricerca -->
          
          <c:choose>
          <c:when test="${utente == null}">
	      	<a href=""class="btn btn-default btn-rounded myUserButton" data-toggle="modal" data-target="#LoginModal"><i class="fas fa-sign-in-alt"></i> Login</a>
	      	
	      	<div class="modal fade" id="LoginModal" role="dialog">
            	<div class="modal-dialog">
            	
                    <div class="modal-content">
                        
                        <div class="modal-header">
                            <h4 class="modal-title">Login</h4>
                        	<button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        
                        <div class="modal-body">
                        <form action="Login">
                        	<div class="form-row mb-4">
                        		<div class="col-sm-1"><i class="fas fa-envelope p-2"></i></div>
                        		<div class="col-sm-11"><input type="text" class="form-control" placeholder="Email..." name="email"></div>
                        	</div>
                        	
                        	<div class="form-row mb-4">
                        		<div class="col-sm-1"><i class="fas fa-key p-2"></i></div>
                        		<div class="col-sm-11"><input type="password" class="form-control" placeholder="Password..." name="password"></div>
                        	</div>
                        	
                        	<div class="text-center mt-1">
                                <input type="submit" class="btn btn-info" value="Login" style="background-color: #eda15e; border: none;">
                            </div>
                        </form>
                        </div>
                        
                        <div class="modal-footer" style="display:inline-block;">
                        	<p>Non hai un account? <a href="RegistrazioneForm">Registrati!</a></p>
                        	<div style="text-align: right;">
                        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
			
          </c:when>
            
            <c:otherwise>
          	<ul class="navbar-nav myUserButton">
          	<li>
              <div class="dropdown">
                <a class="dropdown-toggle nav-link" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user-alt"></i> ${utente.nome} </a>
                <div class="dropdown-menu px-3">
                
                  <c:if test="${utente.permessi == true}">
                    <div class="dropdown-header text-center">Admin Panel</div>
                    
                    <div class="dropdown-item text-center">
                    	<form action="AdminProdotto">
                    		<button class="btn btn-link" role="link">Aggiungi prodotto</button>
                    	</form>
                    </div>
                    
                    <div class="dropdown-item text-center">
                    	<form action="AdminCategoria">
                    		<button class="btn btn-link" role="link">Aggiungi categoria</button>
                    	</form>
                    </div>
                    
                    <div class="dropdown-item text-center">
                    	<form action="AdminUtenti">
                    		<button class="btn btn-link" role="link">Gestione utenti</button>
                    	</form>
                    </div>
                    
                    <hr>
                  </c:if>
                  
                  <div class="dropdown-item text-center">
                  		<form action="Ordini" method="post">
                  			<input type="hidden" value="${utente.ID}" name="utenteID">
                  			<button type="submit" class="btn btn-link" role="link">I miei ordini</button>
                  		</form>
                  </div>
                  
                  <div class="dropdown-item text-center">
                  		<form action="ProfiloUtente" method="post">
                  			<input type="hidden" value="${utente.ID}" name="utenteID">
                  			<button type="submit" class="btn btn-link" role="link">Il mio profilo</button>
                  		</form>
                  </div>
                  
                  <div class="dropdown-item text-center">
                  		<form action="Logout">
                      		<button type="submit" class="btn btn-light" ><i class="fas fa-sign-out-alt"></i> Logout</button>
                    	</form>
				  </div>
                </div>
              </div>
              </li>
              </ul>
            </c:otherwise>
          </c:choose>
          <!-- Vari link (seconda parte) -->
          <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a href="Carrello" class="nav-link"><i class="fas fa-shopping-cart"></i> Carrello</a></li>
          </ul>
          <!-- FIne vari link (seconda parte) -->
        </div>
      </div>
    </nav>
    <!-- Fine navbar -->
    <h1 id="test"></h1>