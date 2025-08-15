package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;


@WebServlet("/Prodotto")
public class ProdottoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();
	private final CategoriaDAO categoriaDAO = new CategoriaDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Prodotto prodotto = prodottoDAO.doRetrieveById(id);
		request.setAttribute("prodotto", prodotto);
		
		if(prodotto==null) {
			throw new MyServletException("Il prodotto non esiste!");
		}
		
		List<Categoria> categorieProdotto = categoriaDAO.doRetrieveByProdotto(id);
		request.setAttribute("categorieProdotto", categorieProdotto);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/prodotto.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
