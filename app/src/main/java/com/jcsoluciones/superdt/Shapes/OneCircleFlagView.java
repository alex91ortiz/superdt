package com.jcsoluciones.superdt.Shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ADMIN on 14/01/2017.
 */
public class OneCircleFlagView extends View
{
    public String mBodyColor="#BAB399";

    public OneCircleFlagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Paint paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);
        paint.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor(mBodyColor), Shader.TileMode.CLAMP));
        paint1.setShader(new RadialGradient( 500, 500, 500,  Color.BLACK, Color.parseColor("#0c1e28"), Shader.TileMode.CLAMP));
        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,paint1);
        canvas.drawCircle((getWidth()/2)+1,(getHeight()/2)+1,(getHeight()/2)-15,paint);

    }

    public void changeColor(String color) {
        mBodyColor = color;
        invalidate();
    }
}
