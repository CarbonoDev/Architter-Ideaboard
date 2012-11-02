/**
 * 
 */
package com.example.architter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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
		LinearLayout column1 = (LinearLayout) getActivity().findViewById(R.id.linear1);
		TextView tx = new TextView(getActivity());
		tx.setText("hola!");
		column1.addView(tx);
		tx = new TextView(getActivity());
		tx.setText("hola2");
		column1.addView(tx);
		IdeaWidget idea = new IdeaWidget(getActivity(), null);
		idea.setDescription("Descripcion1");
		idea.setImage("http://ycombinator.com/images/yc500.gif");
		column1.addView(idea);
		return homeView;
	}
}
