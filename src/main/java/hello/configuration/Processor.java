package hello.configuration;

import hello.dto.Individu;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@AllArgsConstructor
public class Processor implements ItemProcessor<Individu, Individu> {

    @Override
    public Individu process(Individu individu){
        return new Individu();
    }


}
