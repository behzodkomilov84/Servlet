package servlet;

import entity.Product;
import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;
import service.UserService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@WebServlet(urlPatterns = "/secure/products")
public class ProductsServlet extends HttpServlet {

    private ProductService productService;
    public static Set<String > onlineUsersSet = new CopyOnWriteArraySet<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productService = (ProductService) config.getServletContext().getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Product> userProducts = productService.findAllByUserId(user.getId());
        req.setAttribute("products", userProducts);
        req.setAttribute("userLogins", onlineUsersSet);
        req.getRequestDispatcher("/secure/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("product-name");
        String imageUrl = req.getParameter("image-url");
        User user = (User) req.getSession().getAttribute("user");

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name(productName)
                .imageUrl(imageUrl)
                .userId(user.getId())
                .build();

        productService.save(product);

        resp.sendRedirect("/secure/products");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product-id");
        productService.removeById(UUID.fromString(productId));

        resp.sendRedirect("/secure/products");
    }
}
