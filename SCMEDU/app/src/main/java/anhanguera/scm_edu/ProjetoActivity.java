package anhanguera.scm_edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import anhanguera.scm_edu.Database.Uteis;
import anhanguera.scm_edu.model.ProjetoModel;
import anhanguera.scm_edu.repository.ProjetoRepository;

public class ProjetoActivity extends AppCompatActivity {


    EditText         cadastroNomeProjeto;
    EditText         cadastroDataFimProjeto;
    Button           voltarTelaLogin2;
    Button           ConfirmarTelaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto);

        this.CriarComponentes();

        this.CriarEventos();

    }

    //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected  void CriarComponentes(){

        cadastroNomeProjeto    = (EditText) this.findViewById(R.id.cadastroNomeProjeto);
        cadastroDataFimProjeto = (EditText) this.findViewById(R.id.cadastroDataFimProjeto);
        voltarTelaLogin2       = (Button) this.findViewById(R.id.voltarTelaLogin2);
        ConfirmarTelaLogin     = (Button) this.findViewById(R.id.ConfirmarTelaLogin);

    }

    protected  void CriarEventos(){


        ConfirmarTelaLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Salvar_onClick();
            }
        });

        voltarTelaLogin2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intentCancelarProjeto = new Intent(getApplicationContext(), TelaAcessoActivity.class);
                startActivity(intentCancelarProjeto);
                finish();
            }
        });
    }


    protected void Salvar_onClick(){

        if(cadastroNomeProjeto.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.nome_projeto_obrigatorio));
            cadastroNomeProjeto.requestFocus();
        }
        else if(cadastroDataFimProjeto.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.data_projeto_obrigatorio));
            cadastroDataFimProjeto.requestFocus();

        }
        else{

            ProjetoModel projetoModel = new ProjetoModel();

            projetoModel.setNomeProjeto(cadastroNomeProjeto.getText().toString().trim());

            projetoModel.setDateProjeto(cadastroDataFimProjeto.getText().toString().trim());


            /*SALVANDO UM NOVO REGISTRO*/
            new ProjetoRepository(this).Salvar(projetoModel);

            /*MENSAGEM DE SUCESSO!*/
            Uteis.Alert(this,this.getString(R.string.registro_salvo_sucesso));
            Intent VoltarTelaAcessoLogin = new Intent(getApplicationContext(),  TelaAcessoActivity.class);
            startActivity(VoltarTelaAcessoLogin);
            LimparCampos();
        }

    }

    protected void LimparCampos(){

        cadastroNomeProjeto.setText(null);
        cadastroDataFimProjeto.setText(null);
    }

    public void VoltarTelaAcessoLogin(View view){
        Intent VoltarTelaAcessoLogin = new Intent(getApplicationContext(),  TelaAcessoActivity.class);
        startActivity(VoltarTelaAcessoLogin);
    }
}
