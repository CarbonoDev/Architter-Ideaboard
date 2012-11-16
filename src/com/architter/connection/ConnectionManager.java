package com.architter.connection;

import android.app.Activity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class ConnectionManager {

	private static final String BASE_URL = "http://api.architter.com/";
	public static final String ASSET_BASE = "http://www.architter.com/ideaboard/image.php?image=";
	private static Activity activity; 
	private static AsyncHttpClient client = new AsyncHttpClient();

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
	
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
	
	public static void logIn(){
		ConnectionManager.post("auth/login", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String arg0) {
				// TODO: Store user
				System.out.println(arg0);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO: Wrong user
				System.out.println("Bad user");
			}
		});
	}

	public static void getIdeas(RequestParams params, AsyncHttpResponseHandler responseHandler) {
		String resource = "ideas";
		ConnectionManager.get(resource, params, responseHandler);
	}
	
	public static Activity getActivity() {
		return activity;
	}

	public static void setActivity(Activity activity) {
		ConnectionManager.activity = activity;
	}
}
