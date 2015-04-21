package com.android.yummlyclient.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.yummlyclient.R;
import com.android.yummlyclient.activities.FavoriteRecipeActivity;
import com.android.yummlyclient.adapters.RecipesAdapter;
import com.android.yummlyclient.models.FavoriteRecipe;
import com.android.yummlyclient.models.Recipe;
import com.loopj.android.image.SmartImageView;

public class RecipeDetailsTopFragment extends Fragment {
	SmartImageView svImage;
	TextView tvRecipeName;
	ImageView ivFavorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        svImage = (SmartImageView) view.findViewById(R.id.svRecipe);
        tvRecipeName = (TextView) view.findViewById(R.id.tvRecipeName);
        ivFavorite = (ImageView) view.findViewById(R.id.ivFavoriteItem);
        return view;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	final Recipe recipe = (Recipe) getActivity().getIntent().getSerializableExtra("result");
		svImage.setImageUrl(recipe.getRecipeUrl());
		tvRecipeName.setText(recipe.getRecipeName());		
		ivFavorite.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				//FavoriteRecipe favRecipe = (FavoriteRecipe)recipe;
				Intent i = new Intent(getActivity(), FavoriteRecipeActivity.class);
				// Pass svImage and Recipe Name and in activity populate them
				i.putExtra("recipe", recipe);
				startActivity(i);
			}
		});
		
    	super.onActivityCreated(savedInstanceState);
    }
    
    /*
    public void onClickFavorites(MenuItem mi) { 
		Toast.makeText(this, "FAVORITES FOUND", Toast.LENGTH_SHORT).show();
		// Create an activity to display the list of favorite recipes
	    Intent i = new Intent(getApplicationContext(), FavoriteRecipeActivity.class);
	    startActivity(i);
	}*/

}
