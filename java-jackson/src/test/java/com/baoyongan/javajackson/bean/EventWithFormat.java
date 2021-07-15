package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EventWithFormat {
    public String name;
 
    @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "dd-MM-yyyy hh:mm:ss")
    public Date eventDate;

    public EventWithFormat(String name, Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }
}
