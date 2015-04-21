package com.android.yummlyclient.adapters;

import java.util.ArrayList;

import com.android.yummlyclient.R;
import com.android.yummlyclient.models.FavoriteRecipe;
import com.android.yummlyclient.models.Recipe;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FavoriteRecipesAdapter extends ArrayAdapter<FavoriteRecipe> {
	public FavoriteRecipesAdapter(Context context, ArrayList<FavoriteRecipe> favRecipes) {
		super(context, 0, favRecipes);  
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) { 
		FavoriteRecipe favRecipe = this.getItem(position);
		RelativeLayout rlRecipe;
		SmartImageView svImageRecipe;
		TextView tvRecipeName;
		
		if(convertView == null){
			//LayoutInflater inflater = LayoutInflater.from(getContext());
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rlRecipe = (RelativeLayout) inflater.inflate(R.layout.item_recipe, parent, false);
			
		} else {
			rlRecipe = (RelativeLayout) convertView;
		}
		
		svImageRecipe = (SmartImageView) rlRecipe.findViewById(R.id.ivRecipe);
		tvRecipeName = (TextView) rlRecipe.findViewById(R.id.tvName);
		svImageRecipe.setImageUrl(favRecipe.getRecipeUrl());	
		Log.d("debug", "URL  : " + favRecipe.getRecipeUrl());
		tvRecipeName.setText(favRecipe.getRecipeName());
		
		return rlRecipe;
		
	}
}
