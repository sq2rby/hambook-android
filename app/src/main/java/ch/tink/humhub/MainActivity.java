package ch.tink.humhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check if a url is set and then decide which view can be loaded


        setContentView(R.layout.activity_main);


    }
}
