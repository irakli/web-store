package servlet;

import database.bean.Product;
import database.dao.ProductDAO;
import listener.ContextKey;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Products", urlPatterns = {"/Products", "/index.html"})
public class Products extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		ProductDAO dao = (ProductDAO) context.getAttribute(ContextKey.DAO);
		List<Product> products = dao.getProducts();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\" />");
		out.println("<title>Metropolises DataSource Example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>Here are all products:</h3>");
		out.println("<table border=\"1px\">");
		printTable(out, products);
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	private void printTable(PrintWriter out, List<Product> products) {
		for (Product p : products) {
			out.println("<tr>");
			out.println("   <td>" + p.getId() + "</td>");
			out.println("   <td>" + p.getName()  + "</td>");
			out.println("   <td>" + p.getImage() + "</td>");
			out.println("   <td>" + p.getPrice() + "</td>");
			out.println("</tr>");
		}
	}
}