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

public class demas_fragment extends Fragment {


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
        view = inflater.inflate(R.layout.demas_fragment,container);
        mImageView = (ImageView)view.findViewById(R.id.im_demas);
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
        paint2.setStrokeWidth(1);




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


        int j=65,j2=85;
        while(j!=45){
            mCanvas.drawLine(28,60,90,j,paint2);
            mCanvas.drawLine(28,90,90,j2,paint2);
            mCanvas.drawLine(170,60,110,j,paint2);
            mCanvas.drawLine(170,90,110,j2,paint2);
            j-=1;j2+=1;
        }

        int y=60,y2=170;
        while(y!=60+30){
            mCanvas.drawLine(28,y,90,y,paint2);
            mCanvas.drawLine(110,y,170,y,paint2);
            y+=1;
        }




        //Circulos Generales
        mCanvas.drawLine(30,60,30,90,paint);
        mCanvas.drawLine(90,45,90,105,paint);
        mCanvas.drawLine(30,60,90,45,paint);
        mCanvas.drawLine(30,90,90,105,paint);


        mCanvas.drawLine(170,60,170,90,paint);
        mCanvas.drawLine(110,45,110,105,paint);
        mCanvas.drawLine(170,60,110,45,paint);
        mCanvas.drawLine(170,90,110,105,paint);



    }
}
