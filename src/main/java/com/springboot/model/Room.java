package com.springboot.model;

import com.springboot.common.EntityIdentity;
import jakarta.persistence.*;


@SuppressWarnings("All")
@Entity
@Table(name = "rooms")
public class Room extends EntityIdentity {


    @Column(name = "roomname")
    private String roomname;

    @Column(name = "roomtype")
    private String roomtype;

    @Column(name = "price")
    private double price;

    @Column(name = "isavailable")
    private boolean isavailable;

    public Room() {
    }

    public Room(String roomname, String roomtype, double price, boolean isavailable) {
        this.roomname = roomname;
        this.roomtype = roomtype;
        this.price = price;
        this.isavailable = isavailable;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIsavailable() {
        return isavailable;
    }

    public void setIsavailable(boolean isavailable) {
        this.isavailable = isavailable;
    }
}
