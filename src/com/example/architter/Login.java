package com.example.architter;

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
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
	private EditText email;
	private EditText pwd;
	private boolean logging_in = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ConnectionManager.setActivity(this);
        findViewById(R.id.login_button).setOnClickListener(this);
        email = (EditText) findViewById(R.id.login_email);
        pwd = (EditText) findViewById(R.id.login_pwd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			String user = email.getText().toString();
			String password = pwd.getText().toString();
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
									Intent intent = new Intent(getApplicationContext(), MainActivity.class);
									startActivity(intent);
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
    
    
}
