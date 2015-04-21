package com.android.yummlyclient.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.yummlyclient.R;
import com.android.yummlyclient.adapters.RecipesAdapter;
import com.android.yummlyclient.fragments.RecipeDetailsBottomFragment;
import com.android.yummlyclient.fragments.RecipeDetailsMiddleFragment;
import com.android.yummlyclient.fragments.RecipeDetailsTopFragment;
import com.android.yummlyclient.helpers.yummlyclient;
import com.android.yummlyclient.models.Recipe;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

public class RecipeDetailsActivity extends FragmentActivity {
	yummlyclient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_details);
		/*
		RecipeDetailsTopFragment topFragment = new RecipeDetailsTopFragment();//create the fragment instance for the top fragment
        RecipeDetailsMiddleFragment middleFragment = new RecipeDetailsMiddleFragment();//create the fragment instance for the middle fragment
        RecipeDetailsBottomFragment bottomFragment = new RecipeDetailsBottomFragment();//create the fragment instance for the bottom fragment

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();//create an instance of fragment manager

        FragmentTransaction transaction=manager.beginTransaction();//create an instance of Fragment-transaction

        transaction.add(R.id.flTopFragment, topFragment, "Frag_Top_tag");
        transaction.add(R.id.flMiddleFragment, middleFragment, "Frag_Bottom_tag");
        //transaction.add(R.id.flBottomFragment, bottomFragment, "Frag_Middle_tag");
        
        transaction.commit();*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case android.R.id.home:
            // app icon in action bar clicked; go home
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}

}
