package anhanguera.scm_edu.model;

public class GrupoModel {

    private Integer codigo;
    private String  nome_grupo;
    private String  membro_grupo;
    private String  permissao_membro;
    private byte    registroAtivo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeGrupo() {
        return nome_grupo;
    }

    public void setNomeGrupo(String nome_grupo) {
        this.nome_grupo = nome_grupo;
    }

    public String getMembroGrupo() {
        return membro_grupo;
    }

    public void setMembroGrupo(String membro_grupo) {
        this.membro_grupo = membro_grupo;
    }

    public String getPermissaoMembro() {
        return permissao_membro;
    }

    public void setPermissaoMembro(String permissao_membro) {
        this.permissao_membro = permissao_membro;
    }

    public byte getRegistroAtivo() {
        return registroAtivo;
    }

    public void setRegistroAtivo(byte registroAtivo) {
        this.registroAtivo = registroAtivo;
    }


}