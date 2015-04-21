package com.android.yummlyclient.adapters;

import java.util.ArrayList;






//import android.R;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.yummlyclient.R;
import com.android.yummlyclient.models.Recipe;
import com.loopj.android.image.SmartImageView;

public class RecipesAdapter extends ArrayAdapter<Recipe> {

	public RecipesAdapter(Context context, ArrayList<Recipe> recipes) {
		super(context, 0, recipes);  
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) { 
		Recipe recipe = this.getItem(position);
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
		svImageRecipe.setImageUrl(recipe.getRecipeUrl());	
		Log.d("debug", "URL  : " + recipe.getRecipeUrl());
		tvRecipeName.setText(recipe.getRecipeName());
		
		return rlRecipe;
	}
}
