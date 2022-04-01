package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/Products/*")
public class ProductServlet extends HttpServlet {

    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
//        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
//        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
//        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
//        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
//        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            PrintWriter wr = resp.getWriter();
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>Products</th>");
            wr.println("</tr>");
            String path = req.getContextPath() + req.getServletPath() + req.getPathInfo();
            for (Product product : productRepository.findAll()) {

                wr.println("<tr>");
                wr.println("<td><a href='" + getServletContext().getContextPath() + "/Products/" + product.getId() + "'>" + product.getId() + "</a></td>");
                wr.println("<td>" + product.getTitle() + "</td>");
                wr.println("</tr>");


                if (path.endsWith(product.getTitle())) {
                    wr.println("<table>");
                    wr.println("<tr>");
                    wr.println("<th>Products</th>");
                    wr.println("<th>Price</th>");
                    wr.println("</tr>");
                    wr.println("<tr>");
                    wr.println("<td>" + product.getTitle() + "</td>");
                    wr.println("<td>" + product.getCost() + "</td>");

                }
            }
            wr.println("</table>");

        } else {
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if (matcher.matches()) {
                long id = Long.parseLong(matcher.group(1));
                Product product = this.productRepository.findById(id);
                if (product == null) {
                    resp.getWriter().println("<p>Product not found</p>");
                    resp.setStatus(404);
                    return;
                }
                resp.getWriter().println("<p>ID: " + product.getId() + "</p>");
                resp.getWriter().println("<p>Name: " + product.getTitle() + "</p>");
                resp.getWriter().println("<p>Cost: " + product.getCost() + "</p>");
            } else {
                resp.getWriter().println("<p>Not correct parameters</p>");
                resp.setStatus(400);
            }
        }
    }
}


