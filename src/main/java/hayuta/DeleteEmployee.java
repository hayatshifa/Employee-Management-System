package hayuta;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/delete")
public class DeleteEmployee extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response){	
		String driver = "com.mysql.cj.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/mydb2";
	    String username = "root";
	    String password = "root";
		
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, password);
	        Statement statement = connection.createStatement();
	        int id = Integer.parseInt(request.getParameter("id"));

	        String deleteQuery = String.format("DELETE FROM employees WHERE id = %d", id);
	        
	        statement.executeUpdate(deleteQuery);
	        statement.close();
	        connection.close();
	        
	        response.sendRedirect("successPage.html");
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		
	}

	
}