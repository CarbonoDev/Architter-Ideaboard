package com.architter.widgets;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidhive.imagefromurl.ImageLoader;
import com.architter.connection.ConnectionManager;
import com.architter.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ArchThisView extends LinearLayout {

	private ImageView mainImage;
	private View shareLayout, cancel_btn, save_btn;
	private int idea_id;

	public ArchThisView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		setupViewItems();
	}

	private void setupViewItems() {
		mainImage = (ImageView) findViewById(R.id.mainImage);
		shareLayout = findViewById(R.id.shareLayout);
		cancel_btn = findViewById(R.id.cancel);
		save_btn = findViewById(R.id.saveButton);
	}

	public void setMainImage(String imageUrl) {

        // Loader image - will be shown before loading image
        int loader = R.drawable.big_logo;

        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getContext());

        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView
        imgLoader.DisplayImage(imageUrl, loader, mainImage);
	}

	public void setListener(OnClickListener listener) {
		shareLayout.setOnClickListener(listener);
		cancel_btn.setOnClickListener(listener);
		save_btn.setOnClickListener(listener);
		
	}

	public void setIdeaId(int idea_id) {
		this.idea_id = idea_id;
	}

	public int getIdeaId() {
		return idea_id;
	}
	
	public String getDescription() {
		EditText text = (EditText) findViewById(R.id.ideaDescription);
		return text.getText().toString();
	}

	public void loadIdea(JSONObject response) {
		try {
			setIdeaId(response.getInt("id"));
			String img = response.getString("img");
			img = ConnectionManager.getImgUrl(img);
			setMainImage(img);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
