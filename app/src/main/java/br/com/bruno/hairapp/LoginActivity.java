package br.com.bruno.hairapp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Bruno on 12/04/2015.
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getActionBar().hide();
        setContentView(R.layout.activity_login);
    }
}
