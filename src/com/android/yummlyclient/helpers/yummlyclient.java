package com.android.yummlyclient.helpers;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class yummlyclient {
	private final String BASE_URL = "https://api.yummly.com/v1/";
	private final String APP_ID = "9e130aae";
	private final String APP_KEY = "2fa442e391b6e5949bfd2e632165865e";
	private String baseUrl;
	
	private AsyncHttpClient client;
	
	public yummlyclient(){
		
		baseUrl = BASE_URL + "api/recipes";
		this.client = new AsyncHttpClient();
		client.addHeader("X-Yummly-App-ID", "9e130aae");
		client.addHeader("X-Yummly-App-Key", "2fa442e391b6e5949bfd2e632165865e");
	}

	private String getBaseUrl(){
		String url = BASE_URL + "?_app_id=" + APP_ID + "&_app_key=" + APP_KEY;
		return url;
	}
	
	public void getHomeRecipes(JsonHttpResponseHandler handler){
		client.get(baseUrl, handler);
	}
	
	public void getSearchedRecipes(String query, JsonHttpResponseHandler handler){
		String searchQuery = query.replaceAll("(\\W|_)+", "+");
		String searchUrl = baseUrl + "?q=" + searchQuery;
		client.get(searchUrl, handler);
	}
}
