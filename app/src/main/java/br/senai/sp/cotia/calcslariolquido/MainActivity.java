package br.senai.sp.cotia.calcslariolquido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.core.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edit_bruto, edit_dependentes;
    private Button bt_calcular;
    private RadioGroup group_ps,group_vt, group_va, group_vr;
    private RadioButton standard, basico, soper, mester, sim, nao, sim2, nao2, sim3, nao3;
    private TextView resultado, result, porcent, porcentagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_bruto = findViewById(R.id.edit_bruto);
        edit_dependentes = findViewById(R.id.edit_dependentes);
        group_ps = findViewById(R.id.group_ps);
        group_vt = findViewById(R.id.group_vt);
        group_va = findViewById(R.id.group_va);
        group_vr = findViewById(R.id.group_vr);
        standard = findViewById(R.id.standard);
        basico = findViewById(R.id.basico);
        soper = findViewById(R.id.soper);
        mester = findViewById(R.id.mester);
        sim = findViewById(R.id.sim);
        sim2 = findViewById(R.id.sim2);
        sim3 = findViewById(R.id.sim3);
        nao = findViewById(R.id.nao);
        nao2 = findViewById(R.id.nao2);
        nao3 = findViewById(R.id.nao3);
        bt_calcular = findViewById(R.id.bt_calcular);
        result = findViewById(R.id.result);
        resultado = findViewById(R.id.resultado);
        porcent = findViewById(R.id.porcent);
        porcentagem = findViewById(R.id.porcentagem);

        bt_calcular.setOnClickListener(v -> {

            // Declaração de variáveis
            double salBr,salLiq, inss, vr, vt, va, irrf, ps = 0 , base_de_calc, porc;
            salBr = Double.parseDouble(edit_bruto.getText().toString());
            int dependente = 0;
            dependente = Integer.parseInt(edit_dependentes.getText().toString());



            //Estrutura condicional para os planos de saúde

            group_ps.getCheckedRadioButtonId();

            if (salBr <= 3000) {

                switch (group_ps.getCheckedRadioButtonId()) {

                    case R.id.standard:
                        ps = 60;
                        break;

                    case R.id.basico:
                        ps = 80;
                        break;

                    case R.id.soper:
                        ps = 95;
                        break;

                    case R.id.mester:
                        ps = 130;
                        break;

                    default:
                        break;

                }

            }

            if (salBr > 3000) {
                switch (group_ps.getCheckedRadioButtonId()) {
                    case R.id.standard:
                        ps = 80;
                        break;

                    case R.id.basico:
                        ps = 110;
                        break;

                    case R.id.soper:
                        ps = 135;
                        break;

                    case R.id.mester:
                        ps = 180;
                        break;

                    default:
                        break;
                }
            }

            //Estrutura condicional para o cálculo do INSS
            if (salBr <= 1212) {
                inss = salBr * 7.5 / 100;

            } else if (salBr <= 2427.35) {
                inss = salBr * 9 / 100 - 18.18;

            } else if (salBr <= 3641.03) {
                inss = salBr * 12 / 100 - 91;

            } else if (salBr <= 7087.22) {
                inss = salBr * 14 / 100 - 163.82;

            } else {
                inss = 828.39;
            }



            //Cálculo VT
            if(sim.isChecked()) {

                vt = salBr * 6 / 100  ;

            }else {

                vt = 0;
            }



            //Cálculo VA
            if(sim2.isChecked()) {

                if(salBr <= 3000) {

                    va = 15.00;

                }else if(salBr <= 5000) {

                    va = 25.00;

                }else {

                    va = 35.00;

                }

            }else {

                va = 0;

            }


            //Cálculo VR
            if(sim3.isChecked() ) {

                if(salBr <= 3000) {

                    vr = 57.2;

                }else if(salBr <= 5000) {

                    vr = 80.3;

                }else {

                    vr = 143.00;

                }


            }else {

                vr = 0;

            }

            //Base de cálculo do IRRF

            base_de_calc = salBr - inss - (189.59 * dependente);

            //Cálculo do IRRF
            if(base_de_calc <= 1903.98){

                irrf = 0;

            } else if(base_de_calc <= 2826.65) {

                irrf = base_de_calc * 7.5 / 100 - 142.80;

            }else if(base_de_calc <= 3751.05) {

                irrf = base_de_calc * 15 / 100 - 354.80;

            }else if(base_de_calc <= 4664.68) {

                irrf = base_de_calc * 22.5 / 100 - 636.13;

            }else {

                irrf = base_de_calc * 27.5 / 100 - 869.36;

            }

            //Cálculo do salário líquido

            salLiq = salBr - inss - vt - vr - va - irrf - ps;

            //Cálculo da porcentagem de desconto

            porc = salLiq * 100 / salBr;
            porc = 100 - porc;


            result.setText(getString(R.string.resultado,salLiq));
            porcentagem.setText(getString(R.string.porcent,porc));



        });

    }



}