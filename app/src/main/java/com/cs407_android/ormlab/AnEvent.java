package com.cs407_android.ormlab;

/**
 * Created by rkrul on 3/11/2016.
 */
public class AnEvent {
    private Long id;
    private String name;
    private String date;
    private String start;
    private String end;
    private String location;
    private String description;
    private Boolean display;

    public AnEvent() {
    }

    public AnEvent(Long id) {
        this.id = id;
    }

    public AnEvent(Long id, String name, String date, String start, String end, String location, String description, Boolean display) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.start = start;
        this.end = end;
        this.location = location;
        this.description = description;
        this.display = display;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

}
