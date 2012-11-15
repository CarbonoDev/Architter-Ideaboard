/**
 * 
 */
package com.example.architter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author Marco
 *
 */
public class TagsFragment extends MyFragment implements OnClickListener {
	
	Button architectureButton, interiorButton, furnitureButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View tagsView = inflater.inflate(R.layout.tags_fragment, container, false);
		architectureButton = (Button) tagsView.findViewById(R.id.architectureButton);
		architectureButton.setSelected(true);
		interiorButton = (Button) tagsView.findViewById(R.id.interiorButton);
		furnitureButton = (Button) tagsView.findViewById(R.id.furnitureButton);
		architectureButton.setOnClickListener(this);
		interiorButton.setOnClickListener(this);
		furnitureButton.setOnClickListener(this);
		return tagsView;
	}

	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.architectureButton: {
				interiorButton.setSelected(false);
				furnitureButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.interiorButton: {
				architectureButton.setSelected(false);
				furnitureButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			case R.id.furnitureButton: {
				architectureButton.setSelected(false);
				interiorButton.setSelected(false);
				v.setSelected(true);
				break;
			}
			default: {
				break;
			}
		}
	}
}
