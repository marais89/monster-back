package hello.configuration;

import hello.dto.Individu;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class IndividuMapper implements FieldSetMapper<Individu> {

        public Individu mapFieldSet(FieldSet fs) {

            if(fs == null){
                return null;
            }
            String[] contact = fs.readString("contact").split("-");
            String numTel= contact[0];
            String email= contact[1];

            Individu player = new Individu();
            player.setNom(fs.readString("nom"));
            player.setPrenom(fs.readString("prenom"));
            player.setNumTel(numTel);
            player.setEmail(email);
            player.setAdresse(fs.readString("adresse"));

            return player;
        }
}