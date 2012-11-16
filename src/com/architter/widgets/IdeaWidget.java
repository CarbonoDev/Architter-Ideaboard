package com.architter.widgets;

import com.androidhive.imagefromurl.ImageLoader;
import com.example.architter.ArchThisFragment;
import com.example.architter.IdeaViewFragment;
import com.example.architter.MyFragment;
import com.example.architter.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IdeaWidget extends LinearLayout implements OnClickListener {
	
	private ImageView image;
	private TextView description;
	private ImageButton archthis;
	private MyFragment fragment;
	
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
		description = (TextView) findViewById(R.id.description);
		image = (ImageView) findViewById(R.id.image);
		archthis = (ImageButton) findViewById(R.id.archthis);
		image.setOnClickListener(this);
		archthis.setOnClickListener(this);
	}
	
	public int getIdeaId() {
		return 0;
	}
	
	
	public void setDescription(String text) {
		description.setText(text);
	}
	
	public String getDescription() {
		return (String) description.getText();
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
	
	public void setListener(OnClickListener listener) {
		image.setOnClickListener(listener);
		archthis.setOnClickListener(listener);
	}

	public void setFragment(MyFragment fragment) {
		this.fragment = fragment;
		
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.image: {
			int id = this.getIdeaId();
			IdeaViewFragment newFragment = new IdeaViewFragment();
			newFragment.setIdea_id(id);
			//fragment.loadFragment(newFragment, fragment);
			break;
		}
		case R.id.archthis: {
			IdeaWidget i = (IdeaWidget) v.getParent();
			int id = this.getIdeaId();
			ArchThisFragment newFragment = new ArchThisFragment();
			newFragment.setIdea_id(id);
			//fragment.loadFragment(newFragment, fragment);
			break;
		}

		default:
			break;
		}
		
	}
}
