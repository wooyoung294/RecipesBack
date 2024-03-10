package com.example.recipesback.Vo;

import lombok.Data;

@Data
public class CreateFoodRecipe {
    private String user;
    private String title;
    private String desc;
    private String category;
    private String imageSrc;
    private String createdAt;
}
