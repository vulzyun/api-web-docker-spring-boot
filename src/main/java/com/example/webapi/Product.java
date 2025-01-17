package com.example.webapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
    @Id
    public int reference;

    @Column(nullable = false, length = 25)
    @Size(max = 25, message = "Le nom doit faire moins de 25 caractères")
    public String name;

    @Column(nullable = false, length = 255)
    @Size(min = 25, message = "La description doit contenir au moins 25 caractères")
    public String description;

    @Column(nullable = false)
    @Min(value = 1, message = "Le prix doit être supérieur à 0")
    public int price;

    // Getters and Setters
    public int getReference() {return reference;}
    public void setReference(int reference) {this.reference = reference;}

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}