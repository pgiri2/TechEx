

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Database Result";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	            "transitional//en\">\n"; //
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");

	      Connection connection = null;
	      PreparedStatement preparedStatement = null;
	      try {
	         DBConnectionGiri.getDBConnection();
	         connection = DBConnectionGiri.connection;
	         
	         String selectSQL = "SELECT * FROM Entries";
	         preparedStatement = connection.prepareStatement(selectSQL);
	         
	         ResultSet rs = preparedStatement.executeQuery();

	         final int MAXIMUM = 5;
	         
	         int count = 0;
	         String[] names = new String[MAXIMUM];
	         String[] emails = new String[MAXIMUM];
	         int[] numbers = new int[MAXIMUM];
	         int[] ages = new int[MAXIMUM];
	         int[] range = new int[MAXIMUM];
	         while (rs.next()) {
	        	 int id = rs.getInt("id");
	        	 names[count] = rs.getString("userName").trim();
	        	 numbers[count] = Integer.parseInt(rs.getString("luckyNumber").trim());
	        	 emails[count] = rs.getString("email").trim();
	        	 ages[count] = Integer.parseInt(rs.getString("age").trim());
	        	 count++;
	         }
	         
	         if (count < MAXIMUM)
	         {
	        	 out.println("Total Entries Registered: "+ count);
	        	 out.println("<br>");
	        	 out.println("Number of entries still needed: " + (MAXIMUM - count));
	        	 
	         }
	         else
	         {
	        	
	        	 Random rand = new Random();
	        	 int num = rand.nextInt(MAXIMUM);
	        	 
	        	 System.out.println("Random number: " +  num);
	        	 
	        	 out.println("The winner is: " + names[num]);
	        	 out.println("<br>");
	        	 out.println("An email will be sent to: " + emails[num]);
	         }
	         
             out.println("<br><br>");
	         for(int i = 0; i < count; i++)
             {
	        	 out.println("ID: " + (i + 1) + ", ");
	             out.println("User: " + names[i] + ", ");
	             out.println("LuckyNumber: " + numbers[i] + ", ");
	             out.println("Email: " + emails[i] + ", ");
	             out.println("Age: " + ages[i] + "<br>");
             }

	         
	         out.println("<br><br>");
	         out.println("<a href=/webproject-techex-giri/insertpage.html>Add Entries</a> <br>");
	         out.println("</body></html>");
	         rs.close();
	         preparedStatement.close();
	         connection.close();

	      }
	      catch (SQLException se) {
	          se.printStackTrace();
	       } catch (Exception e) {
	          e.printStackTrace();
	       } finally {
	          try {
	             if (preparedStatement != null)
	                preparedStatement.close();
	          } catch (SQLException se2) {
	          }
	          try {
	             if (connection != null)
	                connection.close();
	          } catch (SQLException se) {
	             se.printStackTrace();
	          }
	       }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
