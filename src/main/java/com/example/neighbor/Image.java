package com.example.neighbor;

public class Image {
    private long id;
    private long authorID;
    private String URL;

    public Image(long authorID, String URL){
        this.authorID = authorID;
        this.URL = URL;
    }

    public void setId(long id) { this.id = id; }

    public long getId() { return id; }
    public long getAuthorID() { return authorID; }
    public String getURL() { return URL; }
}
