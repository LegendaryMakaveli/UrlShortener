package com.urlShortner.datas.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "shortUrls")
public class ShortUrl {
    @Id
    private String id;
    private String shortUrl;
    private String longUrl;
    private String createdAt;
    private String owner;
}
