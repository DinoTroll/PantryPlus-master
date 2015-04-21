package com.android.yummlyclient.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;


@Table(name = "FavoriteRecipes1") 
public class FavoriteRecipe extends Model implements Serializable {
	private static final long serialVersionUID = 1L; 
	BaseModel baseModel;
	
	@Column(name = "RecipeId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	//@Column(name = "RecipeId")
	private String recipeId;
	
	@Column(name = "RecipeName")
	private String recipeName;

	@Column(name = "RecipeImageUrl")
	private String smallImageUrl;
	
	@Column(name = "RecipeIngredients")
	private ArrayList<String> ingredients = new ArrayList<String>();
	
	@Column(name = "RecipeTime")
	private String time;
	
	@Column(name = "RecipeRating")
	private String rating;
	
	public FavoriteRecipe() {
		super();
		baseModel = new BaseModel();
	}
	
	public FavoriteRecipe(String name, String url, ArrayList<String> ing, String time, String rating, String id) {
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
		//Log.d("DEBUG", "*******GETTING INGREDIENTS -> ");
    	if (ingredients == null) {
    		ingredients = baseModel.getArrayList("ingredients");
    		//Log.d("DEBUG", "*******GOT INGREDIENTS -> " + ingredients.size());
    		for(String i:ingredients){
    			//Log.d("DEBUG", "FOUND INGREDIENTS -> " + i);
    		}
    	}else{
    		//Log.d("DEBUG", "-------*******GOT INGREDIENTS -> " + ingredients.size());
    		for(String i:ingredients){
    			//Log.d("DEBUG", "------FOUND INGREDIENTS -> " + i);
    		}
    	}
        return ingredients;
	}
	
	public static void add(FavoriteRecipe fRecipe){
		//Log.d("DEBUG", "********Fav recipe ingredients -> " + fRecipe.getIngredients());
		fRecipe.save();
	}
	
	public static List<FavoriteRecipe> getAll() {
        // This is how you execute a query
		List<FavoriteRecipe> listFavRecipes =  new Select()
          .from(FavoriteRecipe.class)
          .execute();
		for(FavoriteRecipe l : listFavRecipes){
			Log.d("DEBUG", "********Fav recipe ID -> " + l.getRecipeId());
		}
		return listFavRecipes;
    }
	/*
	public static List<FavoriteRecipe> deleteAll() {
        // This is how you execute a query
        return new Delete()
          .from(FavoriteRecipe.class)
          .execute();
	}*/
	
	public static FavoriteRecipe fromJson(JSONObject jsonObject) {
		FavoriteRecipe recipe = new FavoriteRecipe();
		recipe.baseModel.jsonObject = jsonObject;
		recipe.getRecipeId();
		recipe.getRecipeName();
		recipe.getRecipePrepTime();
		recipe.getRecipeRating();
		recipe.getRecipeUrl();
		recipe.getIngredients();
		return recipe;
	}
	
	/*
	public static ArrayList<FavoriteRecipe> fromJson(JSONArray jsonArray) {
		recipes = new ArrayList<FavoriteRecipe>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject recipeJson = null;
			try {
				recipeJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			FavoriteRecipe recipe = FavoriteRecipe.fromJson(recipeJson);
			if (recipe != null) {
				recipes.add(recipe);
			}
		}
		
		ActiveAndroid.beginTransaction();
		try{
			for(FavoriteRecipe r : recipes){
				r.save();
			}
			ActiveAndroid.setTransactionSuccessful();
		} finally {
			ActiveAndroid.endTransaction();
		}
		
		return recipes;
	}
	*/

}
