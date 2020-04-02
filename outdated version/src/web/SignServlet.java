package web;

import org.apache.log4j.Logger;
import domain.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignServlet extends HttpServlet {
    private Account account;
    private static final long serialVersionUID = 1L;
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String msg = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        AccountService service = new AccountService();
        account = service.getAccountByUsernameAndPassword(username, password);
       if(account == null ||!code.equalsIgnoreCase((String) session.getAttribute("randCheckCode")))

        {
            msg = "用户名、密码或验证码不正确";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher(SIGNON).forward(request, response);
        } else {
           Logger logger = Logger.getLogger(SignServlet.class);
           logger.info(account.getUsername() + "用户登录");
            session.setAttribute("account", account);
            request.getRequestDispatcher(MAIN).forward(request,response);
        }
    }
}
