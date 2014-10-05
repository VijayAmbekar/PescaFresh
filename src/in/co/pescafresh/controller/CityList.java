package in.co.pescafresh.controller;

import in.co.pescafresh.dao.DBAccess;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CityList
 */
@WebServlet(name = "citylist.do", urlPatterns = { "/citylist.do" })
public class CityList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CityList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// if (DBAccess.getCityList()) {
		// out.print("Connection Pooling success");
		// } else {
		// out.print("Connection Pooling failure!!!");
		// }
	}

}
