package com.breje.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.breje.exceptions.LibraryException;
import com.breje.network.protocols.rpc.LibraryServerRpcProxy;
import com.breje.services.ILibraryServer;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ILibraryServer server = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		ILibraryServer server = new LibraryServerRpcProxy("localhost", 60000);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		try {
//			server.login(request.getParameter("username"), request.getParameter("password"), null);
//			response.sendRedirect("/books");
//		} catch (LibraryException e) {
//			e.printStackTrace();
//		}
	}

}
