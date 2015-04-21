package com.android.yummlyclient.fragments;

import java.util.ArrayList;
import java.util.zip.Inflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.yummlyclient.R;
import com.android.yummlyclient.activities.FavoriteRecipeActivity;
import com.android.yummlyclient.activities.RecipeDetailsActivity;
import com.android.yummlyclient.adapters.RecipesAdapter;
import com.android.yummlyclient.helpers.yummlyclient;
import com.android.yummlyclient.models.Recipe;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivityFragment extends Fragment {
	
	private yummlyclient client;
	private GridView gvRecipes;
	private RecipesAdapter adapter;
	final ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home_activity, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		gvRecipes = (GridView) getActivity().findViewById(R.id.gvRecipes);
		adapter = new RecipesAdapter(getActivity(), recipes);
		gvRecipes.setAdapter(adapter);	
		gvRecipes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent,
					int position, long rowId) {
				Intent i = new Intent(getActivity(), RecipeDetailsActivity.class);
				Recipe imageResult = recipes.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}			
		});
		getRecipes();
	}
	
	public void getRecipes() {
		client = new yummlyclient();
		client.getHomeRecipes(new JsonHttpResponseHandler(){
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
	
	

	/*
	@Override
	public void onClick(View v) {
		if(v == gvRecipes){
			Intent i = new Intent(getActivity(), RecipeDetailsActivity.class);
			Recipe imageResult = recipes.get(position);
			i.putExtra("result", imageResult);
			startActivity(i);
		}
		
	}*/
}
