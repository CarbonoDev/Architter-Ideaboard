package com.example.architter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.androidhive.imagefromurl.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IdeaWidget extends LinearLayout {
	
	private ImageView image;
	private TextView description;
	private ImageButton archthis;
	
	public IdeaWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		//((Activity)getContext()).getLayoutInflater().inflate(R.layout.idea_component, this);
		setupViewItems();
	}
	
	private void setupViewItems() {
		description = (TextView) findViewById(R.id.description);
		image = (ImageView) findViewById(R.id.image);
		archthis = (ImageButton) findViewById(R.id.archthis);
	}
	
	public void setDescription(String text) {
		description.setText(text);
	}
	
	public void setImage(String imageUrl) {
        
        // Loader image - will be shown before loading image
        int loader = R.drawable.big_logo;        
        
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getContext());
        
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(imageUrl, loader, image);
	}
	
	public void setArchthis(ImageButton imgb) {
		archthis = imgb;
	}
}
