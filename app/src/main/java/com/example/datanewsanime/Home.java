package com.example.datanewsanime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.datanewsanime.handlexml.ConnectionAPIs;

public class Home extends AppCompatActivity {
    private Button btn_logout;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_logout = findViewById(R.id.showData);
        textView = findViewById(R.id.textView);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskConnection task = new TaskConnection();
                task.execute("https://viacep.com.br/ws/01001000/json/");
            }
        });
    }

    private class TaskConnection extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            String data = ConnectionAPIs.getData(strings[0]);
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }
    }
}