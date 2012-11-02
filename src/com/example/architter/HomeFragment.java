/**
 * 
 */
package com.example.architter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Marco
 *
 */
public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	    Context darkTheme = new ContextThemeWrapper(getActivity(), R.style.AppTheme);
	    inflater = (LayoutInflater) darkTheme.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View homeView = inflater.inflate(R.layout.home_fragment, container, false);
		getActivity().setContentView(R.layout.home_fragment);
		LinearLayout column1 = (LinearLayout) getActivity().findViewById(R.id.linear2);
		LinearLayout column2 = (LinearLayout) getActivity().findViewById(R.id.linear3);
		TextView tx = new TextView(getActivity());
		tx.setText("hola1");
		column1.addView(tx);
		tx = new TextView(getActivity());
		tx.setText("hola2");
		column2.addView(tx);
		IdeaWidget idea = (IdeaWidget) inflater.inflate(R.layout.idea_component, column1);
//		idea.setDescription("descri");
//		idea.setImage("http://ycombinator.com/images/yc500.gif");
//		column1.addView(idea);
		return homeView;
	}
}
