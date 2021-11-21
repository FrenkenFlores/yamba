package ru.FrenkenFlores.yamba;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;
import winterwell.jtwitter.Status;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;

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

    class postToTwitter extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... statuses) {
            try {
                winterwell.jtwitter.Status s = twitter.updateStatus(statuses[0]);
                return s.text;
            } catch (TwitterException e) {
                Log.e(TAG, e.toString());
                e.printStackTrace();
                return "Failed to post";
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(StatusActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        String status = editText.getText().toString();
        new postToTwitter().execute(status);
        Log.i(TAG, editText.getText().toString());
        Log.d(TAG, "onClick");
    }
}