package com.architter.widgets;

import com.androidhive.imagefromurl.ImageLoader;
import com.example.architter.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ArchThisView extends LinearLayout {
	
	private ImageView mainImage;
	private RelativeLayout shareLayout;
	
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
		shareLayout = (RelativeLayout) findViewById(R.id.shareLayout);
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
	
	public void setListener(OnClickListener listener) {
		shareLayout.setOnClickListener(listener);
	}

}
