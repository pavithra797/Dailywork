package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;   
    private int price;     

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order1 order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Order1 getOrder() {
        return order;
    }

    public void setOrder(Order1 order) {
        this.order = order;
    }
}