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
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * @author Marco
 *
 */
public class SearchFragment extends MyFragment implements OnItemClickListener, OnClickListener, OnEditorActionListener {
	
	ListView tagsListView;
	RelativeLayout containerView;
	String [] tagsArray;
	Button imagesButton, setsButton;
	IdeasScroll ideasScroll;
	EditText searchBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View searchView = inflater.inflate(R.layout.search_fragment, container, false);
		imagesButton = (Button) searchView.findViewById(R.id.imagesButton);
		setsButton = (Button) searchView.findViewById(R.id.setsButton);
		imagesButton.setOnClickListener(this);
		setsButton.setOnClickListener(this);
		containerView = (RelativeLayout) searchView.findViewById(R.id.containerSearchView);
		tagsListView = (ListView) searchView.findViewById(R.id.tagsListView);
		tagsListView.setOnItemClickListener(this);
		tagsArray = getResources().getStringArray(R.array.Tags);
		ideasScroll = (IdeasScroll) searchView.findViewById(R.id.scrollView1);
		ideasScroll.setFragment(this);
		searchBar = (EditText) searchView.findViewById(R.id.searchBar);
		searchBar.setOnEditorActionListener(this);
		return searchView;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		displayIdeas(tagsArray[arg2]);
	}
	
	public void displayIdeas(String tag) {
		tagsListView.setVisibility(View.GONE);
		ideasScroll.setVisibility(View.VISIBLE);
		ideasScroll.loadIdeas(tag, true);
	}
	
	public void displaySearchIdeas(String search) {
		tagsListView.setVisibility(View.GONE);
		ideasScroll.setVisibility(View.VISIBLE);
		ideasScroll.searchIdeas(search, true);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.imagesButton: {
				setsButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.setsButton: {
				imagesButton.setSelected(false);
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
            displaySearchIdeas(v.getText().toString());
            return true;	
        }
		return false;
	}
	
	public void onBackPressed() {
		tagsListView.setVisibility(View.VISIBLE);
		ideasScroll.setVisibility(View.GONE);
		searchBar.setText("");
	}
	
}
