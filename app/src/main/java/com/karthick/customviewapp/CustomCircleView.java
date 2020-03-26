package com.karthick.customviewapp;

import androidx.annotation.Nullable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/*
 * EDIT THE % VALUE IN STRINGS.XML, COLOR VALUE IN COLORS.XML, FONT SIZE IN DIMENS.XML
 * */

public class CustomCircleView extends View {
    private Paint circlePaint = new Paint();
    private int circleCol, textCol, fontSize;
    private String circleTxt;

    Paint textPaint = new Paint();

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupAttributes(attrs);
    }

    void setupAttributes(AttributeSet attrs) {

        //OBTAIN THE ATTRIBUTES MENTIONED IN attrs.xml
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCircleView, 0, 0);

        //EXTRACT CUSTOM ATTRIBUTES INTO MEMBER VARIABLES
        try {
            circleCol = typedArray.getInteger(R.styleable.CustomCircleView_circleColor, getResources().getColor(R.color.circleColor));
            circleTxt = typedArray.getString(R.styleable.CustomCircleView_circleText);
            textCol = typedArray.getInteger(R.styleable.CustomCircleView_textColor, 0);
            fontSize = (int) typedArray.getDimension(R.styleable.CustomCircleView_fontSize, getResources().getDimension(R.dimen.fontSize));
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //GET HALF OF THE WIDTH AND HEIGHT AS WE ARE WORKING WITH A CIRCLE
        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = this.getMeasuredHeight() / 2;

        //GET THE RADIUS AS HALF OF THE WIDTH OR HEIGHT, WHICHEVER IS SMALLER
        //SUBTRACT HUNDRED SO THAT IT HAS SOME SPACE AROUND IT
        float radius = 0;
        if (viewWidthHalf > viewHeightHalf)
            radius = viewHeightHalf - 100;
        else
            radius = viewWidthHalf - 100;

        //SETTING PROPERTIES TO PAINT WITH
        setCircleCol(circleCol);
        setCircleTxt(circleTxt);
        setTextCol(textCol);
        setFontSize(fontSize);

        //DRAW CIRCLE
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);

        //DRAW TEXT
        canvas.drawText(circleTxt, viewWidthHalf, viewHeightHalf, textPaint);
    }

    //GETTER METHODS FOR MEMBER VARIABLES
    public int getCircleCol() {
        return circleCol;
    }

    public String getCircleTxt() {
        return circleTxt;
    }

    public int getTextCol() {
        return textCol;
    }

    //SETTER METHODS FOR MEMBER VARIABLES
    public void setCircleCol(int color) {
        circlePaint.setColor(color);
        //REDRAW THE VIEW
        invalidate();
        requestLayout();
    }

    public void setCircleTxt(String text) {
        //UPDATE THE INSTANCE VARIABLE
        circleTxt = text;
        //REDRAW THE VIEW
        invalidate();
        requestLayout();
    }

    public void setTextCol(int color) {
        textPaint.setColor(color);
        invalidate();
        requestLayout();
    }

    private void setFontSize(int fontSize) {
        textPaint.setTextSize(fontSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        invalidate();
        requestLayout();
    }
}

