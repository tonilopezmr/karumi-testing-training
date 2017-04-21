package com.tonilopezmr.karumitestingtraining;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SessionPresenter.View {

  EditText email;
  EditText password;

  Button login;
  Button logOut;

  ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final SessionApiClient sessionApiClient = new SessionApiClient(new ThreadExecutor(), new TimeMachine());
    final SessionPresenter presenter = new SessionPresenter(sessionApiClient);
    presenter.setView(this);

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
        presenter.login(email.getText().toString(), password.getText().toString());
      }
    });

    logOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.logout();
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

  @Override
  public void showLoader() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoader() {
    progressBar.setVisibility(View.GONE);
  }

  @Override
  public void enableLoginButton(boolean isEnable) {
    login.setEnabled(isEnable);
  }

  @Override
  public void enableLogoutButton(boolean isEnable) {
    logOut.setEnabled(isEnable);
  }

  @Override
  public void showForm() {
    email.setVisibility(View.VISIBLE);
    password.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideForm() {
    email.setVisibility(View.INVISIBLE);
    password.setVisibility(View.INVISIBLE);
    email.setText("");
    password.setText("");
  }

  @Override
  public void showLoginError() {
    Toast.makeText(MainActivity.this, "LOGIN ERROR", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showLogoutError() {
    Toast.makeText(MainActivity.this, "LOGOUT ERROR", Toast.LENGTH_SHORT).show();
  }
}
