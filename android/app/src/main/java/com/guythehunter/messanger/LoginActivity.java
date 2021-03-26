package com.guythehunter.messanger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username_input, password_input;
    private Button login_btn, register_btn;
    private TextView error_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setComponents();
    }

    /**
     * Sets the components of the activity
     */
    private void setComponents() {
        error_text = (TextView) this.findViewById(R.id.error_text);

        username_input = (EditText) this.findViewById(R.id.username_input);
        password_input = (EditText) this.findViewById(R.id.password_input);

        login_btn = (Button) this.findViewById(R.id.login_button);
        register_btn = (Button) this.findViewById(R.id.register_button);

        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    /**
     * Handles login logic
     *
     * @param username User's name
     * @param password User's password
     */
    private void login(String username, String password) {

    }

    /**
     * Handles register logic
     *
     * @param username User's name
     * @param password User's password
     */
    private void register(String username, String password) {

    }

    /**
     * Clears the error message
     */
    private void clearError() {
        this.error_text.setText(R.string.empty_string);
    }

    /**
     * Handles on click event
     *
     * @param v View the was clicked
     */
    @Override
    public void onClick(View v) {
        clearError();
        String username = this.username_input.getText().toString(),
                password = this.password_input.getText().toString();

        switch (v.getId()) {
            case R.id.login_button:
                this.login(username, password);
                break;

            case R.id.register_button:
                this.register(username, password);
                break;
        }
    }
}