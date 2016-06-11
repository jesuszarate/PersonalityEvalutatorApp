package com.zarate.jesus.personalityevaluator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Jeanette on 6/9/16.
 */
public class PieHeader extends View {

    SliceOfPie[] slices;

    //For header
    float headerPadding = 10;
    float currentHeaderPosition = -450;
    float lengthOfHeaderSquares = 50;


    public PieHeader(Context context, SliceOfPie[] slices)
    {
        super(context);
        setMinimumHeight(100);
        setMinimumWidth(100);

        this.slices = slices;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        for(int i = 0; i < slices.length; i++)
        {
            paint.setColor(slices[i].color);
            canvas.drawRect(headerPadding, currentHeaderPosition, lengthOfHeaderSquares, currentHeaderPosition+50, paint);
            currentHeaderPosition += lengthOfHeaderSquares + headerPadding;
        }

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
