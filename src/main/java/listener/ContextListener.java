package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProductDao;
import dao.UserDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.ProductService;
import service.UserService;

import java.io.File;
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ObjectMapper objectMapper = new ObjectMapper();
        File userFile = new File("D:\\JavaPrograms\\RegistrationApps\\src\\main\\resources\\users.json");
        File productsFile = new File("D:\\JavaPrograms\\RegistrationApps\\src\\main\\resources\\products.json");

        UserDao userDao = new UserDao(objectMapper,userFile);
        ProductDao productDao = new ProductDao(objectMapper,productsFile);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserService userService = new UserService(userDao, passwordEncoder);
        ProductService productService = new ProductService(productDao);


        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("productService", productService);

    }
}
