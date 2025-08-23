package com.createq.web_shop.dto;

public class ProductDTO {
    private long id;
    private String title;
    private String description;
    private String imageURL;
    private double price;
    private String categoryName;
    private int quantity;
    private String longDescription;
    private String videoURL;
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {this.quantity=quantity; }

    public String getLongDescription() {return longDescription; }

    public void setLongDescription(String longDescription) {this.longDescription=longDescription; }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName=categoryName;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
