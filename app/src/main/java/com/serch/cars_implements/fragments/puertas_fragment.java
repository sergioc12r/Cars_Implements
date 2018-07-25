package com.serch.cars_implements.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.serch.cars_implements.R;

/**
 * Created by drago on 25/02/2018.
 */

public class puertas_fragment extends Fragment {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.puertas_fragment,container);
        mImageView = (ImageView)view.findViewById(R.id.im_puertas);
        mBitmap = Bitmap.createBitmap(200, 150, Bitmap.Config.ARGB_8888);
        mImageView.setImageBitmap(mBitmap);
        mCanvas = new Canvas(mBitmap);
        return view;
    }

    public void Recept(String color){

        Paint paint =new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        Paint paint2 =new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5);


        //Circulos de Color
        switch (color){
            case "Amarillo":
                paint2.setColor(Color.YELLOW);
                break;
            case "Azul":
                paint2.setColor(Color.BLUE);
                break;
            case "Blanco":
                paint2.setColor(Color.WHITE);
                break;
            case "Cafe":
                paint2.setColor(Color.rgb(121,85,61));
                break;
            case "Gris":
                paint2.setColor(Color.GRAY);
                break;
            case "Rojo":
                paint2.setColor(Color.RED);
                break;
            case "Negro":
                paint2.setColor(Color.BLACK);
                break;
            case "Morado":
                paint2.setColor(Color.MAGENTA);
                break;
            case "Verde":
                paint2.setColor(Color.GREEN);
                break;
        }
        int left=65,top=40,right=160,bottom=110;
        while(left!=right && top!=bottom) {
            mCanvas.drawRect(left + 5, top + 5, right - 5, bottom - 5, paint2);
            left+=1;
            top+=1;
            right-=1;
            bottom-=1;
        }

        int left2=50,top2=65,right2=80,bottom2=110;
        while(left2!=right2 && top2!=bottom2) {
            mCanvas.drawRect(left2 + 5, top2 + 5, right2 - 5, bottom2 - 5, paint2);
            left2+=1;
            top2+=1;
            right2-=1;
            bottom2-=1;
        }

        int x=50,y=70,x2=70,y2=40;
        while(x!=70) {
            mCanvas.drawLine(x,y,x2,y2,paint2);
            x+=1;x2+=1;
            y+=1;y2+=1;
        }

        //Circulos Generales
        mCanvas.drawLine(70,40,160,40,paint);
        mCanvas.drawLine(70,110,160,110,paint);
        mCanvas.drawLine(50,110,160,110,paint);

        mCanvas.drawLine(160,40,160,110,paint);
        mCanvas.drawLine(50,70,50,110,paint);

        mCanvas.drawLine(70,40,50,70,paint);


    }
}
