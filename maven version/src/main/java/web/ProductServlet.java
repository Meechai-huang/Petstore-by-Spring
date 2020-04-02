package web;

//import org.apache.log4j.Logger;
import domain.Account;
import domain.Item;
import domain.Product;
import service.CatalogService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private String productId;
    private static final String CATEGORY = "/WEB-INF/jsp/catalog/Product.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("productId");
        CatalogService service = new CatalogService();
        Product product = service.getProduct(productId);
        List<Item> itemList = service.getItemListByProduct(productId);
        HttpSession session = request.getSession();
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);
        Account account = (Account) session.getAttribute("account");
//        if(account != null) {
//            Logger logger = Logger.getLogger(CategoryServlet.class);
//            logger.info(account.getUsername() + "浏览" + productId);
//        }
        request.getRequestDispatcher(CATEGORY).forward(request, response);
    }
}
