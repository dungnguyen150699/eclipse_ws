package Controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.findAllUserDAO;
import Model.User;

@WebServlet(urlPatterns = {"/ListUser"})
public class ListUserController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3584270854625429405L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		findAllUserDAO fa;
		try {
			String page = request.getParameter("page");
			fa = new findAllUserDAO();
			int p = Integer.valueOf(page);
			if(p==1) {
				List<User> arr = fa.Find_All();
				request.setAttribute("allUser",arr);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/2ListUserServlet.jsp");
					rd.forward(request, response);
			}
			else {
				List<User> arr = fa.Find_All();
				request.setAttribute("allUser",arr);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ListUserServlet.jsp");
					rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("ListUserServlet.jsp");
//		rd.forward(request, response);
	}
}
