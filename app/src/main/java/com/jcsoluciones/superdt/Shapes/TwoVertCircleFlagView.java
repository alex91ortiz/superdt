package com.jcsoluciones.superdt.shapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.jcsoluciones.superdt.R;

/**
 * Created by ADMIN on 14/01/2017.
 */
public class TwoVertCircleFlagView  extends View {

    public String mBodyColor1="#BAB399";
    public String mBodyColor2="#BAB399";
    public Context context;
    public TwoVertCircleFlagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    protected void  onDraw(Canvas canvas){

        Paint paint = new Paint();
        Paint paint12 = new Paint();
        Bitmap output = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_ball_soccer_flag_final);
        final Rect rectimg = new Rect(0, 0, 400, 400);
        RectF rect = new RectF(0,0,getWidth(),getHeight());
        RectF rect12 = new RectF(0,0,getWidth(),getHeight());
        paint.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor(mBodyColor1), Shader.TileMode.CLAMP));
        paint12.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor("#0c1e28"), Shader.TileMode.CLAMP));
        //canvas.drawArc(rect12,180,180,false,paint12);
        canvas.drawArc(rect,180,180,false,paint);
        Paint paint1 = new Paint();
        Paint paint11 = new Paint();
        RectF rect1 = new RectF(0,0,getWidth(),getHeight());
        RectF rect11 = new RectF(0,0,getWidth(),getHeight());
        paint1.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor(mBodyColor2), Shader.TileMode.CLAMP));
        paint11.setShader(new RadialGradient( 500, 500, 500, Color.BLACK, Color.parseColor("#0c1e28"), Shader.TileMode.CLAMP));
        //canvas.drawArc(rect11,0,180,false,paint11);
        canvas.drawArc(rect1,0,180,false,paint1);
        canvas.drawBitmap(output,rectimg,rectimg,paint);
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
