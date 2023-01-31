package com.programacion.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.programacion.db.Author;
import com.programacion.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class ServiceBookImpl implements ServiceBook {

    @Override
    public List<Book> findAll() {
        String url = "http://book.localhost/books";
        Client client = ClientBuilder.newClient();
        Response res = client
                .target(url)
                .request().get();
        Gson gson = new Gson();
        List<Book> list = gson.fromJson(res.readEntity(String.class), new TypeToken<List<Book>>() {}.getType());

        return list;
    }
    public void createBook(Book book) {
        String url = "http://book.localhost/books";
        Client client = ClientBuilder.newClient();

        Response res = client
                .target(url)
                .request(MediaType.APPLICATION_JSON).post(Entity.json(book.toString()));
    }
@Override
    public Book findById(int id) {
        String url = "http://book.localhost/books/"+id;
        Client client = ClientBuilder.newClient();
    Response res = client
            .target(url)
            .request().get();
    Gson gson = new Gson();
    Book book = gson.fromJson(res.readEntity(String.class), new TypeToken<Book>() {}.getType());
        return book;
    }

    @Override
    public void deleteBook(int id) {
        String url = "http://book.localhost/books/"+id;
        Client client = ClientBuilder.newClient();
        client.target(url).request().delete();
    }

    @Override
    public void updateBook(int id, Book book) {
        String url = "http://book.localhost/books/"+id;
        Client client = ClientBuilder.newClient();

        Response res = client
                .target(url)
                .request().put(Entity.json(book.toString()));

    }



}
