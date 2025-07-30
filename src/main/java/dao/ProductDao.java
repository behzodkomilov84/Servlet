package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Product;

import java.io.File;
import java.util.List;

public class ProductDao extends BaseDao<Product> {


    public ProductDao(ObjectMapper objectMapper, File file) {
        super(objectMapper, file, new TypeReference<List<Product>>() {});
    }
}
