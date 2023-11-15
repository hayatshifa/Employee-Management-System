package hayuta;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/add")
public class CreateEmployee extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String driver = "com.mysql.cj.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/mydb2";
	    String username = "root";
	    String password = "root";
			try {
				Class.forName(driver); 
				Connection connection = DriverManager.getConnection(url,username, password);
				Statement statement = connection.createStatement();
								
				String name = request.getParameter("name");
				String designation = request.getParameter("designation");
				int salary = Integer.parseInt(request.getParameter("salary")) ;
				
				String insertStatements = String.format("INSERT INTO employees (name, designation, salary) VALUES ('%s', '%s', %d)", name, designation, salary);

				statement.executeUpdate(insertStatements);

				statement.close();
				connection.close();
				
				response.sendRedirect("successPage.html");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}
