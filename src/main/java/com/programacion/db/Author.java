package com.programacion.db;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name="author")
public class Author {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String first_name;
    @Getter
    @Setter
    private String last_name;
    @Override
    public String toString(){

        return "{\"id\":"+this.getId()+
                ",\"first_name\":"+"\""+this.getFirst_name()+"\","+"\"last_name\":"+"\""+this.getLast_name()+"\"}";
    }
}
