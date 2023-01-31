package com.programacion.services;

import com.programacion.db.Book;

import java.util.List;

public interface ServiceBook {
    public List<Book> findAll();
   Book findById(int id);
    void deleteBook(int id);
    void updateBook(int id, Book book);
    void createBook( Book book);


}
