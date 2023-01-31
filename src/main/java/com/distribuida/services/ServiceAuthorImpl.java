package com.programacion.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.programacion.db.Author;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@ApplicationScoped
public class ServiceAuthorImpl implements ServiceAuthor {

    @Override
    public List<Author> findAll() {
        String url = "http://author.localhost/authors";
        Client client = ClientBuilder.newClient();
        Response author = client
                .target(url)
                .request().get();
        Gson gson = new Gson();
        List<Author> list = gson.fromJson(author.readEntity(String.class), new TypeToken<List<Author>>() {}.getType());

        return list;
    }
    @Override
    public void createAuthor(Author author) {
        String url = "http://author.localhost/authors";
        Client client = ClientBuilder.newClient();

        Response res = client
                .target(url)
                .request(MediaType.APPLICATION_JSON).post(Entity.json(author.toString()));
    }
    @Override
    public void deleteAuthor(int id) {
        String url = "http://author.localhost/authors/"+id;
        Client client = ClientBuilder.newClient();
        client.target(url).request().delete();
    }
    @Override
    public Author findById(int id) {
        String url = "http://author.localhost/authors/"+id;
        Client client = ClientBuilder.newClient();
        Response res = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        Gson gson = new Gson();
        Author list = gson.fromJson(res.readEntity(String.class), new TypeToken<Author>() {}.getType());

        return list;
    }
    @Override
    public void updateAuthor(int id, Author author) {
        String url = "http://author.localhost/authors/"+id;
        Client client = ClientBuilder.newClient();

        Response res = client
                .target(url)
                .request().put(Entity.json(author.toString()));

    }


}
