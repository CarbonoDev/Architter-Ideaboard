package com.example.architter;

import org.json.JSONObject;

import com.architter.connection.ConnectionManager;
import com.architter.widgets.ArchThisView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

public class ArchThisFragment extends MyFragment implements OnClickListener  {
	private int idea_id;
	private String main_image;
	private ArchThisView view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = (ArchThisView) inflater.inflate(R.layout.arch_this, container, false);
		view.setMainImage(main_image);
		view.setListener(this);
		return view;
	}

	public int getIdeaId() {
		return idea_id;
	}

	public void setIdeaId(int idea_id) {
		this.idea_id = idea_id;
		loadIdea();
	}

	private void loadIdea() {
		RequestParams params = new RequestParams();
 		ConnectionManager.getIdea(params, new  JsonHttpResponseHandler() {
			@Override
			public void onFailure(Throwable arg0) {
				System.out.println(":(");
			}
			@Override
			public void onSuccess(JSONObject response) {
					System.out.println("idea loaded");
					view.loadIdea(response);
			}
		}, getIdeaId());
	}

	public void onClick(View v) {
		InputMethodManager inputManager;
		switch (v.getId()) {
		case R.id.shareLayout:
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			break;
		case R.id.saveButton:
			inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); 
			inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
               InputMethodManager.HIDE_NOT_ALWAYS);
			RequestParams params = new RequestParams();
			params.put("id", ""+idea_id);
			params.put("description", view.getDescription());
			ConnectionManager.enlighten(params, new JsonHttpResponseHandler() {
				@Override
				public void onFailure(Throwable arg0) {
					
				}
				@Override
				public void onSuccess(JSONObject arg0) {
					getFragmentManager().popBackStack();
				}
			});
			break;
		case R.id.cancel:
			inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); 
			inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
               InputMethodManager.HIDE_NOT_ALWAYS);
			getFragmentManager().popBackStack();
			break;
		default:
			break;
		}
	}
}
