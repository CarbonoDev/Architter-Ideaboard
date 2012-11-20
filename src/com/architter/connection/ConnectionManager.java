package com.architter.connection;

import android.app.Activity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class ConnectionManager {

	private static final String BASE_URL = "http://api.architter.com/";
	public static final String ASSET_BASE = "http://www.architter.com/ideaboard/image.php?image=";
	public static final String AVATAR_BASE = "http://www.architter.com/avatars/";
	public static String username = "Username";
	public static String realname = "Jhon Doe";
	public static String avatar = "";
	private static Activity activity; 
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static String getUserAvatar(){
		return ConnectionManager.getUserAvatar(ConnectionManager.avatar);
	}
	
	
	public static String getUserAvatar(String avatar) {
		if(avatar.startsWith("http")) {
			return avatar;
		} else {
			return ConnectionManager.AVATAR_BASE + avatar;
		}
	}


	public static void get(String url, RequestParams params,
		AsyncHttpResponseHandler responseHandler) {
			PersistentCookieStore myCookieStore = new PersistentCookieStore(activity);
			client.setCookieStore(myCookieStore);
			String fullUrl = getAbsoluteUrl(url);
			client.get(fullUrl, params, responseHandler);		
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
			PersistentCookieStore myCookieStore = new PersistentCookieStore(activity);
			client.setCookieStore(myCookieStore);
			client.post(getAbsoluteUrl(url), params, responseHandler);
		
	}

	public static void get(String url, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		ConnectionManager.get(url, params, responseHandler);
	}

	public static void post(String url, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		ConnectionManager.post(url, params, responseHandler);
	}
	
	public static void delete(String url,
			JsonHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		params.put("_method", "DELETE");
		ConnectionManager.post(url, params, responseHandler);		
	}
	
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
	
	public static void logIn(String user, String password, AsyncHttpResponseHandler responseHandler){
		String resource = "auth/login";
		RequestParams params = new RequestParams();
		params.put("username", user);
		params.put("password", password);
		params.put("remember", "true");
		ConnectionManager.post(resource, params, responseHandler);
	}
	
	public static void logOut(AsyncHttpResponseHandler response){
		String resource = "auth/logout";
		RequestParams params = new RequestParams();
		ConnectionManager.post(resource, params, response);
	}

	public static void getIdea(RequestParams params, AsyncHttpResponseHandler responseHandler, int id) {
		String resource = "ideas/"+id;
		ConnectionManager.get(resource, params, responseHandler);		
	}
	
	public static void getIdeas(RequestParams params, AsyncHttpResponseHandler responseHandler) {
		String resource = "ideas";
		ConnectionManager.get(resource, params, responseHandler);
	}
	public static void getUserIdeas(RequestParams params, AsyncHttpResponseHandler responseHandler) {
		String resource = "user/ideas";
		ConnectionManager.get(resource, params, responseHandler);
	}
	public static void getTagsIdeas(RequestParams params, AsyncHttpResponseHandler responseHandler) {
		String resource = "user/tags/ideas";
		ConnectionManager.get(resource, params, responseHandler);
	}

	public static void enlighten(RequestParams params,
			JsonHttpResponseHandler responseHandler) {
		String resource = "user/ideas"; 
		ConnectionManager.post(resource, params, responseHandler);
	}
		
	public static Activity getActivity() {
		return activity;
	}

	public static void setActivity(Activity activity) {
		client.setTimeout(5000);
		ConnectionManager.activity = activity;
	}

	public static String getImgUrl(String img) {
		if(!img.startsWith("./ideaboardImages/")) {
			img = "./ideaboardImages/" + img;
		};
		img = ASSET_BASE+img;
		return img;
	}

	public static void deleteIdea(
			JsonHttpResponseHandler jsonHttpResponseHandler, int ideaId) {
		String resource = "user/ideas/"+ideaId;
		ConnectionManager.delete(resource, jsonHttpResponseHandler);
	}		
}
