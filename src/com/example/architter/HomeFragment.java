/**
 * 
 */
package com.example.architter;

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
		LinearLayout column1 = (LinearLayout) homeView.findViewById(R.id.linear2);
		LinearLayout column2 = (LinearLayout) homeView.findViewById(R.id.linear3);
		arquitectureButton = (Button) homeView.findViewById(R.id.arquitectureButton);
		interiorButton = (Button) homeView.findViewById(R.id.interiorButton);
		furnitureButton = (Button) homeView.findViewById(R.id.furnitureButton);
		setsButton = (Button) homeView.findViewById(R.id.setsButton);
		arquitectureButton.setOnClickListener(this);
		interiorButton.setOnClickListener(this);
		furnitureButton.setOnClickListener(this);
		setsButton.setOnClickListener(this);
		IdeaWidget idea;
		for(int i = 0; i < 20; i++){
			idea = (IdeaWidget) inflater.inflate(R.layout.idea_component, column1, false);
			if(i % 2 == 0) {
				idea.setImage("http://www.architter.com/images/Paga_Todo____usoarquitectura___J_7.jpg");
			} else {
				idea.setImage("http://www.architter.com/images/Paga_Todo____usoarquitectura___K_2.jpg");			
			}
			idea.setListener(this);
			idea.setDescription("left "+i);
			column1.addView(idea);
		}
		for(int i = 0; i < 20; i++){
			idea = (IdeaWidget) inflater.inflate(R.layout.idea_component, column1, false);
			idea.setDescription("Ya jala la imagen :)");
			if(i % 2 == 0) {
				idea.setImage("http://www.architter.com/images/Paga_Todo____usoarquitectura___C_2.jpg");
			} else {
				idea.setImage("http://www.architter.com/images/Paga_Todo____usoarquitectura___M_2.jpg");
			}
			idea.setListener(this);
			idea.setDescription("right "+i);
			column2.addView(idea);
		}
		return homeView;
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
