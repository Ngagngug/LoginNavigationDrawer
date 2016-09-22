package com.testnavigationdrawer.husinnapi.testnavigationdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.textinputlayout1)
    TextInputLayout textInputLayout1;
    @BindView(R.id.textinputlayout2)
    TextInputLayout textInputLayout2;
    @BindView(R.id.input_email)
    EditText input_email;
    @BindView(R.id.input_password)
    EditText input_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.link_signup)
    TextView link_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        btn_login.setOnClickListener(e -> login());
        link_signup.setOnClickListener(e -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivityForResult(intent, REQUEST_SIGNUP);
            });
    }

    private void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btn_login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void onLoginSuccess() {
        btn_login.setEnabled(true);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void onLoginFailed() {
        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
        btn_login.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("enter a valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            input_password.setError("beetween 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            input_password.setError(null);
        }
        return valid;
    }
}
