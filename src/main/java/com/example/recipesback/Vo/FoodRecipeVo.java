package com.example.recipesback.Vo;

import lombok.Data;

@Data
public class FoodRecipeVo {
    private int postId;
    private String user;
    private String title;
    private String imageSrc;
    private String createdAt;
    private int views;

}
