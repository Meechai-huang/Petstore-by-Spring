package web;

import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserNameIsExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String Username=request.getParameter("username");
          AccountService service = new AccountService();
          boolean result = service.usernameIsExist(Username);
          response.setContentType("text/plain");
        PrintWriter out=response.getWriter();
        if(result){
            out.print("Exist");
        }else
            out.print("Not Exist");
        out.flush();
        out.close();
    }
}
