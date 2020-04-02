package web;

import domain.Account;
import domain.Cart;
import domain.CartItem;
import service.CatalogService;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class CartServlet extends HttpServlet {
    private static final String CART = "/WEB-INF/jsp/cart/Cart.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if(account != null) {
            CartService service = new CartService();
            Cart cart = (Cart) service.getCartByUsername(account.getUsername());
            session.setAttribute("cart",cart);
        }

        request.getRequestDispatcher(CART).forward(request, response);
    }
}
