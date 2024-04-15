package edu.utsa.cs3443.rowdyeatsv2.model;

public class Recipe {
    private String title;
    private int imgid;
    private int recipeBodyId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public int getRecipeBodyId() {
        return recipeBodyId;
    }

    public void setRecipeBodyId(int recipeBodyId) {
        this.recipeBodyId = recipeBodyId;
    }

    public Recipe(String title, int imgid, int recipeBodyId) {
        this.title = title;
        this.imgid = imgid;
        this.recipeBodyId = recipeBodyId;
    }
}
