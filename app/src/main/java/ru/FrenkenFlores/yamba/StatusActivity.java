package ru.FrenkenFlores.yamba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

import winterwell.jtwitter.Twitter;

public class StatusActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "StatusActivity";
    private EditText editText;
    private Button updateButton;
    private String username = "student";
    private String password = "password";
    private String url = "http://yamba.FrenkenFlores.ru/api";
    Twitter twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);

        editText = (EditText) findViewById(R.id.editText);
        updateButton = (Button) findViewById(R.id.button);

        updateButton.setOnClickListener(this);
        twitter = new Twitter(this.username, this.password);
        twitter.setAPIRootUrl(this.url);
    }

    @Override
    public void onClick(View v) {
        twitter.setStatus(editText.getText().toString());
        Log.i(TAG, editText.getText().toString());
        Log.d(TAG, "onClick");
    }
}