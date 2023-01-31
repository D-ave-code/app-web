package com.distribuida;
import com.distribuida.db.Author;
import com.distribuida.db.Book;
import com.distribuida.services.ServiceAuthor;
import com.distribuida.services.ServiceBook;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class Main {
    static SeContainer container;

    public static void main(String[] args) {
        container = SeContainerInitializer.newInstance().initialize();

       routesAuthors();
       routesBooks();
    }

    public static void routesAuthors(){
        ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
        Instance<ServiceAuthor> obje = container.select(ServiceAuthor.class);
        ServiceAuthor serviceAuthor = obje.get();
        get("/authors", (req, res) -> {
                    List<Author> books = serviceAuthor.findAll();
                    Map<String, Object> model = new HashMap<>();
                    model.put("autores", books);
                    return engine.render(new ModelAndView(model, "Authors"));
                }
        );
        get("/author", (req, res) -> {
                    int id = Integer.parseInt(req.queryParams("id"));
                    Author author = serviceAuthor.findById(id);
                    Map<String, Object> model = new HashMap<>();
                    model.put("authors", author);
                    return engine.render(new ModelAndView(model, "DetailAuthor"));
                }
        );
        get("/authornew", "application/json", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    return engine.render(new ModelAndView(model, "newAuthorForm"));
                }
        );
        post("/newauthor", "application/json", (req, res) -> {
            Author author = new Author();
            String body = req.body();
            String[] cadena = body.split("&");
            String[] datos = cadena[0].split("=");
            author.setFirst_name(datos[1]);
            datos = cadena[1].split("=");
            author.setLast_name(datos[1]);
                    serviceAuthor.createAuthor(author);
                    res.redirect("/authors");
                    return null;
                }
        );

        get("/deleteauthor", (req, res) -> {
                    int id = Integer.parseInt(req.queryParams("id"));
                    serviceAuthor.deleteAuthor(id);
                    res.redirect("/authors");
                    return null;
                }
        );

        get("/authorupdate", (req, res) -> {
                    Author author = new Author();
                    int id = Integer.parseInt(req.queryParams("id"));
                    author = serviceAuthor.findById(id);
                    Map<String, Object> model = new HashMap<>();
            System.out.println(author.getFirst_name()+" soy update");
                    model.put("author", author);
                    return engine.render(new ModelAndView(model, "updateAuthorForm"));
                }
        );
        post("/updateauthor", (req, res) -> {
            Author author = new Author();
            String body = req.body();
            System.out.println(body);
            String[] cadena = body.split("&");
            String[] datos = cadena[0].split("=");
            System.out.println(Arrays.toString(datos));
            System.out.println(Arrays.toString(cadena));

            author.setId(Integer.valueOf(datos[1]));
            datos = cadena[1].split("=");

            author.setFirst_name(datos[1]);
            datos = cadena[2].split("=");
            System.out.println(cadena[2]);
            author.setLast_name(datos[1]);


            System.out.println("id:::::+ "+author.getId()+" soy updateeee"+author.getLast_name());
            serviceAuthor.updateAuthor(author.getId(), author);
                    res.redirect("/authors");
                    return null;
                }
        );


    }
    public static void routesBooks(){
        ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
        Instance<ServiceBook> obje = container.select(ServiceBook.class);
        ServiceBook serviceBook = obje.get();
        get("/books", (req, res) -> {
                    List<Book> books = serviceBook.findAll();
                    Map<String, Object> model = new HashMap<>();
           // System.out.println(books);
                    if(books.size()>0) {
                        model.put("bookks", books);

                    }

            return engine.render(new ModelAndView(model, "Books"));
                }
        );
        get("/book", (req, res) -> {
                    int id = Integer.parseInt(req.queryParams("id"));
            Book book = serviceBook.findById(id);
                    Map<String, Object> model = new HashMap<>();
            System.out.println(book.toString());
                    model.put("books", book);
                    return engine.render(new ModelAndView(model, "DetailBook"));
                }
        );
        get("/booknew", "application/json", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    return engine.render(new ModelAndView(model, "newBookForm"));
                }
        );
        post("/newbook", "application/json", (req, res) -> {
            Book author = new Book();
                    String body = req.body();
                    String[] cadena = body.split("&");
                    String[] datos = cadena[0].split("=");
                    author.setIsbn(datos[1]);
                    datos = cadena[1].split("=");
                    author.setAuthor_id(Integer.valueOf(datos[1]));

            datos = cadena[2].split("=");
            author.setPrice((Integer.valueOf(datos[1])));

            datos = cadena[3].split("=");
            author.setTitle((datos[1]));

           // System.out.println(author.toString());

            serviceBook.createBook(author);
                    res.redirect("/books");
                    return null;
                }
        );

        get("/deletebook", (req, res) -> {
                    int id = Integer.parseInt(req.queryParams("id"));
            serviceBook.deleteBook(id);
                    res.redirect("/books");
                    return null;
                }
        );

        get("/bookupdate", (req, res) -> {
            Book book = new Book();
                    int id = Integer.parseInt(req.queryParams("id"));
                    book = serviceBook.findById(id);
            System.out.println(book.toString());
                    Map<String, Object> model = new HashMap<>();
                    model.put("books", book);
                    return engine.render(new ModelAndView(model, "updateBookForm"));
                }
        );
        post("/updatebook", (req, res) -> {
            System.out.println("hh");
            Book author = new Book();
            String body = req.body();
            String[] cadena = body.split("&");
            String[] datos = cadena[0].split("=");
            System.out.println(Arrays.toString(datos));
            System.out.println(Arrays.toString(cadena));
            author.setId(Integer.valueOf(datos[1]));
            datos = cadena[1].split("=");
            author.setIsbn((datos[1]));
            System.out.println("holasss1");
            datos = cadena[2].split("=");
            author.setAuthor_id(Integer.valueOf(datos[1]));
            System.out.println("holasss2");
            datos = cadena[3].split("=");
            author.setPrice((Integer.valueOf(datos[1])));
            datos = cadena[4].split("=");
            author.setTitle(datos[1]);
            System.out.println("holasss3");
            System.out.println(author.toString()+"soy yo");
            serviceBook.updateBook(author.getId(), author);
                    res.redirect("/books");
                    return null;
                }
        );


    }
}
