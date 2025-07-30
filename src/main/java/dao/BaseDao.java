package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

@AllArgsConstructor
public abstract class BaseDao<T> {

    private final ObjectMapper objectMapper;
    private final File file;
    private TypeReference<List<T>> typeReference;

    @SneakyThrows
    public void save(T product) {
        List<T> list = findAll();
        list.add(product);
        objectMapper.writeValue(file, list);
    }

    @SneakyThrows
    public void saveAll(List<T> products) {objectMapper.writeValue(file, products);}

    @SneakyThrows
    public List<T> findAll() {
        return objectMapper.readValue(file, typeReference);
    }
}
