package csc305.parkwise.Users.Asif.ParkDirector.Budgets;

import java.io.Serializable;
import java.time.LocalDate;

public class Budget implements Serializable {
    private int userId;
    private int budgetId;
    private int requestId;
    private String budgetType;
    private String budgetStatus;
    private LocalDate createdAt;
    private int allocatedAmount;
    private String budgetDescription;

    public Budget(int userId, int budgetId, int requestId, String budgetType, String budgetStatus, LocalDate createdAt, int allocatedAmount, String budgetDescription) {
        this.userId = userId;
        this.budgetId = budgetId;
        this.requestId = requestId;
        this.budgetType = budgetType;
        this.budgetStatus = budgetStatus;
        this.createdAt = createdAt;
        this.allocatedAmount = allocatedAmount;
        this.budgetDescription = budgetDescription;
    }

    public String getBudgetDescription() {
        return budgetDescription;
    }

    public void setBudgetDescription(String budgetDescription) {
        this.budgetDescription = budgetDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getAllocatedAmount() {
        return allocatedAmount;
    }

    public void setAllocatedAmount(int allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "userId=" + userId +
                ", budgetId=" + budgetId +
                ", requestId=" + requestId +
                ", budgetType='" + budgetType + '\'' +
                ", budgetStatus='" + budgetStatus + '\'' +
                ", createdAt=" + createdAt +
                ", allocatedAmount=" + allocatedAmount +
                ", budgetDescription='" + budgetDescription + '\'' +
                '}';
    }
}
