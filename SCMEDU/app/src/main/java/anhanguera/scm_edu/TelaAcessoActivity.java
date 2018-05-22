package anhanguera.scm_edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TelaAcessoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_acesso);
    }

    public void TelaCriarProjeto (View view){
        Intent TelaCriarProjeto = new Intent(getApplicationContext(),  ProjetoActivity.class);
        startActivity(TelaCriarProjeto);
    }

    public void TelaCriarGrupo (View view){
        Intent TelaCriarGrupo = new Intent(getApplicationContext(),  GrupoAclActivity.class);
        startActivity(TelaCriarGrupo);
    }

    public void VoltarMenuPrincipal (View view){
        Intent VoltarMenuPrincipal = new Intent(getApplicationContext(),  MainActivity.class);
        startActivity(VoltarMenuPrincipal);
    }

}
