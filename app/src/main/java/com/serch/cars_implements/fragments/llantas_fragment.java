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

public class llantas_fragment extends Fragment {


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
        view = inflater.inflate(R.layout.llantas_fragment,container);
        mImageView = (ImageView)view.findViewById(R.id.im_llantas);
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
        paint2.setStrokeWidth(25);




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
        mCanvas.drawCircle(100,75,15,paint2);
        mCanvas.drawCircle(100,75,35,paint2);
        mCanvas.drawCircle(100,75,1,paint2);

        //Circulos Generales
        mCanvas.drawCircle(100,75,25,paint);
        mCanvas.drawCircle(100,75,50,paint);

    }


}
