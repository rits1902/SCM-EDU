package anhanguera.scm_edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import anhanguera.scm_edu.Database.Uteis;
import anhanguera.scm_edu.model.CadastroModel;
import anhanguera.scm_edu.repository.CadastroRepository;


public class CadastroUsuarioActivity extends AppCompatActivity {

    EditText         usuario_cadastro;
    EditText         nome_cadastro;
    EditText         email_cadastro;
    EditText         senha_cadastro;
    Button           cancelar_cadastro;
    Button           confirmar_cadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        this.CriarComponentes();

        this.CriarEventos();


    }

    //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected  void CriarComponentes(){

        usuario_cadastro       = (EditText) this.findViewById(R.id.usuario_cadastro);
        nome_cadastro          = (EditText) this.findViewById(R.id.nome_cadastro);
        email_cadastro         = (EditText) this.findViewById(R.id.email_cadastro);
        senha_cadastro         = (EditText) this.findViewById(R.id.senha_cadastro);
        cancelar_cadastro      = (Button) this.findViewById(R.id.cancelar_cadastro);
        confirmar_cadastro     = (Button) this.findViewById(R.id.confirmar_cadastro);

    }

    protected  void CriarEventos(){


        confirmar_cadastro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Salvar_onClick();
            }
        });

        cancelar_cadastro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }


    protected void Salvar_onClick(){

        if(usuario_cadastro.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.usuario_obrigatorio));
            usuario_cadastro.requestFocus();
        }
        else if(nome_cadastro.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.nome_obrigatorio));
            nome_cadastro.requestFocus();
        }
        else if(email_cadastro.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.email_obrigatorio));
            email_cadastro.requestFocus();

        }
        else if(senha_cadastro.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.senha_obrigatorio));
            senha_cadastro.requestFocus();

        }
        else{

            CadastroModel cadastroModel = new CadastroModel();

            cadastroModel.setUsuario(usuario_cadastro.getText().toString().trim());

            cadastroModel.setNome(nome_cadastro.getText().toString().trim());

            cadastroModel.setEmail(email_cadastro.getText().toString().trim());

            cadastroModel.setSenha(senha_cadastro.getText().toString().trim());


            /*SALVANDO UM NOVO REGISTRO*/
            new CadastroRepository(this).Salvar(cadastroModel);

            /*MENSAGEM DE SUCESSO!*/

            Uteis.Alert(this,this.getString(R.string.registro_salvo_sucesso));

            LimparCampos();
        }

    }

    protected void LimparCampos(){

        usuario_cadastro.setText(null);
        nome_cadastro.setText(null);
        email_cadastro.setText(null);
        senha_cadastro.setText(null);
    }

    public void ConfirmarCadastrarUsuario(View view){
        Intent ConfirmarCadastrarUsuario = new Intent(getApplicationContext(),  CadastroUsuarioActivity.class);
        startActivity(ConfirmarCadastrarUsuario);
    }

    public void CancelarCadastroUsuario(View view){
        Intent CancelarCadastrarUsuario = new Intent(getApplicationContext(),  MainActivity.class);
        startActivity(CancelarCadastrarUsuario);
    }

}
