package com.guythehunter.messanger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText username_input, password_input;
    private Button login_btn, register_btn;

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
        username_input = (EditText) this.findViewById(R.id.username_input);
        password_input = (EditText) this.findViewById(R.id.password_input);

        login_btn = (Button) this.findViewById(R.id.login_button);
        register_btn = (Button) this.findViewById(R.id.register_button);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username_input.getText().toString(), password_input.getText().toString());
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(username_input.getText().toString(), password_input.getText().toString());
            }
        });
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
}