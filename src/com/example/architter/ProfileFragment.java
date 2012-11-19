/**
 * 
 */
package com.example.architter;

import com.architter.widgets.IdeasScroll;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * @author Marco
 *
 */
public class ProfileFragment extends MyFragment implements OnClickListener, OnEditorActionListener {

	Button ideasButton, setsButton, projectsButton;
	IdeasScroll ideasScroll;
	EditText searchBar;
	
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
		ideasScroll.setFragment(this);
		ideasScroll.loadUserIdeas("",true);
		searchBar = (EditText) profileView.findViewById(R.id.profileSearchBar);
		searchBar.setOnEditorActionListener(this);
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
	
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            ideasScroll.searchUserIdeas(v.getText().toString(), true);
            return true;	
        }
		return false;
	}
}
