package com.example.architter;

import android.app.Fragment;
import android.app.FragmentTransaction;
import com.architter.R;

public class MyFragment extends Fragment {

	public void loadFragment(Fragment newFragment, Fragment previous) {
		loadFragment(newFragment, previous, android.R.id.content);
	}

	public void loadFragment(Fragment newFragment, Fragment previous, int view) {
		// Create new fragment and transaction
		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack
		transaction.hide(previous);
		transaction.replace(view, newFragment);
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
	}

	public void loadFragment(Fragment newFragment) {
		loadFragment(newFragment, this);		
	}
	
	public void loadFragment(Fragment newFragment, int view) {
		loadFragment(newFragment, this, view);		
	}
	
}
