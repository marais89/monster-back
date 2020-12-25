package hello.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adress")
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Town gouvernorat;

    private String ville;

    private String cite;

    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public String getCite() {
        return cite;
    }

    public int getCode() {
        return code;
    }

    public Town getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(Town gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
