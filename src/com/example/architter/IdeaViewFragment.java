/**
 * 
 */
package com.example.architter;

import org.json.JSONObject;

import com.architter.connection.ConnectionManager;
import com.architter.widgets.AnimatedView;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author Marco
 *
 */
public class IdeaViewFragment extends MyFragment  implements OnClickListener  {
	private int idea_id;
	IdeaView idea;
	RelativeLayout loading;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		idea = (IdeaView) inflater.inflate(R.layout.idea_view, container, false);
		idea.setListener(this);
		loading = (RelativeLayout) idea.findViewById(R.id.loadingView);
		AnimatedView loading_gif = new AnimatedView(loading.getContext());
		loading_gif.loadDrawable(R.drawable.loading_architter);
		loading.addView(loading_gif);
		
		loadIdea();
		return idea;
	}

	public int getIdeaId() {
		return idea_id;
	}

	public void setIdeaId(int idea_id) {
		this.idea_id = idea_id;
	}

	private void loadIdea() {
		RequestParams params = new RequestParams();
 		ConnectionManager.getIdea(params, new  JsonHttpResponseHandler() {
			@Override
			public void onFailure(Throwable arg0) {
				Toast.makeText(getActivity(), "Network error, please try again later.",Toast.LENGTH_LONG).show();
			}
			@Override
			public void onSuccess(JSONObject response) {
					idea.loadIdea(response);
			}
		}, getIdeaId());
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.archButton:
			ArchThisFragment newFragment = new ArchThisFragment();
			newFragment.setIdeaId(idea_id);
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
			ConnectionManager.deleteIdea(new JsonHttpResponseHandler() {
				@Override
				public void onFailure(Throwable arg0) {
					System.out.println(":(");
				}
				@Override
				public void onSuccess(JSONObject response) {
					getFragmentManager().popBackStack();
				}
			}, getIdeaId());
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
