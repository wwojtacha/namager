package com.example.namager.model;

import java.time.LocalDateTime;

public class SearchResult {

    private final LocalDateTime dateTime = LocalDateTime.now();
    private final String name;
    private final int age;

    private SearchResult(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static SearchResult create(String name, int age) {
        return new SearchResult(name.toLowerCase(), age);
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.of(dateTime.toLocalDate(), dateTime.toLocalTime());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
