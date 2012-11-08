/**
 * 
 */
package com.example.architter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Marco
 *
 */
public class IdeaViewFragment extends MyFragment {
	private int idea_id;
	private String main_image;
	
	public IdeaViewFragment() {
		this.setIdea_id(0);
		loadData();
	}

	private void loadData() {
		main_image = "http://www.architter.com/images/KUU_black_BASIC_3.jpg";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		IdeaView idea = (IdeaView) inflater.inflate(R.layout.idea_view, container, false);
		idea.setMainImage(main_image);
		return idea;
	}

	public int getIdea_id() {
		return idea_id;
	}

	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}
}
