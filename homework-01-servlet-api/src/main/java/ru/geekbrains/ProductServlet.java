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

@WebServlet(urlPatterns = "/Products/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("Potato", 22));
        this.productRepository.insert(new Product("Banana", 35));
        this.productRepository.insert(new Product("Duck", 87));
        this.productRepository.insert(new Product("Lamb", 124));
        this.productRepository.insert(new Product("Chicken", 453));
        this.productRepository.insert(new Product("Bacon", 454));
        this.productRepository.insert(new Product("Liver", 235));
        this.productRepository.insert(new Product("Meat", 923));
        this.productRepository.insert(new Product("Dill", 953));
        this.productRepository.insert(new Product("Onion", 23));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");

        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Products</th>");
        wr.println("</tr>");
        String path = req.getContextPath() + req.getServletPath() + req.getPathInfo();
        for (Product product : productRepository.findAll()) {

            wr.println("<tr>");
            wr.println("<td><a href='" + req.getContextPath() + req.getServletPath() + "/" + product.getTitle() + "'>" + product.getId() + "</a></td>");
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

    }

}


