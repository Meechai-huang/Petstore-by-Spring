package web;

//import org.apache.log4j.Logger;
import domain.Account;
import domain.Category;
import domain.Product;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.catalog.Catalog;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    private static final String Category = "/WEB-INF/jsp/catalog/Category.jsp";
    private  String categoryId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     categoryId=request.getParameter("categoryId");
        CatalogService catalogService=new CatalogService();
        Category category= catalogService.getCategory(categoryId);
        List<Product> productList= catalogService.getProductListByCategory(categoryId);
        HttpSession session=request.getSession();
        session.setAttribute("category",category);
        session.setAttribute("productList",productList);

        Account account = (Account) session.getAttribute("account");
//        if(account != null) {
//            Logger logger = Logger.getLogger(CategoryServlet.class);
//            logger.info(account.getUsername() + "浏览" + categoryId);
//        }

         request.getRequestDispatcher(Category).forward(request,response);
    }
}
