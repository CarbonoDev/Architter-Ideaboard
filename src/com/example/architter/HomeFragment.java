/**
 * 
 */
package com.example.architter;

import com.architter.widgets.IdeasScroll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author Marco
 *
 */
public class HomeFragment extends MyFragment implements OnClickListener {
	
	Button arquitectureButton, interiorButton, furnitureButton, setsButton;
	IdeasScroll ideasScroll;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View homeView = inflater.inflate(R.layout.home_fragment, container, false);
		arquitectureButton = (Button) homeView.findViewById(R.id.arquitectureButton);
		arquitectureButton.setSelected(true);
		interiorButton = (Button) homeView.findViewById(R.id.interiorButton);
		furnitureButton = (Button) homeView.findViewById(R.id.furnitureButton);
		setsButton = (Button) homeView.findViewById(R.id.setsButton);
		ideasScroll = (IdeasScroll) homeView.findViewById(R.id.scrollView1);
		arquitectureButton.setOnClickListener(this);
		interiorButton.setOnClickListener(this);
		furnitureButton.setOnClickListener(this);
		setsButton.setOnClickListener(this);	
		ideasScroll.setFragment(this);
		return homeView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ideasScroll.loadIdeas("architecture", true);
	}
	
	
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.arquitectureButton: {
				interiorButton.setSelected(false);
				furnitureButton.setSelected(false);
				setsButton.setSelected(false);
				v.setSelected(true);
				ideasScroll.loadIdeas("architecture", true);
				break;
			}
			case R.id.interiorButton: {
				arquitectureButton.setSelected(false);
				furnitureButton.setSelected(false);
				setsButton.setSelected(false);
				v.setSelected(true);
				ideasScroll.loadIdeas("interior", true);
				break;
			}
			case R.id.furnitureButton: {
				arquitectureButton.setSelected(false);
				interiorButton.setSelected(false);
				setsButton.setSelected(false);
				v.setSelected(true);
				ideasScroll.loadIdeas("furniture", true);
				break;
			}
			case R.id.setsButton: {
				arquitectureButton.setSelected(false);
				interiorButton.setSelected(false);
				furnitureButton.setSelected(false);
				v.setSelected(true);
				ideasScroll.loadIdeas("sets", true);
				break;
			}
			default: {
				break;
			}
		}
	 }


}
