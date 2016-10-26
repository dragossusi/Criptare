package com.example.dragos.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText prelucrat = (EditText)findViewById(R.id.editText3);
                if(prelucrat.getText().length()!=0) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, prelucrat.getText().toString());
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
                else {
                    Snackbar.make(view, "Nimic de trimis", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    int poz,v[]=new int[50];
    String mesajc="";

    ///Criptare
    public void prelucrare(String mesaj,String parola) {
        for(int i = 0 ; i < parola.length() && poz != mesaj.length() - 1; ++i) {
            if(v[i]!=-1) {
                int c=parola.charAt(v[i])+mesaj.charAt(poz)-128;
                mesajc=mesajc+Character.toString((char) c);
                ++poz;
            }
        }
    }
    public void back(String mesaj, String parola,int k) {
        for(int i=-1;i<=parola.length()-1;++i) {
            v[k]=i;
            if (k==parola.length()-1)prelucrare(mesaj, parola);
            else
            if(poz!=mesaj.length()-1)
                back(mesaj,parola,k+1);
            else return;
        }
    }
    ///Decriptare
    private void prelucrared(String mesaj, String parola) {
        for (int i = 0; i < parola.length() && poz != mesaj.length() - 1; ++i) {
            if (v[i] != -1) {
                int c = 128 + mesaj.charAt(poz) - parola.charAt(v[i]);
                mesajc = mesajc + Character.toString((char) c);
                ++poz;
            }
        }
    }
    public void backd(String mesaj, String parola,int k) {
        for(int i=-1;i<=parola.length()-1;++i) {
            v[k]=i;
            if (k==parola.length()-1)prelucrared(mesaj, parola);
            else
            if(poz!=mesaj.length()-1)
                backd(mesaj, parola, k + 1);
            else return;
        }
    }


    public void criptare(View view) {

        ///declarari
        EditText tmesaj = (EditText)findViewById(R.id.editText);
        EditText tparola = (EditText)findViewById(R.id.editText2);
        EditText prelucrat = (EditText)findViewById(R.id.editText3);
        String mesaj = tmesaj.getText().toString() + "a";
        String parola = tparola.getText().toString();
        ///declarari

        if(parola.length()!=0) {
            back(mesaj,parola,0);
            if(mesajc.length()==mesaj.length()-1)
                prelucrat.setText(mesajc);
            else {
                Snackbar.make(view, "Parola e prea scurta", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            poz=0;
            mesajc="";
        }
        else {
            Snackbar.make(view, "Introdu o parola", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void decriptare(View view) {

        ///declarari
        EditText tmesaj = (EditText)findViewById(R.id.editText);
        EditText tparola = (EditText)findViewById(R.id.editText2);
        EditText prelucratd = (EditText)findViewById(R.id.editText3);
        String mesaj = tmesaj.getText().toString() + "a";
        String parola = tparola.getText().toString();
        ///declarari

        if(parola.length()!=0) {
            ///decriptarea
            backd(mesaj,parola,0);
            if(mesajc.length()==mesaj.length()-1)
                prelucratd.setText(mesajc);
            else {
                Snackbar.make(view, "Parola e prea scurta", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            poz=0;
            mesajc="";
        } else {
            Snackbar.make(view, "Introdu o parola", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }
}
