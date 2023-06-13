package com.ui.placeholder;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<Event> ITEMS = new ArrayList<>(Arrays.asList(
            new Event("1", "Event 1", "Description 1"),
            new Event("2", "Event 2", "Description 2"),
            new Event("3", "Event 3", "Description 3"),
            new Event("4", "Event 4", "Description 4"),
            new Event("5", "Event 5", "Description 5")
            ));

    public static class Event {
        public final String id;
        public final String name;
        public final String details;

        public Event(String id, String name, String details) {
            this.id = id;
            this.name = name;
            this.details = details;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}