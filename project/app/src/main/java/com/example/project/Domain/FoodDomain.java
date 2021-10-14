package com.example.project.Domain;

import com.example.project.RetrofitClient;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private int id;
    private String name;
    private String img;
    private String description;
    private Double price;
    private int quantity;

    public FoodDomain(int id,String name, String img, String description, Double price) {
        this.id=id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.price = price;
    }

    public FoodDomain(int id,String name, String img, String description, Double price, int quantity) {
        this.id=id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getPic() {
        return img;
    }

    public void setPic(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return price;
    }

    public void setFee(Double price) {
        this.price = price;
    }

    public int getNumberInCart() {
        return quantity;
    }

    public void setNumberInCart(int quantity) {
        this.quantity = quantity;
    }
}
