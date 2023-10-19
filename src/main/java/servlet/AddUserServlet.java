package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.MD5Util;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        userpwd = MD5Util.getEncryptedPwd(userpwd);
        String role = request.getParameter("role");
        String realname = request.getParameter("realname");
        String remark = request.getParameter("remark");
        User user = new User(role, username, userpwd, realname, remark);
        boolean result = userService.insertUser(user);

        // 使用阿里的插件，转为JSON字符串
        String jsonStr = JSON.toJSONString(result);
        out.print(jsonStr);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
