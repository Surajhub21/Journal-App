package com.developersuraj.journalApp.apiResponse;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsApiPOJO {
    private String status;
    private int totalResults;
    private List<Article> articles;

    @Data
    public static class Article {
        private Source source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String content;
    }

    @Data
    public static class Source {
        private String id;
        private String name;
    }
}
