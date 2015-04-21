package com.android.yummlyclient.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.yummlyclient.R;
import com.android.yummlyclient.adapters.RecipesAdapter;
import com.android.yummlyclient.helpers.yummlyclient;
import com.android.yummlyclient.models.Recipe;
import com.android.yummlyclient.fragments.HomeActivityFragment;
import com.loopj.android.http.JsonHttpResponseHandler;

public class RecipeSearchActivity extends Activity {
	private Bundle b;
	private String query;
	private yummlyclient client;
	private GridView gvRecipes;
	private RecipesAdapter adapter;
	final ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_search);
		b = this.getIntent().getExtras();
		query = b.getString("query");
		gvRecipes = (GridView) this.findViewById(R.id.gvSearchdRecipes);
		adapter = new RecipesAdapter(this, recipes);
		gvRecipes.setAdapter(adapter);		
		gvRecipes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent,
					int position, long rowId) {
				Intent i = new Intent(getApplicationContext(), RecipeDetailsActivity.class);
				Recipe imageResult = recipes.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
			
		});
		getSearchedRecipes(query);
	}
	
	public void getSearchedRecipes(String query) {
		client = new yummlyclient();
		client.getSearchedRecipes(query, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int code, JSONObject body) {
				JSONArray recipes = null;
				try{
					recipes = body.getJSONArray("matches");
					ArrayList<Recipe> listRecipes = Recipe.fromJson(recipes);
					for (Recipe recipe : listRecipes){
						adapter.add(recipe);
					}
				}catch(JSONException e){
					e.printStackTrace();
				}
			} 
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe_search, menu);
		return true;
	}

}
