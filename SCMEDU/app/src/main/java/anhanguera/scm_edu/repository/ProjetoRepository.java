package anhanguera.scm_edu.repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import anhanguera.scm_edu.Database.Database;
import anhanguera.scm_edu.model.ProjetoModel;

public class ProjetoRepository {

    Database database;

    public ProjetoRepository(Context context){

        database =  new Database(context);

    }

    public void Salvar(ProjetoModel ProjetoModel){

        ContentValues contentValues =  new ContentValues();
        contentValues.put("nome_projeto",          ProjetoModel.getNomeProjeto());
        contentValues.put("data_projeto",          ProjetoModel.getDateProjeto());
        contentValues.put("fl_ativo",              ProjetoModel.getRegistroAtivo());

        database.GetConexaoDataBase().insert("tb_cadastro_projeto",null,contentValues);

        database.close();

    }

    public void Atualizar(ProjetoModel ProjetoModel){

        ContentValues contentValues =  new ContentValues();

        contentValues.put("nome_projeto",          ProjetoModel.getNomeProjeto());
        contentValues.put("data_projeto",          ProjetoModel.getDateProjeto());
        contentValues.put("fl_ativo",              ProjetoModel.getRegistroAtivo());

        database.GetConexaoDataBase().update("tb_cadastro_projeto", contentValues, "id_projeto = ?", new String[]{Integer.toString(ProjetoModel.getCodigo())});

        database.close();

    }

    public Integer Excluir(int codigo){

        return database.GetConexaoDataBase().delete("tb_cadastro_projeto","id_projeto = ?", new String[]{Integer.toString(codigo)});
    }

    public ProjetoModel getCodigo(int codigo){


        Cursor cursor =  database.GetConexaoDataBase().rawQuery("SELECT * FROM tb_cadastro_projeto WHERE id_projeto= "+ codigo,null);

        cursor.moveToFirst();

        ProjetoModel ProjetoModel =  new ProjetoModel();

        ProjetoModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_projeto")));
        ProjetoModel.setNomeProjeto(cursor.getString(cursor.getColumnIndex("nome_projeto")));
        ProjetoModel.setDateProjeto(cursor.getString(cursor.getColumnIndex("data_projeto")));
        ProjetoModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));

        database.close();
        return ProjetoModel;

    }

    public List<ProjetoModel> SelecionarTodos(){

        List<ProjetoModel> projeto = new ArrayList<ProjetoModel>();


        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_projeto,         ");
        stringBuilderQuery.append("        nome_projeto,       ");
        stringBuilderQuery.append("        data_projeto,       ");
        stringBuilderQuery.append("        fl_ativo        ");
        stringBuilderQuery.append("  FROM  tb_cadastro_projeto ");
        stringBuilderQuery.append(" ORDER BY nome_projeto      ");


        Cursor cursor = database.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        cursor.moveToFirst();

        ProjetoModel ProjetoModel;

        while (!cursor.isAfterLast()){

            ProjetoModel =  new ProjetoModel();

            ProjetoModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_projeto")));
            ProjetoModel.setNomeProjeto(cursor.getString(cursor.getColumnIndex("nome_projeto")));
            ProjetoModel.setDateProjeto(cursor.getString(cursor.getColumnIndex("data_projeto")));
            ProjetoModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));

            projeto.add(ProjetoModel);

            cursor.moveToNext();
        }
        database.close();
        return projeto;

    }
}