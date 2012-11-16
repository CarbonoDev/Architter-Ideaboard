/**
 * 
 */
package com.example.architter;

import com.architter.widgets.IdeaView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

/**
 * @author Marco
 *
 */
public class IdeaViewFragment extends MyFragment  implements OnClickListener  {
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
		idea.setListener(this);
		idea.setMainImage(main_image);
		return idea;
	}

	public int getIdea_id() {
		return idea_id;
	}

	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.archButton:
			ArchThisFragment newFragment = new ArchThisFragment();
			newFragment.setIdea_id(idea_id);
			loadFragment(newFragment, this);
			break;
		case R.id.shareButton:
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			break;
		case R.id.deleteButton:
			break;
		case R.id.urlButton:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			startActivity(browserIntent);
			break;
		default:
			break;
		}
	}
}
