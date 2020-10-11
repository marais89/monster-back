package hello.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String username;

    public String authority;
}
