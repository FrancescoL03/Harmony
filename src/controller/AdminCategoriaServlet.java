package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;


@WebServlet("/AdminCategoria")
public class AdminCategoriaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final CategoriaDAO categoriaDAO = new CategoriaDAO();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAdmin(request);

		String idstr = request.getParameter("id");
		if (idstr != null) {
			@SuppressWarnings("unchecked")
			List<Categoria> categorie = ((List<Categoria>) request.getAttribute("categorie"));

			Categoria categoria = null;
			if (!idstr.isEmpty()) {
				int id = Integer.parseInt(idstr);
				categoria = categorie.stream().filter(c -> c.getID() == id).findAny().get();
			}

			if (request.getParameter("rimuovi") != null) {
				categoriaDAO.doDelete(categoria.getID());
				categorie.remove(categoria);
				request.setAttribute("notifica", "Categoria rimossa con successo.");
			} else {
				String nome = request.getParameter("nome");
				if (nome != null) { // modifica/aggiunta categoria
					if (categoria == null) { // aggiunta nuova categoria
						categoria = new Categoria();
						categoria.setNome(nome);
						categoriaDAO.doSave(categoria);
						categorie.add(categoria);
						request.setAttribute("notifica", "Categoria aggiunta con successo.");
					} else { // modifica categoria esistente
						categoria.setNome(nome);
						categoriaDAO.doUpdate(categoria);
						request.setAttribute("notifica", "Categoria modificata con successo.");
					}
				}
				request.setAttribute("categoria", categoria);
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/admincategoria.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
