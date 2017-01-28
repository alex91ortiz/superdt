package com.jcsoluciones.superdt.shapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.jcsoluciones.superdt.R;

/**
 * Created by ADMIN on 14/01/2017.
 */
public class OneCircleFlagView extends View
{
    public String mBodyColor="#BAB399";
    public Context context;
    public OneCircleFlagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Paint paint1 = new Paint();
        Bitmap output = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_ball_soccer_flag_final);
        final Rect rect = new Rect(0, 0, 600, 600);
        paint1.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);
        paint.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor(mBodyColor), Shader.TileMode.MIRROR));
        paint1.setShader(new RadialGradient( 500, 500, 500,  Color.GRAY, Color.GRAY, Shader.TileMode.MIRROR));
        //canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,paint1);
        canvas.drawCircle((getWidth()/2),(getHeight()/2),(getHeight()/2),paint);
        canvas.drawBitmap(output,rect,rect,paint);


    }

    public void changeColor(String color) {
        mBodyColor = color;
        invalidate();
    }
}
