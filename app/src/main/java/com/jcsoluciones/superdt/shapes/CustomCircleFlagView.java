package com.jcsoluciones.superdt.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ADMIN on 14/01/2017.
 */
public class CustomCircleFlagView extends View
{

    public CustomCircleFlagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        RectF rect = new RectF(0,0,getWidth(),getHeight());
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawArc(rect,-270,180,false,paint);
        Paint paint1 = new Paint();
        RectF rect1 = new RectF(0,0,getWidth(),getHeight());
        paint1.setColor(Color.parseColor("#BAB288"));
        canvas.drawArc(rect1,90,-180,false,paint1);
    }
}
