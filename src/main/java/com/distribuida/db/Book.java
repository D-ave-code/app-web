package com.distribuida.db;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name="book")
public class Book {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String isbn;
    @Getter
    @Setter
    private Integer author_id;
    @Getter
    @Setter
    private Integer price;
    @Getter
    @Setter
    private String title;
    @Override
    public String toString(){

        return "{\"id\":"+this.getId()+
                ",\"isbn\":"+"\""+this.getIsbn()+"\","+"\"author_id\":"+"\""+this.getAuthor_id()+"\""+
                ",\"price\":"+"\""+this.getPrice()+"\","+"\"title\":"+"\""+this.getTitle()+"\"}";
    }
}
