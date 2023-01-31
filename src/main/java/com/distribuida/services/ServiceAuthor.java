package com.programacion.services;

import com.programacion.db.Author;

import java.util.List;

public interface ServiceAuthor {
    public List<Author> findAll();
    Author findById(int id);

    void updateAuthor(int id, Author author);

   void createAuthor(Author author);
    void deleteAuthor(int id);
}
