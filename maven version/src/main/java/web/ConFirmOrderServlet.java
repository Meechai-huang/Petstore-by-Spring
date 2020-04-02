package web;

//import org.apache.log4j.Logger;
import domain.Account;
import domain.Cart;
import domain.CartItem;
import domain.Order;
import service.OrderService;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class ConFirmOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Order order = (Order) session.getAttribute("order");
        OrderService service = new OrderService();
        service.insertOrder(order);

        Cart cart = (Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        CartService cartService = new CartService();
        while(cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            cartService.removeCartItem(cartItem, account);
        }
//
//        if(account != null) {
//            Logger logger = Logger.getLogger(CategoryServlet.class);
//            logger.info(account.getUsername() + "生成订单" + order.getOrderId());
//        }
        request.getRequestDispatcher(VIEW_ORDER).forward(request, response);
    }
}
