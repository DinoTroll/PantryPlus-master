package com.android.yummlyclient.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Recipes1")
public class Recipe extends Model implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Recipe> recipes;
	BaseModel baseModel;
	
	@Column(name = "RecipeId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	//@Column(name = "RecipeId")
	private String recipeId;
	
	@Column(name = "RecipeName")
	private String recipeName;

	@Column(name = "RecipeImageUrl")
	private String smallImageUrl;
	
	@Column(name = "RecipeIngredients")
	private ArrayList<String> ingredients;
	
	@Column(name = "RecipeTime")
	private String time;
	
	@Column(name = "RecipeRating")
	private String rating;
	
	public Recipe() {
		super();
		baseModel = new BaseModel();
	}
	
	public Recipe(String name, String url, ArrayList<String> ing, String time, String rating, String id) {
		super();
		this.recipeId = id;
		this.recipeName = name;
		this.smallImageUrl = url;
		this.ingredients = ing;
		this.time = time;
		this.rating = rating;
		baseModel = new BaseModel();
	}
	
	public String getRecipeId() {
		if (recipeId == null) {
            recipeId = baseModel.getString("id");
    	}
        return recipeName;
	}

	public String getRecipeName() {
    	if (recipeName == null) {
            recipeName = baseModel.getString("recipeName");
    	}
        return recipeName;
    }

	public String getRecipeUrl() {
    	if (smallImageUrl == null) {
    		ArrayList<String> imageUrls = baseModel.getArrayList("smallImageUrls");
    		for(String s : imageUrls){
    			Log.d("DEBUG", "smallImageUrl -> " + s);
    		}
    		
    		smallImageUrl = imageUrls.get(0).toString();
    	}
        return smallImageUrl;
    }

	public String getRecipeRating() {
    	if (rating == null) {
    		rating = baseModel.getString("rating");
    	}
        return rating;
    }
	
	public String getRecipePrepTime() {
    	if (time == null) {
    		time = baseModel.getString("totalTimeInSeconds");
    	}
        return time;
    }
	
	public ArrayList<String> getIngredients() {
    	if (ingredients == null) {
    		ingredients = baseModel.getArrayList("ingredients");
    	}
        return ingredients;
	}
	
	public Recipe getJsonObject(FavoriteRecipe fr){
		Recipe r = new Recipe();
		return r;
	}

	public static Recipe fromJson(JSONObject jsonObject) {
		Recipe recipe = new Recipe();
		recipe.baseModel.jsonObject = jsonObject;
		recipe.getRecipeId();
		recipe.getRecipeName();
		recipe.getRecipePrepTime();
		recipe.getRecipeRating();
		recipe.getRecipeUrl();
		recipe.getIngredients();
		return recipe;
	}
	
	public static ArrayList<Recipe> fromJson(JSONArray jsonArray) {
		recipes = new ArrayList<Recipe>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject recipeJson = null;
			try {
				recipeJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Recipe recipe = Recipe.fromJson(recipeJson);
			if (recipe != null) {
				recipes.add(recipe);
			}
		}
		
		ActiveAndroid.beginTransaction();
		try{
			for(Recipe r : recipes){
				r.save();
			}
			ActiveAndroid.setTransactionSuccessful();
		} finally {
			ActiveAndroid.endTransaction();
		}
		
		return recipes;
	}
	
	public static List<Recipe> getAll() {
        // This is how you execute a query
        return new Select()
          .from(Recipe.class)
          .execute();
    }

}	
	