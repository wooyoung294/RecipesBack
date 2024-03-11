package com.example.recipesback.Controller;


import com.example.recipesback.Service.MainService;
import com.example.recipesback.Vo.FoodRecipeVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/food")
    public List<FoodRecipeVo> getFoodRecipes(
            @RequestParam("category") String category,
            @RequestParam("order") String order,
            @RequestParam("searchText") String searchText
    ){
        if(searchText == ""){
            searchText = null;
        }
        return mainService.getFoodRecipes(category,order,searchText);
    }
    @GetMapping("/food/detail")
    public FoodRecipeVo getFoodRecipeDetail(
            @RequestParam("postId") String postId
    ){
        return mainService.getFoodRecipeDetail(postId);
    }
    @PostMapping("/createFood")
    public int createFoodRecipe(
            @RequestParam("user") String user,
            @RequestParam("title") String title,
            @RequestParam("desc") String desc,
            @RequestParam("imageSrc") MultipartFile imageSrc,
            @RequestParam("createdAt") String createdAt,
            @RequestParam("category") String category
    ){

        return mainService.createFoodRecipe(user,title,desc,imageSrc,createdAt,category);
    }
    @PatchMapping("/increaseViewCount")
    public int increaseViewCount(
            @RequestParam("postId") String postId
    ){
        return mainService.increaseViewCount(postId);
    }
}
