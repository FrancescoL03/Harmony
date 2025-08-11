package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;
import model.Utente;


public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategoriaDAO categoriaDAO = new CategoriaDAO();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categoria> categorie = categoriaDAO.doRetrieveAll();
		request.setAttribute("categorie", categorie);
		super.service(request, response);
	}
	
	protected void checkAdmin(HttpServletRequest request) throws ServletException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente == null || !utente.getPermessi()) {
			throw new MyServletException("Utente non autorizzato");
		}
	}

}