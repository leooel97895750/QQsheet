package com.leooel97895750.qqsheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuPresenter;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    String[] sheet=new String[] {"尼爾","四月是你的謊言ost","a thousand year","faded","魔女宅急便","IB記憶","^_^","^_^","^_^","^_^","^_^"};
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////SHEET//////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    String fsheet="jevxmoqieuxqqhevxmqogcsvoyfcvxmomeaqvmvdapsvmxelquqyjevvxxmmooqqieuxeqilqqhevvxxemmjqqoogcsvoyfcvxfmjomeaejxvuelquq" +
            "vjejlaeyjnoqbhjneqtrcqjocqfrjohchxlocryqahaceqjejxlnbtrcvjocqfrjolfvjxlmcoyqelileqqjevjxemmjooaqqieuixemmlooiqqhevjxlmmjqqeyoogcsgv" +
            "jvvcoofcvjxlvvjxxfmmeeqjvamemmddpgvjxxavvjyuueeqqilqqevjqevvjxxlmmjooeqqiqeulxiqelqhevvjxxlxxjmmeyoogcogsjvcovvfcvjxlttjrrfqqeeq" +
            "jvammexxelqeuiqlqyvjejlbevxnqvxmcjcexfvjxhchxjmlocyolhtcfrchqahaehlmjejljoeqhejlaqjyohchjtlrqahacmerjfmjxlmaocjocmfojytthcxfvxothc" +
            "hvjqevvjxxlmmjooeqqiqeulxiqelyqqhevvjxxlvvaqqeoogcgjvvcoofcvvjxxlmmjoocmmeaxxejvvaydavvgxxjmmaoojxxelqeuiqlocmjxevvjxxlmmjooe" +
            "qqiexliveulyvhqevjxlmjqeogcsgvjocfcvjxlmjofmeeqjvameyxelqeuiqlxjevxmoqieuxqqhevxmqyogcsvofcvxmomeatvmvdapsvmyxelquqjevaxmmoo" +
            "qqieuxmmooqqhevxmmqqyoogcsvvvoofcvxvvxxmmeaqvqqxxelquvvuuyvvjevxmqv";
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    private ListView sheetlist;
    private TextView songname;
    private Button startbutton;
    private int posvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隱藏title bar
        getSupportActionBar().hide();
        //隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //隱藏時間電池
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sheetlist=(ListView)findViewById(R.id.sheetlist);
        songname=(TextView)findViewById(R.id.textView);
        startbutton=(Button)findViewById(R.id.button);

        startbutton.setOnClickListener(startbuttonlistener);

        ArrayAdapter<String> adaptersheet=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sheet);
        sheetlist.setAdapter(adaptersheet);

        sheetlist.setOnItemClickListener(sheetlistener);
    }

    private Button.OnClickListener startbuttonlistener=new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,sheetstart.class);

            //傳送posvalue到分頁
            Bundle test1=new Bundle();
            test1.putInt("posvalue",posvalue);
            intent.putExtras(test1);

            Bundle passsheet=new Bundle();
            Bundle passmax=new Bundle();
            switch(posvalue)
            {
                case 0:{
                    break;
                }
                case 1:{
                    break;
                }
                case 2:{
                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    passsheet.putString("fsheet",fsheet);
                    intent.putExtras(passsheet);
                    break;
                }
            }

            startActivity(intent);
        }
    };
    private ListView.OnItemClickListener sheetlistener=new ListView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent,View view,int position,long id)
        {
            //記錄按到第幾個位置
            posvalue=position;
            String nameoflist=parent.getItemAtPosition(position).toString();
            songname.setText("曲名   "+nameoflist);
        }
    };
}
