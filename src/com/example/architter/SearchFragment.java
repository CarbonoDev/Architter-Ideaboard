/**
 * 
 */
package com.example.architter;

import com.architter.widgets.IdeaWidget;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * @author Marco
 *
 */
public class SearchFragment extends MyFragment implements OnItemClickListener, OnClickListener {
	
	ListView tagsListView;
	RelativeLayout containerView;
	ScrollView scrollView;
	LinearLayout ideaContainer, column1, column2;
	String [] tagsArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View searchView = inflater.inflate(R.layout.search_fragment, container, false);
		containerView = (RelativeLayout) searchView.findViewById(R.id.containerSearchView);
		tagsListView = (ListView) searchView.findViewById(R.id.tagsListView);
		tagsListView.setOnItemClickListener(this);
		tagsArray = getResources().getStringArray(R.array.Tags);
		return searchView;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		System.out.println(tagsArray[arg2]);
		displayIdeas();
	}
	
	public void displayIdeas() {
		tagsListView.setVisibility(View.GONE);
		scrollView = new ScrollView(getActivity());
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
		        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		lp.addRule(RelativeLayout.BELOW, R.id.searchContainer);
		containerView.addView(scrollView, lp);
		ideaContainer = new LinearLayout(getActivity()); 
		ideaContainer.setOrientation(LinearLayout.HORIZONTAL); 
		ideaContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		ideaContainer.setGravity(Gravity.CENTER);
		scrollView.addView(ideaContainer);
		column1 = new LinearLayout(getActivity());
		column1.setOrientation(LinearLayout.VERTICAL);  
		column1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		ideaContainer.addView(column1);
		column2 = new LinearLayout(getActivity());
		column2.setOrientation(LinearLayout.VERTICAL);  
		column2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		ideaContainer.addView(column2);
		IdeaWidget idea;
		LayoutInflater inflater = LayoutInflater.from(getActivity());
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
	}
	
	public void onClick(View v) {
		
	}
	
}
