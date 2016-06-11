package com.zarate.jesus.personalityevaluator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

/**
 * Created by jesuszarate on 9/8/14.
 */
public class PieChart extends View {

    RectF _contentRect;
    float _radius;
    int _color = Color.RED;

    SliceOfPie[] slices;
    float currentAngle = 0;

//    public interface OnSplotchTouchListener{
//        public void onSplotchTouched(PieChart v);
//    }
//    OnSplotchTouchListener _onSplotchTouchListener = null;

    public PieChart(Context context, SliceOfPie[] slices) {
        super(context);
        this.setBackgroundColor(Color.BLACK);

        setMinimumHeight(100);
        setMinimumWidth(100);

        this.slices = slices;
        // Colors: OPACITY/RED/GREEN/BLUE
        //this.setBackgroundColor(0XFF228844);
    }

//    //Converts percentage into angle in degrees
//    public double getAngle(double percentage){
//        return (percentage/100) * 2 * Math.PI;
//    }

//    public int getColor() {
//        return _color;
//    }

//    public void setColor(int _color) {
//        this._color = _color;
//
//        // Redraws the circle so it can be the new color.(Marks it for redraw,
//        // then generates a onDraw() when it's ready)
//        invalidate();
//    }

//    // The parameter is the interface type.
//    public void setOnSplotchTouchListener(OnSplotchTouchListener listener){
//        _onSplotchTouchListener = listener;
//    }
//
//    public OnSplotchTouchListener getOnSplotchTouchListener(){
//        return _onSplotchTouchListener;
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        float x = event.getX();
//        float y = event.getY();
//
//        // Check if click is inside the circle, by measuring the distance
//        //  between the center of the circle and the radius of the circle.
//        // -> If the point clicked is less than the radius of the circle
//        //    then it is a click.
//        float CircleCenterX = _contentRect.centerX();
//        float CircleCenterY = _contentRect.centerY();
//
//        // Distance formula-> sqrt((x1 - x2)^2 + (y1 - y2)^2)
//        float distance = (float) Math.sqrt(Math.pow(CircleCenterX - x, 2) + Math.pow(CircleCenterY - y, 2));
//        if (distance < _radius) {
//            Log.i("paint_view", "Touched inside the circle");
//            if (_onSplotchTouchListener != null)
//            {
//                _onSplotchTouchListener.onSplotchTouched(this);
//            }
//        }
//
//        return super.onTouchEvent(event);
//    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(_color);
        Path path = new Path();

        _contentRect = new RectF();
        _contentRect.left = getPaddingLeft();
        _contentRect.top = getPaddingTop();
        _contentRect.right = getWidth() - getPaddingRight();
        _contentRect.bottom = getHeight() - getPaddingBottom();

        for(int i = 0; i < slices.length; i++)
        {
            paint.setColor(slices[i].color);
            canvas.drawArc(_contentRect, currentAngle, (float) slices[i].angle, true, paint);

            currentAngle += slices[i].angle;
        }

//        for(int i = 0; i < slices.length; i++)
//        {
//            paint.setColor(slices[i].color);
//            canvas.drawRect(headerPadding, currentHeaderPosition, lengthOfHeaderSquares, currentHeaderPosition+50, paint);
//
//            currentHeaderPosition += lengthOfHeaderSquares + headerPadding;
//        }


//
//        PointF circleCenter = new PointF(_contentRect.centerX(), _contentRect.centerY());
//        float maxRadius = Math.min(_contentRect.width() * 0.3f, _contentRect.height() * 0.3f);
//        float minRadius = 0.25f * maxRadius;
//        _radius = minRadius + (maxRadius - minRadius) * 0.5f;
//
//        int pointCount = 30;
//
//        for(int pointIndex = 0; pointIndex < pointCount; pointIndex++){
//            PointF point = new PointF();
//
//            point.x = circleCenter.x + _radius * (float)Math.cos(((double) pointIndex /
//                    (double) pointCount) * 2.0 * Math.PI);
//
//            point.y = circleCenter.y + _radius * (float)Math.sin(((double) pointIndex /
//                    (double) pointCount) * 2.0 * Math.PI);
//
//
//
//            if(currentAngle == 0)
//            {
//                paint.setColor(Color.CYAN);
//            }
//            if(pointIndex == 0){
//                path.moveTo(point.x, point.y);
//            }
//            else
//            {
//                path.lineTo(point.x, point.y);
//            }
//
//        }


        //canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // First four lines are extract the bit mask
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // Pull the information associated with the Mode.
        // -> Unspecified - widthSpec, heightSpec contain no value, usually 0.
        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpec = MeasureSpec.getSize(heightMeasureSpec);

        int width = getSuggestedMinimumWidth();
        int height = getSuggestedMinimumHeight();

        if (widthMode == MeasureSpec.AT_MOST){
            width = widthSpec;
        }
        if (heightMode == MeasureSpec.AT_MOST){
            height = heightSpec;
        }

        if (widthMode ==  MeasureSpec.EXACTLY){
            width = widthSpec;
            height = width;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSpec;
            width = height;
        }

        // TODO; RESPECT THE PADDING!
        if (width > height && widthMode != MeasureSpec.EXACTLY){
            width = height;
        }
        if (height > width && heightMode != MeasureSpec.EXACTLY){
            height = width;
        }

        // resolveSizeAndState(int size, int measureSpec, int childMeasuredState)
        // -> childMeasuredState - boolean asks if you are happy with the size or not.
        setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec,
                        width < getSuggestedMinimumWidth() ? MEASURED_STATE_TOO_SMALL: 0),
                resolveSizeAndState(height, heightMeasureSpec,
                        height < getSuggestedMinimumHeight() ? MEASURED_STATE_TOO_SMALL: 0));
    }
}
