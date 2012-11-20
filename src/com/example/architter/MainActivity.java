package com.example.architter;

import com.architter.connection.ConnectionManager;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        
        Tab tab = actionBar.newTab()
                .setText("")
                .setIcon(R.drawable.logo_small)
                .setTabListener(new TabListener<HomeFragment>(
                        this, "home", HomeFragment.class));
        actionBar.addTab(tab);
        
        tab = actionBar.newTab()
                .setText("")
                .setIcon(R.drawable.tags_button)
                .setTabListener(new TabListener<TagsFragment>(
                        this, "tags", TagsFragment.class));
        actionBar.addTab(tab);
        
        tab = actionBar.newTab()
                .setText("")
                .setIcon(R.drawable.profile_button)
                .setTabListener(new TabListener<ProfileFragment>(
                        this, "profile", ProfileFragment.class));
        actionBar.addTab(tab);
        
        tab = actionBar.newTab()
                .setText("")
                .setIcon(R.drawable.search_button)
                .setTabListener(new TabListener<SearchFragment>(
                        this, "search", SearchFragment.class));
        actionBar.addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.menu_exit:
    			finish();
    			break;
    		case R.id.menu_logout:
    			ConnectionManager.logOut(new AsyncHttpResponseHandler() {
    				@Override
    				public void onSuccess(String arg0) {
    					// TODO Auto-generated method stub
    					Toast.makeText(getApplicationContext(), "You have been logged out.",Toast.LENGTH_LONG).show();
    				}
    			});
    			logOut();
    			break;
    	}
		return true;
    	
    };
    
    public void finish() {
    	setResult(2);
    	super.finish();
    }
    
    public void logOut() {
    	setResult(1);
    	super.finish();
    }
    
    @Override
    public void onBackPressed()
    {
        SearchFragment search = (SearchFragment)getFragmentManager().findFragmentByTag("search");
        if(search != null && search.isVisible()) {
        	search.onBackPressed();
        } else {
        	super.onBackPressed();
        }
    }
    
    
    
    /**
     * TabLister permite cambiar entre fragmetos utilizando el ActionBar
     * @author Marco
     *
     * @param <T>
     */
    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        /** Constructor used each time a new tab is created.
          * @param activity  The host Activity, used to instantiate the fragment
          * @param tag  The identifier tag for the fragment
          * @param clz  The fragment's Class, used to instantiate the fragment
          */
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        /* The following are each of the ActionBar.TabListener callbacks */

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // Check if the fragment is already initialized
//            if (mFragment == null) {
                // If not, instantiate and add it to the activity
            	mActivity.setTheme(R.style.AppTheme);
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.replace(android.R.id.content, mFragment, mTag);
//            } else {
//                // If it exists, simply attach it in order to show it
//                ft.replace(mFragment);
//            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }
    }
}
