package com.webshoppe.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webshoppe.ecommerce.entity.Book;
import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;
import com.webshoppe.ecommerce.repository.BookRepository;
import com.webshoppe.ecommerce.service.BookCatalogService;

public class BookCatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BookCatalogService bookCatalogService;

    @Override
    public void init() throws ServletException {
        final JdbcConnectionManager jdbcConnectionManager = new JdbcConnectionManager();
        final BookRepository bookRepository = new BookRepository(jdbcConnectionManager);
        bookCatalogService = new BookCatalogService(bookRepository);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        final List<Book> books = bookCatalogService.getBookCatalog();
        final StringBuilder stringBuilder = new StringBuilder();
        if (books.isEmpty()) {
            stringBuilder.append("<b>Book Catalog Empty</b>");
        } else {
            stringBuilder.append("<table class='table'>");
            stringBuilder.append("<thead>");
            stringBuilder.append("<th scope='col'>ID</th>");
            stringBuilder.append("<th scope='col'>Title</th>");
            stringBuilder.append("<th scope='col'>Description</th>");
            stringBuilder.append("<th scope='col'>Price</th>");
            stringBuilder.append("</thead>");
            books.forEach(e -> {
                stringBuilder.append("<tr scope='row'>");
                stringBuilder.append("<td>").append(e.getId()).append("</td>");
                stringBuilder.append("<td>").append(e.getName()).append("</td>");
                stringBuilder.append("<td>").append(e.getDescription()).append("</td>");
                stringBuilder.append("<td>").append(e.getPrice()).append("</td>");
                stringBuilder.append("</tr>");
            });
            stringBuilder.append("</table>");
        }

        PrintWriter out = response.getWriter();
        out.println(stringBuilder.toString());
        out.flush();
        out.close();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        final String minimumPriceParam = request.getParameter("minimum-price");
        final BigDecimal minimumPrice = new BigDecimal(minimumPriceParam);
        
        final String maximumPriceParam = request.getParameter("maximum-price");
        final BigDecimal maximumPrice = new BigDecimal(maximumPriceParam);

        final List<Book> books = bookCatalogService.getBookCatalog(minimumPrice, maximumPrice);
        final StringBuilder stringBuilder = new StringBuilder();
        if (books.isEmpty()) {
            stringBuilder.append("<b>Cannot find books that met the price range.</b>");
        } else {
            stringBuilder.append("<table class='table'>");
            stringBuilder.append("<thead>");
            stringBuilder.append("<th scope='col'>ID</th>");
            stringBuilder.append("<th scope='col'>Title</th>");
            stringBuilder.append("<th scope='col'>Description</th>");
            stringBuilder.append("<th scope='col'>Price</th>");
            stringBuilder.append("</thead>");
            books.forEach(e -> {
                stringBuilder.append("<tr scope='row'>");
                stringBuilder.append("<td>").append(e.getId()).append("</td>");
                stringBuilder.append("<td>").append(e.getName()).append("</td>");
                stringBuilder.append("<td>").append(e.getDescription()).append("</td>");
                stringBuilder.append("<td>").append(e.getPrice()).append("</td>");
                stringBuilder.append("</tr>");
            });
            stringBuilder.append("</table>");
        }

        PrintWriter out = response.getWriter();
        out.println(stringBuilder.toString());
        out.flush();
        out.close();
    }

}
