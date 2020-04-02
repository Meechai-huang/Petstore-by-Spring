package web;

import domain.Account;
//import org.apache.log4j.Logger;
import domain.Cart;
import domain.Item;
import service.CartService;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private static final String CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private String workingItemId;
    private Cart cart;
    private CartService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        CartService service = new CartService();
        cart = (Cart)session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
        }

        if(cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
            service.updateCart(cart.getCartItemById(workingItemId), (Account) session.getAttribute("account"));
        } else {
            service = new CartService();
            boolean inInStock = service.isItemInStock(workingItemId);
            Item item = service.getItem(workingItemId);
            cart.addItem(item, inInStock);
            service.insertCartItem(cart.getCartItemById(workingItemId), (Account) session.getAttribute("account"));

        }
//        Account account = (Account) session.getAttribute("account");
//        Logger logger = Logger.getLogger(RemoveItemFromCartServlet.class);
//        logger.info(account.getUsername() + "添加物品" + workingItemId);

        session.setAttribute("cart", cart);
        request.getRequestDispatcher(CART).forward(request, response);

    }
}
