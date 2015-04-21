package com.android.yummlyclient.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Vector;

import com.android.yummlyclient.R;


public class PantryFragment extends Activity {
	
	
	

    private static Context context;
    private static FileOutputStream outputStream;
    private static FileInputStream inputStream;
    private static Vector<String> pantryItems;
    private static PantryAdapter pantry;
    private static AlertDialog.Builder alertDialogBuilder;
    private static ListView pantryView;
    private static Vector<String> searchItems;
    private static int index = 0;
    private static ArrayList<Integer> checked;
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_pantry);
   
        pantryItems = new Vector<String>();

        //searchItems = new Vector<String>();

        context = PantryFragment.this;

        final EditText ingredient = (EditText) findViewById(R.id.ingredient);
        final Button searchSelected = (Button) findViewById(R.id.searchSelected);

        pantry = new PantryAdapter(getBaseContext(), pantryItems);

        pantryView = (ListView) findViewById(R.id.pantryListView);


        pantryView.setAdapter(pantry);
        pantryView.setVisibility(View.GONE);

        final File pantryFile = new File(context.getFilesDir(), "pantrylist.txt");

        // If file exists take whats in the file and fill the vector


            // Grabs list from text file
            /* Need to read each line from file and store in
             * Vector.
             */

        if (pantryFile.exists()) {
            try {
                inputStream = openFileInput("pantrylist.txt");
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);

                StringBuilder stringBuilder = new StringBuilder();
                String item;

                while ((item = br.readLine()) != null) {
                    pantryItems.add(item);
                }
                inputStream.close();

                    //ingredient.setText(pantryItems.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
           // ingredient.setText("File Does Not Exist");
        }


        /* GET PREVIOUS SIZE OF VECTOR THEN NEWSIZE FROM ADDING ITEMS
         * INCREMENT FROM THAT POSITION AND USE pantryItems.get(i)
         */




        //debugging
        //ingredient.setText(pantryItems.get(0));

        Button exit = (Button)findViewById(R.id.cancelbtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    outputStream = openFileOutput("pantrylist.txt", Context.MODE_PRIVATE);
                    for (int i = 0; i < pantryItems.size(); i++) {
                        outputStream.write((pantryItems.get(i)+"\n").getBytes());
                        //outputStream.write("\n".getBytes());
                    }
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //pantryFile.delete();
                finish();
            }
        });

        final Button viewPantry = (Button)findViewById(R.id.view);
        viewPantry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                 // If statement for dismiss pantry/view settext
                 if (viewPantry.getText().equals("View Pantry")) {
                     pantryView.setVisibility(View.VISIBLE);
                     viewPantry.setText("Dismiss");
                 }
                 else {
                     pantryView.setVisibility(View.GONE);
                     viewPantry.setText("View Pantry");
                 }

            }
        });

        // Adds ingredient to vector not text file
        Button Add = (Button)findViewById(R.id.add);
        Add.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                String txt = ingredient.getText().toString();
                ingredient.setText("");

                pantryItems.add(txt);
                pantryView.setAdapter(pantry);
            }
        });

        // Searches for checked items in the pantry
        searchSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ingredientSearch = "";
             for (int i = 0; i < searchItems.size(); i++) {
               if (i != searchItems.size()-1)
                  ingredientSearch += searchItems.get(i) + " ";
               else
                  ingredientSearch += searchItems.get(i);
             }
                ingredient.setText(ingredientSearch);
            }

        });

        
    }


    public class PantryAdapter extends ArrayAdapter<String> {


        public PantryAdapter(Context context, Vector<String> items) {
            super(context, 0, items);
            searchItems = new Vector<String>();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //final String item = getItem(position);
            pantryView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.pantry_list_item, parent, false);
            }

            final EditText editName = (EditText) convertView.findViewById(R.id.editName);
            final CheckBox selected = (CheckBox) convertView.findViewById(R.id.checkBox);
            final Button rename = (Button) convertView.findViewById(R.id.rename);
            final Button remove = (Button) convertView.findViewById(R.id.remove);

            final TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
            itemName.setText(pantryItems.get(position));

            rename.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v) {
                   if (rename.getText().equals("Rename")) {
                       itemName.setVisibility(View.GONE);
                       editName.setVisibility(View.VISIBLE);
                       rename.setText("Done");
                       remove.setText("Cancel");
                       editName.setSelection(0);

                   }
                   else {
                       // SET pantryItems[i] = editText
                       String oldItem = itemName.getText().toString();
                       String newItem = editName.getText().toString();
                       index = pantryItems.indexOf(oldItem);
                       pantryItems.set(index, newItem);

                       itemName.setVisibility(View.VISIBLE);
                       editName.setVisibility(View.GONE);
                       rename.setText("Rename");
                       remove.setText("Remove");
                       pantry.notifyDataSetChanged();

                   }
                }
            });

            // Add search and remove duplicates buttons?

            remove.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if (remove.getText().equals("Cancel")){
                        rename.setText("Rename");
                        remove.setText("Remove");
                        itemName.setVisibility(View.VISIBLE);
                        editName.setText("");
                        editName.setVisibility(View.GONE);

                    }
                    else {

                        String oldItem = itemName.getText().toString();
                        index = pantryItems.indexOf(oldItem);

                        context = PantryFragment.this;
                        //context = getApplicationContext();
                        
                        alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Pantry Item Removal");

                        alertDialogBuilder
                                .setMessage("Do you want to remove this item?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if(selected.isChecked()){
                                            int ind = searchItems.indexOf(itemName.getText().toString());
                                            searchItems.remove(ind);
                                            selected.setChecked(false);
                                        }
                                        pantryItems.remove(index);
                                        pantry.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }

                }
            });

            selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pantry.notifyDataSetChanged();
                    if (selected.isChecked()){
                        searchItems.add(itemName.getText().toString());
                    }
                    else {
                        int ind = searchItems.indexOf(itemName.getText().toString());
                        searchItems.remove(ind);
                    }
                }
            });

            return convertView;
        }
    }

}
