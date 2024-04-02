package br.com.ms.authandauto.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonUtils {
  public static String readFileAsString(String filePath) throws IOException {
    final File input = new ClassPathResource(filePath).getFile();
    return new String(Files.readAllBytes(Paths.get(input.getPath())));
  }

  public static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new ParameterNamesModule())
          .registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());

  public static <T> T getObjectFromFile(String path, Class<T> objectClass) throws IOException{
    File file = new ClassPathResource(path).getFile();
    return objectMapper.readValue(file, objectClass);
  }

  public static <T> List<T> getListOfObjectFromFile(String path, Class<T> objectClass) throws IOException{
    File file = new ClassPathResource(path).getFile();
    return  objectMapper.readValue(file,
            objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, objectClass));
  }

  public static <T> T getObjectFromJson(String value, Class<T> objectClass) throws IOException {
    return objectMapper.readValue(value, objectClass);
  }
}
