package com.example.architter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.architter.connection.ConnectionManager;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
	private EditText email;
	private EditText pwd;
	private boolean logging_in = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ConnectionManager.setActivity(this);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login_button).setOnClickListener(this);
        email = (EditText) findViewById(R.id.login_email);
        pwd = (EditText) findViewById(R.id.login_pwd);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.menu_exit:
    			finish();
    			break;
    	}
		return true;

    };

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			String user = "user@example.com";//email.getText().toString();
			String password = "secret"; //pwd.getText().toString();
			if(user.isEmpty() || password.isEmpty()) {
				Toast.makeText(getApplicationContext(), "Email and password cannot be empty.",Toast.LENGTH_LONG).show();
			} else {
				if(!logging_in) {
					logging_in = true;
					Toast.makeText(getApplicationContext(), "Connecting...",Toast.LENGTH_LONG).show();
					ConnectionManager.logIn(user, password, new JsonHttpResponseHandler() {
						@Override
						public void onFailure(Throwable arg0) {
							logging_in = false;
							Toast.makeText(getApplicationContext(), "Network error, please try again later.",Toast.LENGTH_LONG).show();
						}
						@Override
						public void onSuccess(JSONObject response) {
							try {
								logging_in = false;
								Toast.makeText(getApplicationContext(), response.getString("mod"), Toast.LENGTH_LONG).show();
								if(response.getBoolean("success")) {
									ConnectionManager.avatar =  response.getString("avatar");
									ConnectionManager.username = response.getString("username");
									ConnectionManager.realname = response.getString("realname");
									Intent intent = new Intent(getApplicationContext(), MainActivity.class);
									startActivityForResult(intent, 1);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if(resultCode==2){
	        finish();
	    }
	}


}
