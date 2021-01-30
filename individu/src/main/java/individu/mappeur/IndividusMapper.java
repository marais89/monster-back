package individu.mappeur;

import individu.dto.IndividuDto;
import individu.entity.Individu;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Mapper(componentModel = "spring")
public interface IndividusMapper {


    @Mapping(source = "user_image", target = "user_image", qualifiedByName = "decompress")
    IndividuDto mapToDto(Individu individu);

    @Mapping(source = "user_image", target = "user_image", qualifiedByName = "compress")
    Individu mapToEntity(IndividuDto individuDto);

    @Named("compress")
    public static byte[] compress(byte[] content) {
        if(content == null){
            return  null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Named("decompress")
    public static byte[] decompress(byte[] contentBytes) {
        if(contentBytes == null){
            return  null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(contentBytes)), out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }
}
