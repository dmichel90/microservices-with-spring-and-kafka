package de.dmichel90.ms_with_kafka.model;

import lombok.Data;

import java.util.Date;

@Data
public class Document {

    private String id;
    private String name;
    private String mimeType;
    private String path;
    private Date createdAt;
}
