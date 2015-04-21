package com.android.yummlyclient.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.yummlyclient.R;
import com.android.yummlyclient.activities.RecipeDetailsActivity;
import com.android.yummlyclient.adapters.RecipeDetailsAdapter;
import com.android.yummlyclient.adapters.RecipesAdapter;
import com.android.yummlyclient.models.Recipe;

public class RecipeDetailsBottomFragment extends Fragment {
	ListView lvIngredients;
	RecipeDetailsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_bottom, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		lvIngredients = (ListView) getActivity().findViewById(R.id.lvIngredients);
		final ArrayList<String> ingredients = new ArrayList<String>();
		Recipe recipe = (Recipe) getActivity().getIntent().getSerializableExtra("result");
		ingredients.addAll(recipe.getIngredients());
		adapter = new RecipeDetailsAdapter(getActivity(), ingredients);
		lvIngredients.setAdapter(adapter);		
	}
}
