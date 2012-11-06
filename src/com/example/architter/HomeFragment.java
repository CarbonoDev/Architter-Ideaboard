/**
 * 
 */
package com.example.architter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author Marco
 *
 */
public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View homeView = inflater.inflate(R.layout.home_fragment, container, false);
		getActivity().setContentView(R.layout.home_fragment);
		LinearLayout column1 = (LinearLayout) getActivity().findViewById(R.id.linear2);
		LinearLayout column2 = (LinearLayout) getActivity().findViewById(R.id.linear3);
		IdeaWidget idea;
		for(int i = 0; i < 200; i++){
			idea = (IdeaWidget) inflater.inflate(R.layout.idea_component, column1, false);
			idea.setDescription("Ya jala la imagen :)");
			if(i % 2 == 0) {
				idea.setImage("http://www.architter.com/images/Paga_Todo____usoarquitectura___J_7.jpg");
			} else {
				idea.setImage("http://www.architter.com/images/Paga_Todo____usoarquitectura___K_2.jpg");			
			}
			column1.addView(idea);
		}
		for(int i = 0; i < 200; i++){
			idea = (IdeaWidget) inflater.inflate(R.layout.idea_component, column1, false);
			idea.setDescription("Ya jala la imagen :)");
			if(i % 2 == 0) {
				idea.setImage("http://www.architter.com/images/Paga_Todo____usoarquitectura___C_2.jpg");
			} else {
				idea.setImage("http://www.architter.com/images/Paga_Todo____usoarquitectura___M_2.jpg");
			}
			column2.addView(idea);
		}
		return homeView;
	}
}
