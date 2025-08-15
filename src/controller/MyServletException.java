package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/MyServletException")
public class MyServletException extends ServletException{

	public MyServletException() {
        super();
    }
   

    public MyServletException(String message) {
        super(message);
    }

    public MyServletException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
       
    public MyServletException(Throwable rootCause) {
        super(rootCause);
    }

    
    private static final long serialVersionUID = 1L;
}
