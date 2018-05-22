package anhanguera.scm_edu.repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import anhanguera.scm_edu.Database.Database;
import anhanguera.scm_edu.model.CadastroModel;

public class CadastroRepository {

    Database database;

    public CadastroRepository(Context context){

        database =  new Database(context);

    }

    public void Salvar(CadastroModel cadastroModel){

        ContentValues contentValues =  new ContentValues();
        contentValues.put("usuario",       cadastroModel.getUsuario());
        contentValues.put("nome",          cadastroModel.getNome());
        contentValues.put("email",         cadastroModel.getEmail());
        contentValues.put("senha",         cadastroModel.getSenha());
        contentValues.put("fl_ativo",      cadastroModel.getRegistroAtivo());


        database.GetConexaoDataBase().insert("tb_cadastro_usuario",null,contentValues);

        database.close();

    }

    public void Atualizar(CadastroModel cadastroModel){

        ContentValues contentValues =  new ContentValues();

        contentValues.put("usuario",       cadastroModel.getUsuario());
        contentValues.put("nome",          cadastroModel.getNome());
        contentValues.put("email",         cadastroModel.getEmail());
        contentValues.put("senha",         cadastroModel.getSenha());
        contentValues.put("fl_ativo",      cadastroModel.getRegistroAtivo());


        database.GetConexaoDataBase().update("tb_cadastro_usuario", contentValues, "id_cadastro = ?", new String[]{Integer.toString(cadastroModel.getCodigo())});

        database.close();

    }

    public Integer Excluir(int codigo){

        return database.GetConexaoDataBase().delete("tb_cadastro_usuario","id_cadastro = ?", new String[]{Integer.toString(codigo)});
    }

    public CadastroModel getCodigo(int codigo){


        Cursor cursor =  database.GetConexaoDataBase().rawQuery("SELECT * FROM tb_cadastro_usuario WHERE id_cadastro= "+ codigo,null);

        cursor.moveToFirst();

        CadastroModel cadastroModel =  new CadastroModel();

        cadastroModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_cadastro")));
        cadastroModel.setUsuario(cursor.getString(cursor.getColumnIndex("usuario")));
        cadastroModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        cadastroModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        cadastroModel.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        cadastroModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));


        database.close();
        return cadastroModel;


    }

    public List<CadastroModel> SelecionarTodos(){

        List<CadastroModel> usuario = new ArrayList<CadastroModel>();


        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_cadastro,    ");
        stringBuilderQuery.append("        usuario,        ");
        stringBuilderQuery.append("        nome,           ");
        stringBuilderQuery.append("        email,          ");
        stringBuilderQuery.append("        senha,          ");
        stringBuilderQuery.append("        fl_ativo        ");
        stringBuilderQuery.append("  FROM  tb_cadastro_usuario       ");
        stringBuilderQuery.append(" ORDER BY usuario       ");


        Cursor cursor = database.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        cursor.moveToFirst();

        CadastroModel cadastroModel;

        while (!cursor.isAfterLast()){

            cadastroModel =  new CadastroModel();

            cadastroModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_cadastro")));
            cadastroModel.setUsuario(cursor.getString(cursor.getColumnIndex("usuario")));
            cadastroModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cadastroModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            cadastroModel.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            cadastroModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));

            usuario.add(cadastroModel);

            cursor.moveToNext();
        }
        database.close();
        return usuario;

    }
}