package com.ui.placeholder;

public class EventData {
    public final String id;
    public final String name;
    public final String details;
    public final String imageUrl;

    public EventData(String id, String name, String details, String imageUrl) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return name;
    }
}
