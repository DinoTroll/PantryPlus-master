package com.android.yummlyclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.yummlyclient.R;
import com.android.yummlyclient.models.Recipe;

public class RecipeDetailsMiddleFragment extends Fragment {
    TextView tvTime;
    TextView tvRating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_middle, container, false);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvRating = (TextView) view.findViewById(R.id.tvRating);
        return view;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	Recipe recipe = (Recipe) getActivity().getIntent().getSerializableExtra("result");
    	tvTime.setText(recipe.getRecipePrepTime());
    	tvRating.setText(recipe.getRecipeRating());
    	super.onActivityCreated(savedInstanceState);
    }
}
