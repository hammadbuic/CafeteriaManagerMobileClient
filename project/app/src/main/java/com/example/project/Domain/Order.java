package com.example.project.Domain;

import androidx.annotation.Nullable;

public class Order {

    public Order(String customer_name, int order_quantity, String delivery_address, String order_time, int item_id, boolean status) {
        this.customer_name = customer_name;
        this.order_quantity = order_quantity;
        this.delivery_address = delivery_address;
        this.order_time = order_time;
        this.item_id = item_id;
        this.status = status;
    }

    private String customer_name;
    private int order_quantity;
    private String delivery_address;
    private String order_time;
    private int item_id;
    private boolean status;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity = order_quantity;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
