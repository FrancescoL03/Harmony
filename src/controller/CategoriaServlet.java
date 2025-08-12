package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.Prodotto;
import model.ProdottoDAO;


@WebServlet("/Categoria")
public class CategoriaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Categoria> categorie = (List<Categoria>) request.getAttribute("categorie");
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("categoria", categorie.stream().filter(c -> c.getID() == id).findAny().get());

		List<Prodotto> prodotti = prodottoDAO.doRetrieveByCategoria(id, 0, 20);
		request.setAttribute("prodotti", prodotti);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/categoria.jsp");
		requestDispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}