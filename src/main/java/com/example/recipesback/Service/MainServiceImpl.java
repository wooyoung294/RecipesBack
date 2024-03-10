package com.example.recipesback.Service;

import com.example.recipesback.Mapper.MainMapper;
import com.example.recipesback.Vo.CreateFoodRecipe;
import com.example.recipesback.Vo.FoodRecipeVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
public class MainServiceImpl implements MainService{
    private MainMapper mainMapper;
    private String projectRoot = System.getProperty("user.dir");
    private String uploadDir = projectRoot + "/uploads/";
    public MainServiceImpl(MainMapper mainMapper) {
        this.mainMapper = mainMapper;
    }

    @Override
    public List<FoodRecipeVo> getFoodRecipes(String category, String order, String searchText) {
        List<FoodRecipeVo> recipeVoList = mainMapper.getFoodRecipes(category,order,searchText);
        try {
            for (FoodRecipeVo post : recipeVoList) {
                String base64Image = encodeImage(uploadDir+ "/"+ post.getPostId() + "/"+post.getImageSrc());

                // AuctionTableVo의 img 필드에 Base64 이미지 문자열 설정
                post.setImageSrc(base64Image);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return recipeVoList;
    }

    @Override
    public int createFoodRecipe(String user, String title, String desc, MultipartFile imageSrc, String createdAt, String category) {
        try {
            String filePath  = null;
            // 파일이 존재하고, 배열을 순회하면서 각 파일 처리
            if (imageSrc != null) {
                    MultipartFile file = imageSrc;

                    // 파일의 원본 이름 얻기
                    String originalFileName = file.getOriginalFilename();

                    String no =mainMapper.getNextNo();

                    // 업로드 디렉토리에 파일 저장
                    String fullPath = uploadDir+ "/"+ no + "/" +originalFileName;
                    File dir = new File(uploadDir+ "/"+ no);

                    if (!dir.exists()) {
                        dir.mkdirs();  // 디렉토리가 없으면 생성
                    }

                    file.transferTo(new File(fullPath));

                    // 파일 이름을 StringBuilder에 추가
                    filePath=originalFileName;
            }
            CreateFoodRecipe createFoodRecipe = new CreateFoodRecipe();
            createFoodRecipe.setUser(user);
            createFoodRecipe.setTitle(title);
            createFoodRecipe.setCategory(category);
            createFoodRecipe.setDesc(desc);
            createFoodRecipe.setCreatedAt(createdAt);
            createFoodRecipe.setImageSrc(filePath);

            // 파일 업로드 성공 시 파일 이름들을 응답

            return mainMapper.createFoodRecipe(createFoodRecipe);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

    }
    private static String encodeImage(String imagePath) throws Exception {
        Path path = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
