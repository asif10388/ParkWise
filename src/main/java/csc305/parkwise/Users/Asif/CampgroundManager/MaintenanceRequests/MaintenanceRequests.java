package csc305.parkwise.Users.Asif.CampgroundManager.MaintenanceRequests;

public class MaintenanceRequests {
    private int userId;
    private int requestId;
    private int campsiteId;
    private String priority;
    private String description;

    public MaintenanceRequests(int userId, int requestId, int campsiteId, String priority, String description) {
        this.userId = userId;
        this.requestId = requestId;
        this.campsiteId = campsiteId;
        this.priority = priority;
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(int campsiteId) {
        this.campsiteId = campsiteId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MaintenanceRequests{" +
                "userId=" + userId +
                ", requestId=" + requestId +
                ", campsiteId=" + campsiteId +
                ", priority='" + priority + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
