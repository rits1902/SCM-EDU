package anhanguera.scm_edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import anhanguera.scm_edu.Database.Uteis;
import anhanguera.scm_edu.model.GrupoModel;
import anhanguera.scm_edu.repository.GrupoRepository;

public class GrupoAclActivity extends AppCompatActivity {

    EditText         nome_grupo;
    EditText         membro_grupo;
    EditText         permissao_grupo;
    Button           VoltarCadastroGrupo;
    Button           ConfirmarCadastroGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_acl);

        this.CriarComponentes();

        this.CriarEventos();

    }

    //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected  void CriarComponentes(){

        nome_grupo              = (EditText) this.findViewById(R.id.nome_grupo);
        membro_grupo            = (EditText) this.findViewById(R.id.membro_grupo);
        permissao_grupo         = (EditText) this.findViewById(R.id.permissao_grupo);
        VoltarCadastroGrupo     = (Button) this.findViewById(R.id.VoltarCadastroGrupo);
        ConfirmarCadastroGrupo  = (Button) this.findViewById(R.id.ConfirmarCadastroGrupo);

    }

    protected  void CriarEventos(){


        ConfirmarCadastroGrupo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Salvar_onClick();
            }
        });

        VoltarCadastroGrupo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intentVoltarGrupo = new Intent(getApplicationContext(), TelaAcessoActivity.class);
                startActivity(intentVoltarGrupo);
                finish();
            }
        });
    }

    protected void Salvar_onClick(){

        if(nome_grupo.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.nome_grupo_obrigatorio));
            nome_grupo.requestFocus();
        }
        else if(membro_grupo.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.membro_grupo_obrigatorio));
            membro_grupo.requestFocus();
        }
        else if(permissao_grupo.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.permissao_grupo_obrigatorio));
            permissao_grupo.requestFocus();

        }
        else{

            GrupoModel grupoModel = new GrupoModel();

            grupoModel.setNomeGrupo(nome_grupo.getText().toString().trim());
            grupoModel.setMembroGrupo(membro_grupo.getText().toString().trim());
            grupoModel.setPermissaoMembro(permissao_grupo.getText().toString().trim());


            /*SALVANDO UM NOVO REGISTRO*/
            new GrupoRepository(this).Salvar(grupoModel);

            /*MENSAGEM DE SUCESSO!*/

            Uteis.Alert(this,this.getString(R.string.registro_salvo_sucesso));
            Intent VoltarTelaAcessoLogin = new Intent(getApplicationContext(),  TelaAcessoActivity.class);
            startActivity(VoltarTelaAcessoLogin);

            LimparCampos();
        }

    }

    protected void LimparCampos(){

        nome_grupo.setText(null);
        membro_grupo.setText(null);
        permissao_grupo.setText(null);
    }

    public void TelaVoltarMenuPrincipal (View view){
        Intent TelaVoltarMenuPrincipal = new Intent(getApplicationContext(),  TelaAcessoActivity.class);
        startActivity(TelaVoltarMenuPrincipal);
    }
}
