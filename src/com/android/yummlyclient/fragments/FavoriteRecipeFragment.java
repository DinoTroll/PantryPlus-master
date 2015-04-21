package com.android.yummlyclient.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.android.yummlyclient.R;
import com.android.yummlyclient.activities.RecipeDetailsActivity;
import com.android.yummlyclient.adapters.FavoriteRecipesAdapter;
import com.android.yummlyclient.helpers.yummlyclient;
import com.android.yummlyclient.models.FavoriteRecipe;
import com.android.yummlyclient.models.Recipe;

public class FavoriteRecipeFragment extends Fragment {
	private yummlyclient client;
	private FavoriteRecipesAdapter adapter;
	//final ArrayList<FavoriteRecipe> favRecipes = new ArrayList<FavoriteRecipe>();
	
	//private static Recipe recipe = new Recipe();
	final static ArrayList<FavoriteRecipe> favoriteRecipes = new ArrayList<FavoriteRecipe>();
	List<FavoriteRecipe> listFavRecipes = new ArrayList<FavoriteRecipe>();
	private GridView gvFavoriteRecipes; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_favorite_recipe_activity, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		gvFavoriteRecipes = (GridView) getActivity().findViewById(R.id.gvFavoriteRecipes);		
		final Recipe recipe = (Recipe) getActivity().getIntent().getSerializableExtra("recipe");
		
		FavoriteRecipe favRecipe = new FavoriteRecipe(recipe.getRecipeName(), recipe.getRecipeUrl(), recipe.getIngredients(), recipe.getRecipePrepTime(), recipe.getRecipeRating(), recipe.getRecipeId());
		FavoriteRecipe.add(favRecipe);

		listFavRecipes = FavoriteRecipe.getAll();
		for(FavoriteRecipe listFavItem : listFavRecipes){
			Log.d("DEBUG", "********Fav recipe -> " + listFavItem.getRecipeName());
			Log.d("DEBUG", "********Fav recipe ING -> " + listFavItem.getIngredients());			
		}
		adapter = new FavoriteRecipesAdapter(getActivity().getBaseContext(), (ArrayList<FavoriteRecipe>)listFavRecipes);
		gvFavoriteRecipes.setAdapter(adapter);	
		gvFavoriteRecipes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent,
					int position, long rowId) {
				FavoriteRecipe imageResult = listFavRecipes.get(position);
				
				Recipe r = new Recipe(imageResult.getRecipeName(), imageResult.getRecipeUrl(), imageResult.getIngredients(), imageResult.getRecipePrepTime(), imageResult.getRecipeRating(), imageResult.getRecipeId());
				Intent i = new Intent(getActivity(), RecipeDetailsActivity.class);				
				i.putExtra("result", r);
				startActivity(i);
			}			
		});

	}
	
}
