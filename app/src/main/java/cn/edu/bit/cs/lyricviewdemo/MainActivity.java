package cn.edu.bit.cs.lyricviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LyricView view;
    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (LyricView)findViewById(R.id.view);
        editText = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button);
        InputStream is = getResources().openRawResource(R.raw.lyric);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Long> list1 = new ArrayList<>();
        String line;
        try {
            while((line = br.readLine()) != null) {
                list.add(line);
                list1.add(0l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.setLyricText(list, list1);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.scrollToIndex(3);
            }
        }, 1000);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                int index = 0;
                try {
                    index = Integer.parseInt(text);
                } catch (Exception e) {

                }
                view.scrollToIndex(index);
            }
        });

        view.setOnLyricScrollChangeListener(new LyricView.OnLyricScrollChangeListener() {
            @Override
            public void onLyricScrollChange(int index, int oldindex) {
                editText.setText("" + index);
            }
        });
    }
}
