package anhanguera.scm_edu.model;

public class ProjetoModel {

    private Integer codigo;
    private String  nome_projeto;
    private String  data_projeto;
    private byte    registroAtivo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeProjeto() {
        return nome_projeto;
    }

    public void setNomeProjeto(String nome_projeto) {
        this.nome_projeto = nome_projeto;
    }

    public String getDateProjeto() {
        return data_projeto;
    }

    public void setDateProjeto(String data_projeto) {
        this.data_projeto = data_projeto;
    }

    public byte getRegistroAtivo() {
        return registroAtivo;
    }

    public void setRegistroAtivo(byte registroAtivo) {
        this.registroAtivo = registroAtivo;
    }


}