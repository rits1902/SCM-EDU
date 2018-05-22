package anhanguera.scm_edu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import anhanguera.scm_edu.Database.Uteis;
import anhanguera.scm_edu.Database.Database;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    EditText acesso_email;
    EditText acesso_senha;

    public void TelaAcessoLoginMenu(View view){

        Database database;

        database =  new Database(this);

        EditText acesso_email  = (EditText) findViewById(R.id.acesso_email);
        EditText acesso_senha  = (EditText) findViewById(R.id.acesso_senha);

        if (acesso_email.getText().toString().length() <= 0) {
            acesso_email.setError("Preencha o campo EMAIL!");
            acesso_email.requestFocus();

        }else if(acesso_senha.getText().toString().length() <= 0) {
            acesso_senha.setError("Preencha o campo Senha!");
            acesso_senha.requestFocus();

        }else {

            Cursor cursor = database.GetConexaoDataBase().rawQuery("SELECT count(*) FROM tb_cadastro_usuario WHERE email = '"+acesso_email.getText().toString()+ "' AND senha = '"+acesso_senha.getText().toString()+"'", null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String registro = cursor.getString(0);

                    if ( Integer.parseInt(registro) > 0){
                        Uteis.Alert(this,this.getString(R.string.usuario_autenticado_sucesso));
                        Intent TelaAcessoLoginMenu = new Intent(getApplicationContext(),  TelaAcessoActivity.class);
                        startActivity(TelaAcessoLoginMenu);

                    }else {

                        Uteis.Alert(this,this.getString(R.string.autenticacao_falha));

                    }
                    cursor.moveToNext();
                }
            }
        }
    }


    //ACESSANDO TELA DE CADASTRO
    public void TelaCadastroUsuario(View view){
        Intent cadastrarUsuario = new Intent(getApplicationContext(),  CadastroUsuarioActivity.class);
        startActivity(cadastrarUsuario);
    }

}

