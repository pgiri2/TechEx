

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	      String userName = request.getParameter("userName");
	      String luckynumber = request.getParameter("luckyNumber");
	      String email = request.getParameter("email");
	      String age = request.getParameter("age");

	      Connection connection = null;
	      String insertSql = " INSERT INTO Entries (id, USERNAME, LUCKYNUMBER, EMAIL, AGE) values (default, ?, ?, ?, ?)";

	      try {
	         DBConnectionGiri.getDBConnection();
	         connection = DBConnectionGiri.connection;
	         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
	         preparedStmt.setString(1, userName);
	         preparedStmt.setString(2, luckynumber);
	         preparedStmt.setString(3, email);
	         preparedStmt.setString(4, age);
	         preparedStmt.execute();
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Insert Data to DB table";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n" + //
	            "<ul>\n" + //

	            "  <li><b>User Name</b>: " + userName + "\n" + //
	            "  <li><b>Lucky Number</b>: " + luckynumber + "\n" + //
	            "  <li><b>Email</b>: " + email + "\n" + //
	            "  <li><b>Age</b>: " + age + "\n" + //

	            "</ul>\n");

	      out.println("<a href=/webproject-techex-giri/result.html>View Results</a> <br>");
	      out.println("</body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
