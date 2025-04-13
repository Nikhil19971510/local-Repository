package com.springboot.dto;

@SuppressWarnings("All")
public class RoomDto {

    private String name;
    private String type;
    private double price;
    private boolean available;

    public RoomDto() {
    }

    public RoomDto(String name, String type, double price, boolean available) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "RoomDto { " + "name='" + name + '\'' + ", type='" + type + '\'' + ", price=" + price + ", available=" + available + " }";
    }
}