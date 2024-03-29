package servlet;

import database.bean.Product;
import database.dao.ProductDAO;
import listener.ContextKey;

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
		// Forward post request to get.
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		ProductDAO dao = (ProductDAO) context.getAttribute(ContextKey.DAO);
		List<Product> products = dao.getProducts();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// Print html page with list of products "dynamically".
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\" />");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css\"\n" +
				"          integrity=\"sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ\" crossorigin=\"anonymous\">\n" +
				"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js\"\n" +
				"            integrity=\"sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn\"\n" +
				"            crossorigin=\"anonymous\"></script>");
		out.println("<title>Student Store</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Student Store</h1>");
		out.println("Items available:");
		out.println("<br />");
		out.println("<table border=\"1px\">");
		printList(out, products);
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * Prints products with links.
	 *
	 * @param out      writer
	 * @param products list of products
	 */
	private void printList(PrintWriter out, List<Product> products) {
		for (Product p : products) {
			out.println("<ul>");
			out.println("	<li><a href=\"show-product.jsp?id=" + p.getId() + "\"</a>" + p.getName() + "</li>");
			out.println("</ul>");
		}
	}
}
