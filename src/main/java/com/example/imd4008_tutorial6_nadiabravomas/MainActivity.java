package com.example.imd4008_tutorial6_nadiabravomas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    static final String SAVED_STATE = "savedState";
    DrawingCanvas dCanvas;

    Button btnLine, btnRectangle, btnCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dCanvas = findViewById(R.id.myCanvas);
        dCanvas.pathColour = Color.YELLOW;

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Testing if the button registers the click, but it doesn't
                Log.d("ButtonClick", "Button clicked!");
                dCanvas.clearCanvas();
            }
        });
    }



    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_STATE, dCanvas.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            dCanvas.onRestoreInstanceState(savedInstanceState.getParcelable(SAVED_STATE));
        }
    }


}