package com.architter.widgets;

import com.androidhive.imagefromurl.ImageLoader;
import com.architter.connection.ConnectionManager;
import com.example.architter.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IdeaWidget extends LinearLayout {
	
	private ImageView image;
	private TextView via, username;
	private ImageButton archthis;
	private TextView idea_id;
	
	public IdeaWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public IdeaWidget(Context context) {
		super(context);
	}

	@Override
	protected void onFinishInflate() {
		setupViewItems();
		super.onFinishInflate();
	}
	
	public void setupViewItems() {
		via = (TextView) findViewById(R.id.via);
		username = (TextView) findViewById(R.id.username);
		idea_id = (TextView) findViewById(R.id.idea_id);
		image = (ImageView) findViewById(R.id.image);
		archthis = (ImageButton) findViewById(R.id.archthis);
	}
	
	public void setIdeaId(int idea_id) {
		this.idea_id.setText(""+idea_id);
	}
	
	public int getIdeaId() {
		return Integer.parseInt((String)idea_id.getText());
	}
	
	
	public void setUsername(String text) {
		username.setText(text);
	}
	
	public String getUsername() {
		return (String) username.getText();
	}
	
	public void userHas(boolean user_has) {
		if(user_has) {
			findViewById(R.id.archthis).setVisibility(GONE);
		} else {
			findViewById(R.id.archthis).setVisibility(VISIBLE);
		}
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
	
public void setUserPicture(String imageUrl) {
        imageUrl = ConnectionManager.getUserAvatar(imageUrl);
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
	
	public void setListener(OnClickListener listener) {
		image.setOnClickListener(listener);
		archthis.setOnClickListener(listener);
	}

}
