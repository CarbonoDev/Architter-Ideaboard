package com.example.architter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
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
	
	private TextView description;
	private ImageView image;
	private ImageButton archthis;
	
	public IdeaWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		((Activity)getContext()).getLayoutInflater().inflate(R.layout.idea_component, this);
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
	
	public void setImage(String url) {
		Bitmap bitImage = getBitmapFromURL(url);
		image.setImageBitmap(bitImage);
	}
	
	public void setArchthis(ImageButton imgb) {
		archthis = imgb;
	}
	
	public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
