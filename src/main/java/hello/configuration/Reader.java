package hello.configuration;

import hello.dto.Individu;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class Reader extends FlatFileItemReader<Individu> {

    public Reader() {

        super();

        setResource(new ClassPathResource("individuDataX.csv"));

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] { "nom", "prenom", "adresse", "contact" });
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);

        IndividuMapper fieldSetMapper = new IndividuMapper();


        DefaultLineMapper<Individu> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(defaultLineMapper);
    }
}
