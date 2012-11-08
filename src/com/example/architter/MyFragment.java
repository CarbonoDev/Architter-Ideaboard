package com.example.architter;

import android.app.Fragment;
import android.app.FragmentTransaction;

public class MyFragment extends Fragment {

	public void loadFragment(Fragment newFragment, Fragment previous) {
		// Create new fragment and transaction
		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack
		transaction.hide(previous);
		transaction.replace(android.R.id.content, newFragment);
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
	}
}
