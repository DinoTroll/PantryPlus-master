package com.android.yummlyclient.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.Toast;

import com.android.yummlyclient.R;
import com.android.yummlyclient.adapters.FavoriteRecipesAdapter;
import com.android.yummlyclient.adapters.RecipesAdapter;
import com.android.yummlyclient.models.FavoriteRecipe;
import com.android.yummlyclient.models.Recipe;

public class FavoriteRecipeActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_recipe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favorite_recipe, menu);
		return true;
	}

}
