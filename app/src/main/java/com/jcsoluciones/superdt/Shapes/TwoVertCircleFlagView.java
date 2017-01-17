package com.jcsoluciones.superdt.Shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ADMIN on 14/01/2017.
 */
public class TwoVertCircleFlagView  extends View {

    public String mBodyColor1="#BAB399";
    public String mBodyColor2="#BAB399";

    public TwoVertCircleFlagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void  onDraw(Canvas canvas){

        Paint paint = new Paint();
        Paint paint12 = new Paint();
        RectF rect = new RectF(15,15,getWidth()-15,getHeight()-15);
        RectF rect12 = new RectF(0,0,getWidth(),getHeight());
        paint.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor(mBodyColor1), Shader.TileMode.CLAMP));
        paint12.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor("#0c1e28"), Shader.TileMode.CLAMP));
        canvas.drawArc(rect12,180,180,false,paint12);
        canvas.drawArc(rect,180,180,false,paint);
        Paint paint1 = new Paint();
        Paint paint11 = new Paint();
        RectF rect1 = new RectF(15,15,getWidth()-15,getHeight()-15);
        RectF rect11 = new RectF(0,0,getWidth(),getHeight());
        paint1.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor(mBodyColor2), Shader.TileMode.CLAMP));
        paint11.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor("#0c1e28"), Shader.TileMode.CLAMP));
        canvas.drawArc(rect11,0,180,false,paint11);
        canvas.drawArc(rect1,0,180,false,paint1);

    }
    public void changeColor1(String color) {
        mBodyColor1 = color;
        invalidate();
    }
    public void changeColor2(String color) {
        mBodyColor2 = color;
        invalidate();
    }
}
