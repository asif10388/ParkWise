package csc305.parkwise.Users.Asif.ParkDirector.Announcements;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Announcement implements Serializable {
    private String body;
    private String title;
    private LocalDate expirationDate;
    private ArrayList<String> receivers;

    private String status;
    private String announcer;
    private LocalDate createdAt;

    @Override
    public String toString() {
        return "Announcement{" +
                "body='" + body + '\'' +
                ", title='" + title + '\'' +
                ", expirationDate=" + expirationDate +
                ", receivers=" + receivers +
                ", status='" + status + '\'' +
                ", announcer='" + announcer + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnnouncer() {
        return announcer;
    }

    public void setAnnouncer(String announcer) {
        this.announcer = announcer;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Announcement(String body, String title, LocalDate expirationDate, ArrayList<String> receivers, String status, String announcer, LocalDate createdAt) {
        this.body = body;
        this.title = title;
        this.expirationDate = expirationDate;
        this.receivers = receivers;
        this.status = status;
        this.announcer = announcer;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public ArrayList<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(ArrayList<String> receivers) {
        this.receivers = receivers;
    }

}
