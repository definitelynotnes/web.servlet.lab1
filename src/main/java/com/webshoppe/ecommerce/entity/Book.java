package com.webshoppe.ecommerce.entity;

import java.math.BigDecimal;

public class Book {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    
    public Book() {
    }

    public Book(String id, String name, String description, BigDecimal price) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Override
    public int hashCode() {
        return this.id.hashCode() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }
        Book otherBook = (Book)obj;
        return this.id.equals(otherBook.getId()) && this.name.equals(otherBook.getName())
               && this.description.equals(otherBook.getDescription())
               && this.price.equals(otherBook.getPrice());
    }
    
}

    

    
