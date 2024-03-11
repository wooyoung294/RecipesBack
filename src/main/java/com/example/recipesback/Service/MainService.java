package com.example.recipesback.Service;

import com.example.recipesback.Vo.FoodRecipeVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MainService {
    List<FoodRecipeVo> getFoodRecipes(String category, String order, String searchText);

    int createFoodRecipe(String user, String title, String desc, MultipartFile imageSrc, String createdAt, String category);

    FoodRecipeVo getFoodRecipeDetail(String postId);
}
