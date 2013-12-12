package pt.ist.sirs.services.dto;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class RegistoDTO {

    private String conteudo;
    private String nomePaciente;
    private String nomeMedico;
    private String nomeEspecialidade;
    private String nomeEstabelecimento;
    private Integer objectID;

    public RegistoDTO(String conteudo, String nomePaciente, String nomeMedico, String nomeEspecialidade,
            String nomeEstabelecimento, Integer objectID) {
        this.conteudo = conteudo;
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.nomeEspecialidade = nomeEspecialidade;
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.objectID = objectID;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }
}
