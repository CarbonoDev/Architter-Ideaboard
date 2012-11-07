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
public class IdeaViewFragment extends Fragment {
	private int id;
	private String main_image;
	
	public IdeaViewFragment(int id) {
		super();
		this.id = id;
		loadData();		
	}

	public IdeaViewFragment() {
		this.id = 0;
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
}
