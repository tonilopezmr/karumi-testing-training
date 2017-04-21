package com.tonilopezmr.karumitestingtraining;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final SessionApiClient sessionApiClient = new SessionApiClient();

    final EditText email = ((EditText) findViewById(R.id.emailEditText));
    final EditText password = ((EditText) findViewById(R.id.passwordEditText));

    final Button login = ((Button) findViewById(R.id.login));
    Button logOut = ((Button) findViewById(R.id.logOut));

    login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sessionApiClient.login(email.getText().toString(), password.getText().toString(), new SessionApiClient.Callback() {
          @Override
          public void onSuccess() {
            email.setVisibility(View.INVISIBLE);
            password.setVisibility(View.INVISIBLE);
            login.setEnabled(false);
            email.setText("");
            password.setText("");
          }

          @Override
          public void onError() {
            Toast.makeText(MainActivity.this, "LOGIN ERROR", Toast.LENGTH_SHORT).show();
          }
        });
      }
    });

    logOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sessionApiClient.logout(new SessionApiClient.Callback() {
          @Override
          public void onSuccess() {
            email.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            login.setEnabled(true);
          }

          @Override
          public void onError() {
            Toast.makeText(MainActivity.this, "LOGOUT ERROR", Toast.LENGTH_SHORT).show();
          }
        });
      }
    });
  }
}
