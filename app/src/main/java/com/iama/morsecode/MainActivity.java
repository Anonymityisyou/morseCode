package com.iama.morsecode;

import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;




public class MainActivity extends AppCompatActivity {

     HashMap<Character,String> morse;
     EditText editText;
    Button swapButton;
    TextView mt;
    static int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.text);
        mt = findViewById(R.id.morsetv);
        swapButton = findViewById(R.id.swapButton);
        morse = new HashMap<Character, String>();
        morse.put('a', ". -");
        morse.put('b', "- . . .");
        morse.put('c',  "- . - .");
        morse.put('d',  "- . .");
        morse.put('e',    ".");
        morse.put('f', ". . - .");
        morse.put('g',  "- - .");
        morse.put('h', ". . . .");
        morse.put('i',   ". .");
        morse.put('j', ". - - -");
        morse.put('k',   "- . -");
        morse.put('l', ".- . .");
        morse.put('m',   "- -");
        morse.put('n',   "- .");
        morse.put('o',  "- - -");
        morse.put('p', ". - - .");
        morse.put('q', "- - . -");
        morse.put('r', ". - .");
        morse.put('s',  ". . .");
        morse.put('t',   "-");
        morse.put('u',  ". . -");
        morse.put('v', ". . . -");
        morse.put('w',  ". - -");
        morse.put('x', "- . . -");
        morse.put('y', "- . - -");
        morse.put('z', "- - . .");
        morse.put('1', ". - - - -");
        morse.put('2',". . - - -");
        morse.put('3', ". . . - -");
        morse.put('4', ". . . . -");
        morse.put('5', ". . . . .");
        morse.put('6', "- . . . .");
        morse.put('7', "- - . . .");
        morse.put('8', "- - - . .");
        morse.put('9', "- - - - .");
        morse.put('0', "- - - - -");
        morse.put(' ',"   ");


        editText.addTextChangedListener(ttm);
       swapButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(flag==1)  //text to morse
               {
                   editText.setText("");
                   editText.setHint("Enter text");
                    editText.addTextChangedListener(ttm);
                    flag=0;
               }
               else
               {
                   editText.setText("");
                   editText.setHint("Enter Morse Code");
                   editText.addTextChangedListener(mtt);
                   flag=1;
               }

           }
       });




    }

    TextWatcher ttm = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence sr, int start, int before, int count) {

            {
                String value = sr.toString();
                //Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
                if(sr.length()!=0)
                {
                    char s[] = value.toCharArray();
                    int i=0;
                    String m="";
                    while(s[i]!='\0')
                    {
                        if(morse.get(value.charAt(i))!=null)
                        {
                            m = m+ morse.get(value.charAt(i));
                            m=m+"   ";
                        }
                        else
                        {
                            m=m+"       ";
                        }

                        i++;
                        if(i==s.length)
                            break;
                    }
                    mt.setText(m);
                }
                else
                {
                    mt.setText("");
                }



            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    TextWatcher mtt = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String value = s.toString();
            String m="";
            if(value.contains("       "))
            {
                String str[] = value.split("       ");
                {
                    for(int i=0;i<str.length;i++)
                    {
                        if(str[i].contains("   "))
                        {
                            String str3[] = str[i].split("   ");
                            for(int j=0;j<str3.length;j++)
                            {
                                if(morse.containsValue(str3[j]))
                                {

                                    for(Map.Entry<Character,String> entry : morse.entrySet())
                                    {
                                        if (str3[j].equals(entry.getValue().toString()))
                                        {
                                            m=m+entry.getKey().toString();
                                        }
                                    }
                                    // Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                        else
                        if(morse.containsValue(str[i]))
                        {

                            for(Map.Entry<Character,String> entry : morse.entrySet())
                            {
                                if (str[i].equals(entry.getValue().toString()))
                                {
                                    m=m+entry.getKey().toString();
                                }
                            }
                            //Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT).show();

                        }

                        m= m+" ";
                    }
                }
            }
            else
            {

                if(value.contains("   "))
                {
                    String str3[] = value.split("   ");
                    for(int j=0;j<str3.length;j++)
                    {
                        if(morse.containsValue(str3[j]))
                        {

                            for(Map.Entry<Character,String> entry : morse.entrySet())
                            {
                                if (str3[j].equals(entry.getValue().toString()))
                                {
                                    m=m+entry.getKey().toString();
                                }
                            }
                            // Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT).show();

                        }
                    }
                }else
                {

                    for(Map.Entry<Character,String> entry : morse.entrySet())
                    {
                        if (value.equals(entry.getValue().toString()))
                        {
                            m=m+entry.getKey().toString();
                        }
                    }
                }

            }
            mt.setText(m);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
