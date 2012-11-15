/**
 * 
 */
package com.example.architter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.architter.connection.ConnectionManager;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * @author Marco
 *
 */
public class HomeFragment extends MyFragment implements OnClickListener {
	
	Button arquitectureButton, interiorButton, furnitureButton, setsButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View homeView = inflater.inflate(R.layout.home_fragment, container, false);
		
		ConnectionManager.getIdeas(1, new  JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray ideas) {
				loadElements(ideas);
			}
		});
		return homeView;
	}
	
	public void loadElements(JSONArray ideas){
		View homeView = getView();
		LinearLayout column1 = (LinearLayout) homeView.findViewById(R.id.linear2);
		LinearLayout column2 = (LinearLayout) homeView.findViewById(R.id.linear3);
		arquitectureButton = (Button) homeView.findViewById(R.id.arquitectureButton);
		arquitectureButton.setSelected(true);
		interiorButton = (Button) homeView.findViewById(R.id.interiorButton);
		furnitureButton = (Button) homeView.findViewById(R.id.furnitureButton);
		setsButton = (Button) homeView.findViewById(R.id.setsButton);
		arquitectureButton.setOnClickListener(this);
		interiorButton.setOnClickListener(this);
		furnitureButton.setOnClickListener(this);
		setsButton.setOnClickListener(this);
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		IdeaWidget idea;
		int column = 1;
		
		for (int i = 0; i < ideas.length(); i++) {
			try {
				JSONObject invention = ideas.getJSONObject(i);
				String img = invention.getString("img");
				if(img.startsWith("./")) {
					img = img.substring(2);
				};
				img = ConnectionManager.ASSET_BASE + img;
				String description = invention.getString("descr");
				String user = invention.getString("iduser");
				idea = (IdeaWidget) inflater.inflate(R.layout.idea_component, column1, false);
				idea.setImage(img);
				idea.setDescription(description+user);
				idea.setListener(this);
				switch (column) {
				case 1:
					column1.addView(idea);
					column++;
					break;
				case 2:
					column2.addView(idea);
					column++;
				default:
					column = 1;
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
				break;
			}
			
		}
	}
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.arquitectureButton: {
				interiorButton.setSelected(false);
				furnitureButton.setSelected(false);
				setsButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.interiorButton: {
				arquitectureButton.setSelected(false);
				furnitureButton.setSelected(false);
				setsButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.furnitureButton: {
				arquitectureButton.setSelected(false);
				interiorButton.setSelected(false);
				setsButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.setsButton: {
				arquitectureButton.setSelected(false);
				interiorButton.setSelected(false);
				furnitureButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.image: {
				IdeaWidget i = (IdeaWidget) v.getParent();
				int id = i.getIdeaId();
				IdeaViewFragment newFragment = new IdeaViewFragment();
				newFragment.setIdea_id(id);
				loadFragment(newFragment, this);
				break;
			}
			case R.id.archthis: {
				IdeaWidget i = (IdeaWidget) v.getParent();
				int id = i.getIdeaId();
				ArchThisFragment newFragment = new ArchThisFragment();
				newFragment.setIdea_id(id);
				loadFragment(newFragment, this);
				break;
			}
			default: {
				break;
			}
		}
	 }


}
