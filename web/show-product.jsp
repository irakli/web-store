<%@ page import="database.bean.Product" %>
<%@ page import="database.dao.ProductDAO" %>
<%@ page import="listener.ContextKey" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ServletContext context = request.getServletContext();
    ProductDAO dao = (ProductDAO) context.getAttribute(ContextKey.DAO);
    Product product = dao.getProductById(request.getParameter("id"));
%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
            integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
            crossorigin="anonymous"></script>
    <title><%= product.getName() %>
    </title>
</head>
<body>
<h1><%= product.getName() %>
</h1>
<img src="<%="/store-images/" + product.getImage() %>" alt="<%= product.getName() %>">

<form action="Cart" method="post">
    $<%= product.getPrice() %> <input name="id" type="hidden" value="<%= product.getId() %>"/>
    <input type="submit" value="Add to Cart"/>
</form>

</body>
</html>
