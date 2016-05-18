package com.breje.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.breje.model.Book;
import com.breje.network.protocols.rpc.LibraryServerRpcProxy;
import com.breje.services.ILibraryServer;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private ILibraryServer server = null;
	// private ILibraryClient libraryClientController = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// server = new LibraryServerRpcProxy("localhost", 50000);
		// libraryClientController = new LibraryClientController(server);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ILibraryServer server = new LibraryServerRpcProxy("localhost", 60000);
		response.setContentType("text/html");
		try {
			List<Book> books = server.getAvailableBooks();
			PrintWriter pw = response.getWriter();
			pw.write("<ul>");
			for (Book book : books) {
				pw.write("<li>");
				pw.write(book.toString());
				pw.write("<li>");
			}
			pw.write("</ul>");
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("aici");
		ILibraryServer server = new LibraryServerRpcProxy("localhost", 60000);
		try {
			
			List<Book> books = server.getAvailableBooks();
			PrintWriter pw = response.getWriter();
			pw.write("<ul>");
			for (Book book : books) {
				pw.write("<li>");
				pw.write(book.toString());
				pw.write("<li>");
			}
			pw.write("</ul>");
			pw.flush();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// server.login(request.getParameter("username"),
		// request.getParameter("password"), libraryClientController);
		// request.getRequestDispatcher("/books").forward(request, response);
		// } catch (LibraryException e) {
		// e.printStackTrace();
		// }
	}

}
