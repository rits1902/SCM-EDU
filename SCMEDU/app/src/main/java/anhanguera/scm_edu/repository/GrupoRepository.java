package anhanguera.scm_edu.repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import anhanguera.scm_edu.Database.Database;
import anhanguera.scm_edu.model.GrupoModel;

public class GrupoRepository {

    Database database;

    public GrupoRepository(Context context){

        database =  new Database(context);

    }

    public void Salvar(GrupoModel GrupoModel){

        ContentValues contentValues =  new ContentValues();
        contentValues.put("nome_grupo",          GrupoModel.getNomeGrupo());
        contentValues.put("membro_grupo",        GrupoModel.getMembroGrupo());
        contentValues.put("permissao_membro",    GrupoModel.getPermissaoMembro());
        contentValues.put("fl_ativo",            GrupoModel.getRegistroAtivo());

        database.GetConexaoDataBase().insert("tb_cadastro_grupo",null,contentValues);

        database.close();

    }

    public void Atualizar(GrupoModel GrupoModel){

        ContentValues contentValues =  new ContentValues();

        contentValues.put("nome_grupo",          GrupoModel.getNomeGrupo());
        contentValues.put("membro_grupo",        GrupoModel.getMembroGrupo());
        contentValues.put("permissao_membro",    GrupoModel.getPermissaoMembro());
        contentValues.put("fl_ativo",            GrupoModel.getRegistroAtivo());

        database.GetConexaoDataBase().update("tb_cadastro_grupo", contentValues, "id_grupo = ?", new String[]{Integer.toString(GrupoModel.getCodigo())});

        database.close();

    }

    public Integer Excluir(int codigo){

        return database.GetConexaoDataBase().delete("tb_cadastro_grupo","id_grupo = ?", new String[]{Integer.toString(codigo)});
    }

    public GrupoModel getCodigo(int codigo){


        Cursor cursor =  database.GetConexaoDataBase().rawQuery("SELECT * FROM tb_cadastro_grupo WHERE id_grupo= "+ codigo,null);

        cursor.moveToFirst();

        GrupoModel GrupoModel =  new GrupoModel();

        GrupoModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_grupo")));
        GrupoModel.setNomeGrupo(cursor.getString(cursor.getColumnIndex("nome_grupo")));
        GrupoModel.setMembroGrupo(cursor.getString(cursor.getColumnIndex("membro_grupo")));
        GrupoModel.setPermissaoMembro(cursor.getString(cursor.getColumnIndex("permissao_membro")));
        GrupoModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));

        database.close();
        return GrupoModel;

    }

    public List<GrupoModel> SelecionarTodos(){

        List<GrupoModel> projeto = new ArrayList<GrupoModel>();


        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_grupo,         ");
        stringBuilderQuery.append("        nome_grupo,       ");
        stringBuilderQuery.append("        membro_grupo,     ");
        stringBuilderQuery.append("        permissao_membro, ");
        stringBuilderQuery.append("        fl_ativo          ");
        stringBuilderQuery.append("  FROM  tb_cadastro_grupo ");
        stringBuilderQuery.append(" ORDER BY nome_grupo    ");


        Cursor cursor = database.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        cursor.moveToFirst();

        GrupoModel GrupoModel;

        while (!cursor.isAfterLast()){

            GrupoModel =  new GrupoModel();

            GrupoModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_grupo")));
            GrupoModel.setNomeGrupo(cursor.getString(cursor.getColumnIndex("nome_grupo")));
            GrupoModel.setMembroGrupo(cursor.getString(cursor.getColumnIndex("membro_grupo")));
            GrupoModel.setPermissaoMembro(cursor.getString(cursor.getColumnIndex("permissao_membro")));
            GrupoModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));

            projeto.add(GrupoModel);

            cursor.moveToNext();
        }
        database.close();
        return projeto;

    }
}