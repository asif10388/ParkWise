package csc305.parkwise.Users.Asif.CampgroundManager.RestockSuppliesRequests;

import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class RestockSuppliesRequest implements Serializable {
    private int userId;
    private int itemId;
    private int quantity;
    private int requestId;
    private String status;
    private String priority;
    private String requestType;
    private int requiredAmount;
    private LocalDate createdAt;
    private String requestReason;

    public RestockSuppliesRequest(int userId, int itemId, int quantity, int requestId, String status, String priority, String requestType, int requiredAmount, LocalDate createdAt, String requestReason) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.requestId = requestId;
        this.status = status;
        this.priority = priority;
        this.requestType = requestType;
        this.requiredAmount = requiredAmount;
        this.createdAt = createdAt;
        this.requestReason = requestReason;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public boolean submitRestockSuppliesRequest() {
        try {
            StreamMapper stream = new StreamMapper();
            ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.RestockSuppliesRequestObjects), false);
            oos.writeObject(this);
            oos.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "RestockSuppliesRequest{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", requestId=" + requestId +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", requestType='" + requestType + '\'' +
                ", requiredAmount=" + requiredAmount +
                ", createdAt=" + createdAt +
                ", requestReason='" + requestReason + '\'' +
                '}';
    }
}
