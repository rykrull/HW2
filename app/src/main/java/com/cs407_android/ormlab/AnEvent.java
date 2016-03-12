package com.cs407_android.ormlab;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table AN_EVENT.
 */
public class AnEvent {

    private Long id;
    private String name;
    private String location;
    private String description;
    private Integer day;
    private Integer month;
    private Integer year;
    private Boolean display;

    public AnEvent() {
    }

    public AnEvent(Long id) {
        this.id = id;
    }

    public AnEvent(Long id, String name, String location, String description, Integer day, Integer month, Integer year, Boolean display) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.day = day;
        this.month = month;
        this.year = year;
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

}
