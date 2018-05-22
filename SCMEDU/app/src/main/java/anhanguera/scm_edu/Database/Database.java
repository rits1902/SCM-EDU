package anhanguera.scm_edu.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {

    //NOME DA BASE DE DADOS
    private static final String NOME_BASE_DE_DADOS   = "BANCO.db";

    //VERSÃO DO BANCO DE DADOS
    private static final int    VERSAO_BASE_DE_DADOS = 1;

    //CONSTRUTOR
    public Database(Context context){

        super(context,NOME_BASE_DE_DADOS,null,VERSAO_BASE_DE_DADOS);
    }

    /*NA INICIALIZAÇÃO DA CLASSE VAMOS CRIAR A TABELA QUE VAMOS USAR*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder stringBuilderCreateTable = new StringBuilder();
        StringBuilder stringBuilderCreateTableProjeto = new StringBuilder();
        StringBuilder stringBuilderCreateTableGrupo = new StringBuilder();

        stringBuilderCreateTable.append(" CREATE TABLE tb_cadastro_usuario (");
        stringBuilderCreateTable.append("        id_cadastro      INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTable.append("        usuario          TEXT    NOT NULL,            ");
        stringBuilderCreateTable.append("        nome             TEXT    NOT NULL,            ");
        stringBuilderCreateTable.append("        email            TEXT    NOT NULL,            ");
        stringBuilderCreateTable.append("        senha            TEXT    NOT NULL,            ");
        stringBuilderCreateTable.append("        fl_ativo       INT     NOT NULL )             ");

        stringBuilderCreateTableProjeto.append(" CREATE TABLE tb_cadastro_projeto (");
        stringBuilderCreateTableProjeto.append("        id_projeto            INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTableProjeto.append("        nome_projeto          TEXT    NOT NULL,                  ");
        stringBuilderCreateTableProjeto.append("        data_projeto          TEXT    NOT NULL,                  ");
        stringBuilderCreateTableProjeto.append("        fl_ativo              INT     NOT NULL )                 ");

        stringBuilderCreateTableGrupo.append(" CREATE TABLE tb_cadastro_grupo (");
        stringBuilderCreateTableGrupo.append("        id_grupo              INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTableGrupo.append("        nome_grupo            TEXT    NOT NULL,                  ");
        stringBuilderCreateTableGrupo.append("        membro_grupo          TEXT    NOT NULL,                  ");
        stringBuilderCreateTableGrupo.append("        permissao_membro      TEXT    NOT NULL,                  ");
        stringBuilderCreateTableGrupo.append("        fl_ativo              INT     NOT NULL )                 ");

        db.execSQL(stringBuilderCreateTable.toString());
        db.execSQL(stringBuilderCreateTableProjeto.toString());
        db.execSQL(stringBuilderCreateTableGrupo.toString());

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tb_cadastro_usuario");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_cadastro_projeto");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_cadastro_grupo");
        onCreate(db);

    }

    /*MÉTODO QUE VAMOS USAR NA CLASSE QUE VAIEXECUTAR AS ROTINAS NO
    BANCO DE DADOS*/
    public SQLiteDatabase GetConexaoDataBase(){

        return this.getWritableDatabase();
    }

}