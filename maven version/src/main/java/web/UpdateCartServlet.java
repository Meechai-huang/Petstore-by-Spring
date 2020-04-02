package web;

import domain.Account;
import domain.Cart;
import domain.CartItem;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartServlet extends HttpServlet {
    private static final String CART = "/WEB-INF/jsp/cart/Cart.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        CartService service = new CartService();

        Cart cart = (Cart) session.getAttribute("cart");

        //跟新购物车网页以及数据库
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                if(request.getParameter(itemId) != null) {
                    int quantity = Integer.parseInt(request.getParameter(itemId));
                    cart.setQuantityByItemId(itemId, quantity);
                    service.updateCart(cartItem, account);
                    if (quantity < 1) {
                        cartItems.remove();
                        service.removeCartItem(cartItem, account);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("cart", cart);
        request.getRequestDispatcher(CART).forward(request, response);
    }
}
