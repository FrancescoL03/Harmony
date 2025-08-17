package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConnPool;

@WebServlet("/Suggerimenti")
public class SuggerimentiServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("input");
		ArrayList<String> result = dammiNomi(input);
		response.setContentType("application/json");
		
		if(result != null) {
			Iterator<String> iterator = result.iterator();
			
			String str = "[";
			
			while(iterator.hasNext()) {
				String s = iterator.next();
				str = str.concat("{\"name\":").concat("\"" + s + "\"").concat("},");
			}
			
			int lastPos = str.lastIndexOf(",");
			String finished = str.substring(0,lastPos);
			String suggerimenti = finished.concat("]");
			
			response.getWriter().append(suggerimenti);			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private ArrayList<String> dammiNomi(String input){
		try {
			Connection conn = ConnPool.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select nome from prodotto where nome LIKE'" + input +"%' limit 5");
			ArrayList<String> list = new ArrayList<String>();
			if(rs.next()==false) {
				return null;
			} else {
				while(rs.next()) {
					list.add(rs.getString("nome"));
				}				
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
