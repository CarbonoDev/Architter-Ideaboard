package com.architter.widgets;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;

public class AnimatedView extends View {

	public AnimatedView(Context context) {
		super(context);
	}

	Movie movie, movie1;
	InputStream is = null, is1 = null;
	long moviestart;
	
	public void loadDrawable(int id){
		is = getContext().getResources().openRawResource(id);
		movie=Movie.decodeStream(is);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		long now = android.os.SystemClock.uptimeMillis();
		if (moviestart == 0) { // first time
			moviestart = now;

		}
		int relTime = (int) ((now - moviestart) % movie.duration());
		movie.setTime(relTime);
		movie.draw(canvas, this.getWidth() / 2 - 20, this.getHeight() / 2 - 40);
		this.invalidate();
	}
}
