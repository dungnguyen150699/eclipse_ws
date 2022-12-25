package Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.Value;

import DAO.userDAO;
import Model.User;

/**
 * Servlet implementation class userServlet
 */
@WebServlet(urlPatterns = {"","/user/edit",
		"/user/update","/user/shopping",
		"/user/insert","/user/delete","/user/viewbill"})
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private userDAO ud;
	
	public void init() {
		try {
			ud = new userDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public userServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println(action);
		try {
			switch (action) {
			case "/user/viewbill":
				System.out.println("Showing bill page");
				showProduct(request, response);
				break;
			case "/user/shopping":
				System.out.println("Showing page");
				showProduct(request, response);
				break;
			case "/user/insert":
				System.out.println("Inserting");
				insertUser(request, response);
				break;
			case "/user/delete":
				System.out.println("Deletting");
				deleteUser(request, response);
				break;
			case "/user/edit":
				System.out.println("Showing edit page");
				showEditForm(request, response);
				break;
			case "/user/update":
				System.out.println("Updating");
				updateUser(request, response);
				break;
			default:
					listUser(request, response);
				break;
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"resultCode\":\"-2");
        sb.append("\",\"message\":\"");
        sb.append("Sai captcha !}");
        PrintWriter out = response.getWriter();
        out.print(sb.toString());
        out.flush();
        System.out.println(out.toString());
		
	}

	private void showProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User u = new User();
		u.setId(Integer.parseInt(request.getParameter("id")));
		u.setName(request.getParameter("name"));
//		java.util.Date utilDate = new java.util.Date(request.getParameter("birth"));
		java.sql.Date birth= java.sql.Date.valueOf(request.getParameter("birth"));
		u.setBirth(birth);
		boolean checkUpdate = ud.edit(u);
		if(checkUpdate) {
			//NTN sẽ ra loaclhost8080/CRUD_ServletMaven/
			response.sendRedirect(request.getContextPath()+""); 
			// Nếu để là "" thôi thì sẽ ra link localhost:8080/user/
		}
		else {
			request.setAttribute("user", u);
			request.setAttribute("mesUpdate", "Update Fail");
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		User u0 = new User();
		u0.setId(id);
		List<User> users = ud.getUsersByCondition(u0);
		request.setAttribute("user", users.get(0));
		request.setAttribute("mesUpdate", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	public void listUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<User> listUser = new ArrayList<User>();
		try {
			listUser = ud.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
//		System.out.println(listUser.get(0).getName());
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
