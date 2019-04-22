package ch.tink.humhub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {

    private EditText url;
    protected SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        this.sharedPreferences = this.getSharedPreferences("share",MODE_PRIVATE);

        this.fadeInImage();
        this.editThisText();
    }

    private void editThisText(){
        this.url = (EditText) findViewById(R.id.editText2);
        this.url.setText("http://hambook.ampr.org");
        this.url.selectAll();

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void checkInput(View view){
        String textToCheck = this.url.getText().toString();

        if(textToCheck.startsWith("https://") || textToCheck.startsWith("http://")){
            this.saveUrl(textToCheck);
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    private void saveUrl(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url",url);
        editor.commit();
    }

    private void fadeInImage(){
        ImageView myImageView= (ImageView)findViewById(R.id.imageView);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        myImageView.startAnimation(myFadeInAnimation);
    }

}
