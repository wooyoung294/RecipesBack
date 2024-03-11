package com.example.recipesback.Mapper;

import com.example.recipesback.Vo.CreateFoodRecipe;
import com.example.recipesback.Vo.FoodRecipeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MainMapper {

    List<FoodRecipeVo> getFoodRecipes(String category, String order, String searchText);

    int createFoodRecipe(CreateFoodRecipe createFoodRecipe);

    String getNextNo();

    FoodRecipeVo getFoodRecipeDetail(String postId);

    int increaseViewCount(String postId);
}
