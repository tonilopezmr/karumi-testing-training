package com.tonilopezmr.karumitestingtraining;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


  EditText email;
  EditText password;

  Button login;
  Button logOut;

  ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final SessionApiClient sessionApiClient = new SessionApiClient(new ThreadExecutor());

    email = ((EditText) findViewById(R.id.emailEditText));
    password = ((EditText) findViewById(R.id.passwordEditText));

    login = ((Button) findViewById(R.id.login));
    logOut = ((Button) findViewById(R.id.logOut));

    progressBar = ((ProgressBar) findViewById(R.id.progressBar));

    if (isUserLogin()) {
      userLogin();
    } else {
      notLogin();
    }

    login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        login.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        sessionApiClient.login(email.getText().toString().trim(), password.getText().toString().trim(), new SessionApiClient.Callback() {
          @Override
          public void onSuccess() {
            userLogin();
            storeLogin(true);
            progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onError() {
            Toast.makeText(MainActivity.this, "LOGIN ERROR", Toast.LENGTH_SHORT).show();
            login.setEnabled(true);
            progressBar.setVisibility(View.GONE);
          }
        });
      }
    });

    logOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        logOut.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        sessionApiClient.logout(new SessionApiClient.Callback() {
          @Override
          public void onSuccess() {
            notLogin();
            storeLogin(false);
            progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onError() {
            Toast.makeText(MainActivity.this, "LOGOUT ERROR", Toast.LENGTH_SHORT).show();
            logOut.setEnabled(true);
            progressBar.setVisibility(View.GONE);
          }
        });
      }
    });
  }

  private void notLogin() {
    email.setVisibility(View.VISIBLE);
    password.setVisibility(View.VISIBLE);
    login.setEnabled(true);
    logOut.setEnabled(false);
  }

  private void userLogin() {
    email.setVisibility(View.INVISIBLE);
    password.setVisibility(View.INVISIBLE);
    login.setEnabled(false);
    logOut.setEnabled(true);
    email.setText("");
    password.setText("");
  }

  private void storeLogin(boolean isLogin) {
    SharedPreferences sharedPreferences = getSharedPreferences("karumi-testing-training", 1);
    SharedPreferences.Editor edit = sharedPreferences.edit();
    edit.putBoolean("login", isLogin);
    edit.apply();
  }

  private boolean isUserLogin() {
    SharedPreferences sharedPreferences = getSharedPreferences("karumi-testing-training", 1);
    return sharedPreferences.getBoolean("login", false);
  }
}
