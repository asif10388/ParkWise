package csc305.parkwise.Users.Asif.CampgroundManager.Common.Models;

import java.io.Serializable;

public class Equipment implements Serializable {
	private int itemId;
	private String itemName;
	private String itemType;
	private int itemsInStock;
	private boolean isItemInUse;
	private String itemCategory;
	private int itemPerUnitPrice;
	private String itemDescription;
	private String itemAvailabilityStatus;

	public Equipment(int itemId, String itemName, String itemType, int itemsInStock, boolean isItemInUse,
			String itemCategory, int itemPerUnitPrice, String itemDescription, String itemAvailabilityStatus) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemsInStock = itemsInStock;
		this.isItemInUse = isItemInUse;
		this.itemCategory = itemCategory;
		this.itemPerUnitPrice = itemPerUnitPrice;
		this.itemDescription = itemDescription;
		this.itemAvailabilityStatus = itemAvailabilityStatus;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getItemsInStock() {
		return itemsInStock;
	}

	public void setItemsInStock(int itemsInStock) {
		this.itemsInStock = itemsInStock;
	}

	public boolean getIsItemInUse() {
		return isItemInUse;
	}

	public void setIsItemInUse(boolean isItemInUse) {
		this.isItemInUse = isItemInUse;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getItemPerUnitPrice() {
		return itemPerUnitPrice;
	}

	public void setItemPerUnitPrice(int itemPerUnitPrice) {
		this.itemPerUnitPrice = itemPerUnitPrice;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String isItemAvailabilityStatus() {
		return itemAvailabilityStatus;
	}

	public void setItemAvailabilityStatus(String itemAvailabilityStatus) {
		this.itemAvailabilityStatus = itemAvailabilityStatus;
	}

	public boolean isAvailable() {
		return itemsInStock > 10 && !isItemInUse;
	}

	public double calculateTotalValue() {
		return itemsInStock * itemPerUnitPrice;
	}

	public boolean belongsToCategory(String category) {
		return this.itemCategory.equalsIgnoreCase(category);
	}

	public String determineStockStatus() {
		if (itemsInStock == 0) {
			return "Out of stock";
		} else if (itemsInStock < 10) {
			return "Low stock";
		} else {
			return "Available";
		}
	}

	@Override
	public String toString() {
		return "Equipment{" +
				"itemId=" + itemId +
				", itemName='" + itemName + '\'' +
				", itemType='" + itemType + '\'' +
				", itemsInStock=" + itemsInStock +
				", isItemInUse='" + isItemInUse + '\'' +
				", itemCategory='" + itemCategory + '\'' +
				", itemPerUnitPrice=" + itemPerUnitPrice +
				", itemDescription='" + itemDescription + '\'' +
				", itemAvailabilityStatus=" + itemAvailabilityStatus +
				'}';
	}
}
