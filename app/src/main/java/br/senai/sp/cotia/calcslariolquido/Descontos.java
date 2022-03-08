package br.senai.sp.cotia.calcslariolquido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Descontos extends AppCompatActivity {

    private TextView result, porcentagem, resultado, porcent;
    private double salLiq, porc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descontos);

        getIntent().hasExtra("salLiq");
        getIntent().hasExtra("porc");

       salLiq = getIntent().getDoubleExtra("salLiq",0);
       porc = getIntent().getDoubleExtra("porc",0);

        result = findViewById(R.id.result);
        porcentagem = findViewById(R.id.porcentagem);
        resultado = findViewById(R.id.resultado);
        porcent = findViewById(R.id.porcent);

        result.setText(getString(R.string.resultado,salLiq));
        porcentagem.setText(getString(R.string.porcent,porc));


    }
}