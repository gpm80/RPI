package com.dp.rosseti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener  {

    private Button mEnterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mEnterButton = findViewById(R.id.bt_enter_user);
        mEnterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_enter_user:
                //String code = mCodeEditTect.getText().toString();
                //if (code.equals("658166")) {
                startUserInfoActivity();
                /*} else {
                    Toast.makeText(this,
                            "Please enter correct code",
                            Toast.LENGTH_SHORT).show();
                }*/
                break;
        }
    }

    private void startUserInfoActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
