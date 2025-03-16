package com.StudyHub.StudyHub.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_name;
    private String text;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    public Review() {
    }

    public Review(String user_name, String text, int rating, Material material) {
        this.user_name = user_name;
        this.text = text;
        this.rating = rating;
        this.material = material;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String user) {
        this.user_name = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}