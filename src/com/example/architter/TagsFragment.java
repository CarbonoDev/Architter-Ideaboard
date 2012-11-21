/**
 * 
 */
package com.example.architter;

import com.architter.R;
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
public class TagsFragment extends MyFragment implements OnClickListener, OnEditorActionListener {
	
	Button architectureButton, interiorButton, furnitureButton;
	IdeasScroll ideasScroll;
	EditText searchBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View tagsView = inflater.inflate(R.layout.tags_fragment, container, false);
		architectureButton = (Button) tagsView.findViewById(R.id.architectureButton);
		architectureButton.setSelected(true);
		interiorButton = (Button) tagsView.findViewById(R.id.interiorButton);
		furnitureButton = (Button) tagsView.findViewById(R.id.furnitureButton);
		architectureButton.setOnClickListener(this);
		interiorButton.setOnClickListener(this);
		furnitureButton.setOnClickListener(this);
		ideasScroll = (IdeasScroll) tagsView.findViewById(R.id.scrollView1);
		ideasScroll.setFragment(this);
		ideasScroll.loadTagsIdeas("architecture", true);
		searchBar = (EditText) tagsView.findViewById(R.id.tagSearchBar);
		searchBar.setOnEditorActionListener(this);
		return tagsView;
	}

	public void onClick(View v) {
		searchBar.setText("");
		switch(v.getId()) {
			case R.id.architectureButton: {
				interiorButton.setSelected(false);
				furnitureButton.setSelected(false);
				v.setSelected(true);
				ideasScroll.loadTagsIdeas("architecture", true);
				break;
			}
			case R.id.interiorButton: {
				architectureButton.setSelected(false);
				furnitureButton.setSelected(false);
				v.setSelected(true);
				ideasScroll.loadTagsIdeas("interior", true);
				break;
			}
			case R.id.furnitureButton: {
				architectureButton.setSelected(false);
				interiorButton.setSelected(false);
				v.setSelected(true);
				ideasScroll.loadTagsIdeas("furniture", true);
				break;
			}
		}
	}
	
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            architectureButton.setSelected(false);
            interiorButton.setSelected(false);
			furnitureButton.setSelected(false);
            ideasScroll.searchTagsIdeas(v.getText().toString(), true);
            return true;	
        }
		return false;
	}
}
