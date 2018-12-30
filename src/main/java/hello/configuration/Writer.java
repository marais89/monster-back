package hello.configuration;

import hello.dto.Individu;
import hello.service.IndividusService;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class Writer<T> implements ItemWriter<T> {

    private final IndividusService individusService;

    @Override
    public void write(List<? extends T> individus) throws Exception {
        for (T item : individus)
        {
            individusService.save((Individu) item);
        }
    }
}
