package com.android.yummlyclient.adapters;

import java.util.ArrayList;

import android.R.color;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.yummlyclient.R;
import com.android.yummlyclient.models.Recipe;
import com.loopj.android.image.SmartImageView;

public class RecipeDetailsAdapter extends ArrayAdapter<String> {
	
	public RecipeDetailsAdapter(Context context, ArrayList<String> ingredients) {
		super(context, 0, ingredients);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		String ing = this.getItem(position);
		LinearLayout llIng;
		TextView tvIngName;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			llIng = (LinearLayout) inflater.inflate(R.layout.item_ingredient, parent, false);
			
		} else {
			llIng = (LinearLayout) convertView;
		}
		
		tvIngName = (TextView) llIng.findViewById(R.id.tvIngredient);
		Log.d("debug", "ING  : " + ing);
		tvIngName.setText(ing);		
		return llIng;
	}
}
