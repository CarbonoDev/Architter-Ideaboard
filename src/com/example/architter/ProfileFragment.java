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
public class ProfileFragment extends MyFragment implements OnClickListener {

	Button ideasButton, setsButton, projectsButton;
	IdeasScroll ideasScroll;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View profileView = inflater.inflate(R.layout.profile_fragment, container, false);
		
		ideasButton = (Button) profileView.findViewById(R.id.ideasButton);
		ideasButton.setSelected(true);
		setsButton = (Button) profileView.findViewById(R.id.setsButton);
		projectsButton = (Button) profileView.findViewById(R.id.projectsButton);
		ideasButton.setOnClickListener(this);
		setsButton.setOnClickListener(this);
		projectsButton.setOnClickListener(this);
		ideasScroll = (IdeasScroll) profileView.findViewById(R.id.scrollView1);
		ideasScroll.loadUserIdeas("", 200386, true);
		return profileView;
	}

	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.ideasButton: {
				setsButton.setSelected(false);
				projectsButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.setsButton: {
				ideasButton.setSelected(false);
				projectsButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.projectsButton: {
				ideasButton.setSelected(false);
				setsButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			default: {
				break;
			}
		}
	}
}
