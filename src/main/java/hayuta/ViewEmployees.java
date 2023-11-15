package hayuta;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/view")
public class ViewEmployees extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb2";
        String username = "root";
        String password = "root";
        

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM employees");

            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>\r\n"
                + "  <link rel=\"stylesheet\" href=\"css/bootstrap.css\">\r\n"
                + "</head>");
            pw.println("<body class=\"container-fluid card\" style= \"width: 40rem;\">");
            pw.println("<h1 class=\"bg-danger text-white text-center\">Employee Data</h1>");
            pw.println("<table class=\"table table-hover\" >");
            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Designation</th>");
            pw.println("<th>Salary</th>");
            pw.println("</tr>");

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String designation = result.getString("designation");
                int salary = result.getInt("salary");

                pw.println("<tr>");
                pw.println("<td>" + id + "</td>");
                pw.println("<td>" + name + "</td>");
                pw.println("<td>" + designation + "</td>");
                pw.println("<td>" + salary + "</td>");
                pw.println("</tr>");
            }

            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while displaying the employee");
        }
    }
}