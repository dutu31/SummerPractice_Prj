package com.createq.web_shop.model;

import jakarta.persistence.*;

@Entity
@Table
public class

ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private String videoURL;
    private int quantity;
    @Column(length = 4000)
    private String longDescription;
    @ManyToOne
    @JoinColumn(name="category_id")
    private ProductCategoryModel category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ProductCategoryModel getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryModel category) {
        this.category = category;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {this.quantity=quantity; }

    public String getLongDescription() {return longDescription; }

    public void setLongDescription(String description) {this.description=description; }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}