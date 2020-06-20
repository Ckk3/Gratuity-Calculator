package com.example.calculadordegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private SeekBar             seekbar;
    private TextView            viewGorjeta, viewTotal, viewSeekBar;
    private TextInputEditText   inputValor;
    private double              valor, gorjeta, total;
    private String              toast;
    private int                 progresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar         = findViewById(R.id.seekBar);
        viewGorjeta     = findViewById(R.id.viewGorjeta);
        viewSeekBar     = findViewById(R.id.viewSeekBar);
        viewTotal       = findViewById(R.id.viewTotal);
        inputValor      = findViewById(R.id.inputConta);
        toast           = getString(R.string.toast);
        progresso       = 0;


        ouvidorSeekBar();

    }

    public void calculo(double gorjeta, int progresso){
        gorjeta = (progresso / 100.0) * valor;

        total = valor + gorjeta;

        viewGorjeta.setText("R$" + Double.toString(gorjeta));
        viewTotal.setText("R$" + Double.toString(total));
    }


    public void ouvidorSeekBar(){

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewSeekBar.setText(progress+"%");


                if(inputValor.getText().toString().equals("")){
                    //Toast.makeText(getApplicationContext(),toast,Toast.LENGTH_LONG).show();
                    valor = 0.0;

                }else {
                    valor = Double.parseDouble(inputValor.getText().toString());


                }
                progresso = progress;
                calculo(gorjeta, progresso);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(inputValor.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),toast,Toast.LENGTH_LONG).show();
                    valor = 0.0;


                }else {
                    valor = Double.parseDouble(inputValor.getText().toString());


                }

                calculo(gorjeta, progresso);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(inputValor.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),toast,Toast.LENGTH_LONG).show();
                    valor = 0.0;

                }else {
                    valor = Double.parseDouble(inputValor.getText().toString());


                }
                calculo(gorjeta, progresso);

            }
        });

    }
}