package com.example.architter;

import com.androidhive.imagefromurl.ImageLoader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IdeaView extends RelativeLayout {
	
	private ImageView mainImage;
	private ImageButton shareButton;
	private ImageButton deleteButton;
	private ImageButton archButton;
	private ImageButton urlButton;
	private TextView idea_description;
	private TextView idea_description_tags;
	
	public IdeaView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		//((Activity)getContext()).getLayoutInflater().inflate(R.layout.idea_component, this);
		setupViewItems();
	}
	
	private void setupViewItems() {
		mainImage = (ImageView) findViewById(R.id.mainImage);
		shareButton = (ImageButton) findViewById(R.id.shareButton);
		deleteButton = (ImageButton) findViewById(R.id.deleteButton);
		archButton = (ImageButton) findViewById(R.id.archButton);
		urlButton = (ImageButton) findViewById(R.id.urlButton);
		idea_description = (TextView) findViewById(R.id.idea_description);
		idea_description_tags = (TextView) findViewById(R.id.idea_description_tags);
	}
	
	public int getIdeaId() {
		return 0;
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

	public void setIdea_description(String idea_description) {
		this.idea_description.setText(idea_description);
	}

	public void setIdea_description_tags(String idea_description_tags) {
		this.idea_description_tags.setText(idea_description_tags);
	}

	public void setListener(OnClickListener listener) {
		shareButton.setOnClickListener(listener);
		deleteButton.setOnClickListener(listener);
		archButton.setOnClickListener(listener);
		urlButton.setOnClickListener(listener);
	}
	
		

}
