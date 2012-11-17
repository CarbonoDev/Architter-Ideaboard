package com.architter.widgets;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidhive.imagefromurl.ImageLoader;
import com.architter.connection.ConnectionManager;
import com.example.architter.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
	private int idea_id;
	
	public IdeaView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		//((Activity)getContext()).getLayoutInflater().inflate(R.layout.idea_component, this);
		init();
		setupViewItems();
	}
	private void init() {
		RelativeLayout loading = (RelativeLayout) this.findViewById(R.id.loadingView);
		AnimatedView loading_gif = new AnimatedView(loading.getContext());
		loading_gif.loadDrawable(R.drawable.loading_architter);
		loading.addView(loading_gif);
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
		return idea_id;
	}
	
	public void setIdeaId(int idea_id) {
		this.idea_id = idea_id;
	}
	
	public void loadIdea(JSONObject response) {
		try {
			setId(response.getInt("id"));
			String img = response.getString("img");
			if(!img.startsWith("./ideaboardImages/")) {
				img = "./ideaboardImages/" + img;
			};
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			img = ConnectionManager.ASSET_BASE + img;			
			setMainImage(img);
			setIdea_description(response.getString("descr"));
			setIdea_description_tags(response.getString("tags"));
			this.findViewById(R.id.loadingView).setVisibility(GONE);
			this.findViewById(R.id.ideaContainer).setVisibility(VISIBLE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
