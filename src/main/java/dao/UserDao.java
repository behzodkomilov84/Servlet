package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;

import java.io.File;
import java.util.List;

public class UserDao extends BaseDao<User> {

    public UserDao(ObjectMapper objectMapper, File file) {
        super(objectMapper, file, new TypeReference<List<User>>() {});
    }
}
