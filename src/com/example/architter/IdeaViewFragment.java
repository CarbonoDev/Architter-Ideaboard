/**
 * 
 */
package com.example.architter;

import org.json.JSONObject;

import com.architter.connection.ConnectionManager;
import com.architter.widgets.IdeaView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
	IdeaView idea;
	
	public IdeaViewFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		idea = (IdeaView) inflater.inflate(R.layout.idea_view, container, false);
		idea.setListener(this);
		return idea;
	}

	public int getIdeaId() {
		return idea_id;
	}

	public void setIdeaId(int idea_id) {
		this.idea_id = idea_id;
		loadIdea();
	}

	private void loadIdea() {
		RequestParams params = new RequestParams();
 		ConnectionManager.getIdea(params, new  JsonHttpResponseHandler() {
			@Override
			public void onFailure(Throwable arg0) {
				System.out.println(":(");
			}
			@Override
			public void onSuccess(JSONObject response) {
					System.out.println("idea loaded");
					idea.loadIdea(response);
			}
		}, getIdeaId());
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
