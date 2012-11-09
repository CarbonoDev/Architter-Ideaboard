package com.example.architter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AutoImageView extends ImageView {
	
	public AutoImageView(Context context)
	{
	    super(context);
	}

	public AutoImageView(Context context, AttributeSet attrs)
	{
	    super(context, attrs);
	}

	public AutoImageView(Context context, AttributeSet attrs,
	        int defStyle)
	{
	    super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
	    Drawable drawable = getDrawable();
	    if (drawable != null)
	    {
	        int width =  MeasureSpec.getSize(widthMeasureSpec);
	        int diw = drawable.getIntrinsicWidth();
	        if (diw > 0)
	        {
	            int height = width * drawable.getIntrinsicHeight() / diw;
	            setMeasuredDimension(width, height);
	        }
	        else
	            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    }
	    else
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
