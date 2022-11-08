package com.webshoppe.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.webshoppe.ecommerce.entity.Flower;
import com.webshoppe.ecommerce.entity.Book;
import com.webshoppe.ecommerce.entity.Toy;
import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;
import com.webshoppe.ecommerce.repository.FlowerRepository;
import com.webshoppe.ecommerce.repository.BookRepository;
import com.webshoppe.ecommerce.repository.ToyRepository;
import com.webshoppe.ecommerce.service.BookCatalogService;
import com.webshoppe.ecommerce.service.FlowerCatalogService;
import com.webshoppe.ecommerce.service.ToyCatalogService;


@WebServlet("/selection-catalog")
public class SelectionCatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private ToyCatalogService toyCatalogService;
	private BookCatalogService bookCatalogService;
	private FlowerCatalogService flowerCatalogService;
	private ServletConfig config;
	private String selectionParam;
	private String formParam;
	
	public void init(ServletConfig config) throws ServletException {
	    final JdbcConnectionManager jdbcConnectionManager = new JdbcConnectionManager();
        final FlowerRepository flowerRepository = new FlowerRepository(jdbcConnectionManager);
        flowerCatalogService = new FlowerCatalogService(flowerRepository);
        
        final BookRepository bookRepository = new BookRepository(jdbcConnectionManager);
        bookCatalogService = new BookCatalogService(bookRepository);
        
        final ToyRepository toyRepository = new ToyRepository(jdbcConnectionManager);
        toyCatalogService = new ToyCatalogService(toyRepository);
        
        
        
        this.config = config;
        
        
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    formParam = request.getParameter("selection");
	    selectionParam = config.getInitParameter("choice");
	    if(formParam.equalsIgnoreCase("toy")) {
	        toyInstanceAndAppend(response);
	    } else if(formParam.equalsIgnoreCase("book")) {
	        bookInstanceAndAppend(response);
	    } else if(formParam.equalsIgnoreCase("flower")) {
            flowerInstanceAndAppend(response);
	    } else {
	        PrintWriter out = response.getWriter();
	        response.setContentType("text/html");
	        out.print("<b>Invalid choice </b><br/>");
	    }
	    
	}
	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");

        final String minimumPriceParam = request.getParameter("minimum-price");
        final BigDecimal minimumPrice = new BigDecimal(minimumPriceParam);
        
        final String maximumPriceParam = request.getParameter("maximum-price");
        final BigDecimal maximumPrice = new BigDecimal(maximumPriceParam);
        
        formParam = request.getParameter("selection");
        selectionParam = config.getInitParameter("choice");
        if(formParam.equalsIgnoreCase("toy")) {
            toyInstanceAndAppend(response);
        } else if(formParam.equalsIgnoreCase("book")) {
            bookInstanceAndAppend(response);
        } else if(formParam.equalsIgnoreCase("flower")) {
            flowerInstanceAndAppend(response);
        } else {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<b>Invalid choice </b><br/>");
        }
	}
	
	public void toyInstanceAndAppend(HttpServletResponse response) throws IOException {
        final List<Toy> toys = toyCatalogService.getToyCatalog();
        final StringBuilder stringBuilder = new StringBuilder();
        if (toys.isEmpty()) {
            stringBuilder.append("<b>Flower Catalog Empty</b>");
        } else {
            stringBuilder.append("<table class='table'>");
            stringBuilder.append("<thead>");
            stringBuilder.append("<th scope='col'>ID</th>");
            stringBuilder.append("<th scope='col'>Name</th>");
            stringBuilder.append("<th scope='col'>Description</th>");
            stringBuilder.append("<th scope='col'>Price</th>");
            stringBuilder.append("</thead>");
            toys.forEach(e -> {
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
	
	public void bookInstanceAndAppend(HttpServletResponse response) throws IOException {
        final List<Book> books = bookCatalogService.getBookCatalog();
        final StringBuilder stringBuilder = new StringBuilder();
        if (books.isEmpty()) {
            stringBuilder.append("<b>Flower Catalog Empty</b>");
        } else {
            stringBuilder.append("<table class='table'>");
            stringBuilder.append("<thead>");
            stringBuilder.append("<th scope='col'>ID</th>");
            stringBuilder.append("<th scope='col'>Name</th>");
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
	
	public void flowerInstanceAndAppend(HttpServletResponse response) throws IOException {
        final List<Flower> flowers = flowerCatalogService.getFlowerCatalog();
        final StringBuilder stringBuilder = new StringBuilder();
        if (flowers.isEmpty()) {
            stringBuilder.append("<b>Flower Catalog Empty</b>");
        } else {
            stringBuilder.append("<table class='table'>");
            stringBuilder.append("<thead>");
            stringBuilder.append("<th scope='col'>ID</th>");
            stringBuilder.append("<th scope='col'>Name</th>");
            stringBuilder.append("<th scope='col'>Description</th>");
            stringBuilder.append("<th scope='col'>Price</th>");
            stringBuilder.append("</thead>");
            flowers.forEach(e -> {
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
