package pt.ist.sirs.services.dto;

public class EspecialidadeDTO {
    private Integer ObjectId;
    private String nome;

    public EspecialidadeDTO(Integer objectId, String nome) {
        this.ObjectId = objectId;
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public Integer getObjectId() {
        return this.ObjectId;
    }
}
