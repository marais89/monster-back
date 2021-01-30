package individu.dto;

public enum IndividuStatus {

    ACTIVE("active"),
    ATTENTE("attente"),
    BLOQUE("bloque"),
    RESILIE("resilie");

    public final String libelle;

    IndividuStatus(String libelle) {
        this.libelle = libelle;
    }

}
