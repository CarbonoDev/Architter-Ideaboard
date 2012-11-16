package com.example.architter;

import com.architter.widgets.ArchThisView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class ArchThisFragment extends MyFragment implements OnClickListener  {
	private int idea_id;
	private String main_image;
	
	public ArchThisFragment() {
		this.setIdea_id(0);
		loadData();
	}

	private void loadData() {
		main_image = "http://www.architter.com/images/KUU_black_BASIC_3.jpg";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ArchThisView view = (ArchThisView) inflater.inflate(R.layout.arch_this, container, false);
		view.setMainImage(main_image);
		view.setListener(this);
		return view;
	}

	public int getIdea_id() {
		return idea_id;
	}

	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shareLayout:
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			break;
		default:
			break;
		}		
	}
}
