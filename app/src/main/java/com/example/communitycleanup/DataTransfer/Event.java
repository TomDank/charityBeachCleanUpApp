package com.example.communitycleanup.DataTransfer;

/**A class representing an Event
 * Used as a data transfer object to pass data from the database to the UI
 */
public class Event {
    private String description;
    private String location;
    private String date;
    private String start;
    private String finish;
    private String favourite;

    public Event()
    {
        this.description="";
        this.location="";
        this.date="";
        this.start="";
        this.finish="";
        this.favourite="";
    }

    public Event(String description,String location,String date,String start,String finish,String favourite)
    {
        this.description=description;
        this.location=location;
        this.date=date;
        this.start=start;
        this.finish=finish;
        this.favourite=favourite;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }
}
