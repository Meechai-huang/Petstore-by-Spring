package web;

//import org.apache.log4j.Logger;
import domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOffServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("cart");
//
//        Logger logger = Logger.getLogger(SignOffServlet.class);
//        logger.info(((Account)session.getAttribute("account")).getUsername() + "用户下线");

        session.removeAttribute("account");
        request.getRequestDispatcher(MAIN).forward(request, response);
    }
}
