package com.leooel97895750.qqsheet;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.WindowManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.os.Vibrator;
import android.os.Handler;
import android.app.Service;

public class sheetstart extends AppCompatActivity{

    private Button backbutton;
    private TextView resulttext;
    private TextView textView3;
    private TextView timeview;
    private int posvalue;
    private String posletter;
    private int pospicture=0;
    private Button dol,rel,mil,fal,sol,lal,sil;
    private Button dor,rer,mir,far,sor,lar,sir;
    private Button udol,urel,ufal,usol,ulal;
    private Button udor,urer,ufar,usor,ular;
    private ImageView sheetview;
    private String sheetans;
    private int mistake=0;
    private int position=0;
    private ConstraintLayout backcolor;
    private long starttime;
    private long endtime;
    private Vibrator mVibrator;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sheetpage);

        //隱藏title bar
        getSupportActionBar().hide();
        //隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //隱藏時間電池
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backbutton=(Button)findViewById(R.id.button2);
        resulttext=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        timeview=(TextView)findViewById(R.id.textView4);
        sheetview=(ImageView)findViewById(R.id.imageView2);
        backcolor = (ConstraintLayout) findViewById(R.id.back);
        mVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        ////////////////////////////////////////////
        dol=(Button)findViewById(R.id.dol);
        rel=(Button)findViewById(R.id.rel);
        mil=(Button)findViewById(R.id.mil);
        fal=(Button)findViewById(R.id.fal);
        sol=(Button)findViewById(R.id.sol);
        lal=(Button)findViewById(R.id.lal);
        sil=(Button)findViewById(R.id.sil);
        ////////////////////////
        dor=(Button)findViewById(R.id.dor);
        rer=(Button)findViewById(R.id.rer);
        mir=(Button)findViewById(R.id.mir);
        far=(Button)findViewById(R.id.far);
        sor=(Button)findViewById(R.id.sor);
        lar=(Button)findViewById(R.id.lar);
        sir=(Button)findViewById(R.id.sir);
        /////////////////////////
        udol=(Button)findViewById(R.id.udol);
        urel=(Button)findViewById(R.id.urel);
        ufal=(Button)findViewById(R.id.ufal);
        usol=(Button)findViewById(R.id.usol);
        ulal=(Button)findViewById(R.id.ulal);
        /////////////////////////
        udor=(Button)findViewById(R.id.udor);
        urer=(Button)findViewById(R.id.urer);
        ufar=(Button)findViewById(R.id.ufar);
        usor=(Button)findViewById(R.id.usor);
        ular=(Button)findViewById(R.id.ular);
        ////////////////////////////////////////////
        dol.setOnClickListener(keyboardlistener);
        rel.setOnClickListener(keyboardlistener);
        mil.setOnClickListener(keyboardlistener);
        fal.setOnClickListener(keyboardlistener);
        sol.setOnClickListener(keyboardlistener);
        lal.setOnClickListener(keyboardlistener);
        sil.setOnClickListener(keyboardlistener);
        ////////////////////////
        dor.setOnClickListener(keyboardlistener);
        rer.setOnClickListener(keyboardlistener);
        mir.setOnClickListener(keyboardlistener);
        far.setOnClickListener(keyboardlistener);
        sor.setOnClickListener(keyboardlistener);
        lar.setOnClickListener(keyboardlistener);
        sir.setOnClickListener(keyboardlistener);
        ////////////////////////
        udol.setOnClickListener(keyboardlistener);
        urel.setOnClickListener(keyboardlistener);
        ufal.setOnClickListener(keyboardlistener);
        usol.setOnClickListener(keyboardlistener);
        ulal.setOnClickListener(keyboardlistener);
        ///////////////////////
        udor.setOnClickListener(keyboardlistener);
        urer.setOnClickListener(keyboardlistener);
        ufar.setOnClickListener(keyboardlistener);
        usor.setOnClickListener(keyboardlistener);
        ular.setOnClickListener(keyboardlistener);
        ////////////////////////////////////////////

        backbutton.setOnClickListener(backbuttonlistener);
        //接收posvalue
        Intent intent=this.getIntent();
        Bundle test1=intent.getExtras();
        posvalue=test1.getInt("posvalue");

        starttime = System.currentTimeMillis();
        //設定定時要執行的方法
        handler.removeCallbacks(updateTimer);
        //設定Delay的時間
        handler.postDelayed(updateTimer, 1000);

        //按照曲子的位置分配圖片名稱的字首
        switch(posvalue)
        {
            case 0:{
                posletter="a";
                break;
            }
            case 1:{
                posletter="b";
                break;
            }
            case 2:{
                posletter="c";
                break;
            }
            case 3:{
                posletter="d";
                break;
            }
            case 4:{
                posletter="e";
                break;
            }
            case 5:{
                posletter="f";
                Bundle catchsheet=intent.getExtras();
                sheetans=catchsheet.getString("fsheet");
                break;
            }
        }
        //程式初始化第一章譜面
        pospicture=1;
        String uri = "@drawable/" + posletter+"_"+pospicture; //圖片路徑和名稱
        int imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子
        sheetview.setImageResource(imageResource);
    }

    private Runnable updateTimer = new Runnable() {
        public void run() {
            Long spentTime = System.currentTimeMillis() - starttime;
            //計算目前已過分鐘數
            Long minius = (spentTime/1000)/60;
            //計算目前已過秒數
            Long seconds = (spentTime/1000) % 60;
            timeview.setText(minius+":"+seconds);
            handler.postDelayed(this, 1000);
        }
    };
    //結束此activity回到主選單
    private Button.OnClickListener backbuttonlistener=new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            finish();
        }
    };

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //對所有琴鍵的偵測
    private Button.OnClickListener keyboardlistener=new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.dol:
                {
                    textView3.setText("a");
                    if(sheetans.charAt(position)=='a')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.udol:
                {
                    textView3.setText("b");
                    if(sheetans.charAt(position)=='b')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.rel:
                {
                    textView3.setText("c");
                    if(sheetans.charAt(position)=='c')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.urel:
                {
                    textView3.setText("d");
                    if(sheetans.charAt(position)=='d')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.mil:
                {
                    textView3.setText("e");
                    if(sheetans.charAt(position)=='e')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.fal:
                {
                    textView3.setText("f");
                    if(sheetans.charAt(position)=='f')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.ufal:
                {
                    textView3.setText("g");
                    if(sheetans.charAt(position)=='g')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.sol:
                {
                    textView3.setText("h");
                    if(sheetans.charAt(position)=='h')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.usol:
                {
                    textView3.setText("i");
                    if(sheetans.charAt(position)=='i')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.lal:
                {
                    textView3.setText("j");
                    if(sheetans.charAt(position)=='j')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.ulal:
                {
                    textView3.setText("k");
                    if(sheetans.charAt(position)=='k')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.sil:
                {
                    textView3.setText("l");
                    if(sheetans.charAt(position)=='l')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                ////////////////////////////////////////////////////////
                case R.id.dor:
                {
                    textView3.setText("m");
                    if(sheetans.charAt(position)=='m')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.udor:
                {
                    textView3.setText("n");
                    if(sheetans.charAt(position)=='n')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.rer:
                {
                    textView3.setText("o");
                    if(sheetans.charAt(position)=='o')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.urer:
                {
                    textView3.setText("p");
                    if(sheetans.charAt(position)=='p')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.mir:
                {
                    textView3.setText("q");
                    if(sheetans.charAt(position)=='q')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.far:
                {
                    textView3.setText("r");
                    if(sheetans.charAt(position)=='r')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.ufar:
                {
                    textView3.setText("s");
                    if(sheetans.charAt(position)=='s')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.sor:
                {
                    textView3.setText("t");
                    if(sheetans.charAt(position)=='t')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.usor:
                {
                    textView3.setText("u");
                    if(sheetans.charAt(position)=='u')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.lar:
                {
                    textView3.setText("v");
                    if(sheetans.charAt(position)=='v')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.ular:
                {
                    textView3.setText("w");
                    if(sheetans.charAt(position)=='w')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
                case R.id.sir:
                {
                    textView3.setText("x");
                    if(sheetans.charAt(position)=='x')
                    {
                        position++;
                        backcolor.setBackgroundColor(0xCC33CC33);
                    }
                    else
                    {
                        mistake++;
                        backcolor.setBackgroundColor(0xFFFF0000);
                    }
                    break;
                }
            }
            mVibrator.vibrate(5);
            if(position==sheetans.length())//上限值
            {
                //結束程式//存檔//finsih();
                endtime = System.currentTimeMillis();
                long totaltime=endtime-starttime;
                timeview.setText("time : "+totaltime/1000+" s");
                backcolor.setBackgroundColor(0xCC0000CC);
                position=sheetans.length()-1;
            }
            if(sheetans.charAt(position)=='y')
            {
                pospicture++;
                String uri = "@drawable/" + posletter+"_"+pospicture; //圖片路徑和名稱
                int imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子
                sheetview.setImageResource(imageResource);
                position++;
            }
            resulttext.setText("bad : "+mistake);
        }
    };
}
