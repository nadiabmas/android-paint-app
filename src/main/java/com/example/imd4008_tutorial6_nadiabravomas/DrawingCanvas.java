package com.example.imd4008_tutorial6_nadiabravomas;

import static com.example.imd4008_tutorial6_nadiabravomas.MainActivity.SAVED_STATE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class DrawingCanvas extends View {

    private Paint mPaint;
    private Path mPath;
    private LinkedList<Paint> paintContainer;
    private LinkedList<Path> pathsContainer;


    int pathColour = Color.BLUE;


    private Bitmap mBitmap;
    private Canvas bitmapCanvas;

    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintContainer = new LinkedList<Paint>();
        pathsContainer = new LinkedList<Path>();

        mPaint = new Paint();
        mPath = new Path();

        //enum DrawTool { LINE, RECTANGLE, CIRCLE }
        //DrawTool currentDrawTool;

    }

        @Override
        protected Parcelable onSaveInstanceState() {
            // Save your custom state here
            super.onSaveInstanceState();
            Bundle bundle = new Bundle();

            return bundle;
        }

        @Override
        protected void onRestoreInstanceState(Parcelable state) {
            Bundle bundle = (Bundle) state;

            super.onRestoreInstanceState(bundle.getParcelable(SAVED_STATE));
        }


    @Override
    protected void onDraw(Canvas canvas){
        if (pathsContainer.size() > 0){
            for(int i = pathsContainer.size() - 1; i >= 0; --i){
                canvas.drawPath(pathsContainer.get(i), paintContainer.get(i));
                super.onDraw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){

        //Multi-touch
        int touchCount = event.getPointerCount();

        if (touchCount == 1) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    mPaint.setColor(pathColour);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeJoin(Paint.Join.ROUND);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);
                    mPaint.setStrokeWidth(10);

                    pathsContainer.addFirst(mPath);
                    paintContainer.addFirst(mPaint);

                    pathsContainer.getFirst().moveTo(event.getX(), event.getY());
                    break;

                case MotionEvent.ACTION_MOVE:
                    pathsContainer.getFirst().lineTo(event.getX(), event.getY());
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    mPaint = new Paint();
                    mPath = new Path();

                    if (pathColour == Color.BLUE) {
                        pathColour = Color.RED;
                    } else {
                        pathColour = Color.BLUE;
                    }

                    break;
            }
        } else if (touchCount == 2) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mPaint.setColor(pathColour);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeJoin(Paint.Join.ROUND);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);
                    mPaint.setStrokeWidth(10);

                    pathsContainer.addFirst(mPath);
                    paintContainer.addFirst(mPaint);

                    pathsContainer.getFirst().moveTo(event.getX(0), event.getY(0));
                    pathsContainer.getFirst().lineTo(event.getX(1), event.getY(1));
                    invalidate();

                    break;
                case MotionEvent.ACTION_MOVE:

                    pathsContainer.getFirst().moveTo(event.getX(0), event.getY(0));
                    pathsContainer.getFirst().lineTo(event.getX(1), event.getY(1));
                    invalidate();

                    break;
            }
        } else if (touchCount == 3) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mPaint.setColor(pathColour);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeJoin(Paint.Join.ROUND);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);
                    mPaint.setStrokeWidth(10);

                    pathsContainer.addFirst(mPath);
                    paintContainer.addFirst(mPaint);

                    pathsContainer.getFirst().moveTo(event.getX(0), event.getY(0));
                    pathsContainer.getFirst().lineTo(event.getX(1), event.getY(1));
                    invalidate();

                    break;
                case MotionEvent.ACTION_MOVE:

                    pathsContainer.getFirst().moveTo(event.getX(0), event.getY(0));
                    pathsContainer.getFirst().lineTo(event.getX(1), event.getY(1));
                    invalidate();

                    break;
            }
        }
        return true;
    }

    public void clearCanvas() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        invalidate();
    }
}
