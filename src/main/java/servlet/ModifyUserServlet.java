package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.MD5Util;

@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifyUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        UserService userService = new UserServiceImpl();
        boolean result = false;

        String status = request.getParameter("status");
        if (status.equals("banuser")) {
            String userid = request.getParameter("userid");
            result = userService.banUser(userid);
        }else if (status.equals("modifyuser")) {
            String userid = request.getParameter("userid");
            String username = request.getParameter("username");
            String newuserpwd = request.getParameter("newuserpwd");
            String userpwd = request.getParameter("userpwd");
            userpwd = MD5Util.getEncryptedPwd(newuserpwd);
            String role = request.getParameter("role");
            String realname = request.getParameter("realname");
            String remark = request.getParameter("remark");
            User user = new User(Integer.parseInt(userid), role, username, userpwd, realname, null, remark);
            result = userService.modifyUser(user);

            // 如果修改的是登陆者本人，刷新session
            HttpSession session = request.getSession();
            User loginuser = (User) session.getAttribute("user");
            if(result &&loginuser.getUserid().equals(userid)) {
                loginuser.setUserrole(role);
                loginuser.setUsername(username);
                loginuser.setUserpwd(userpwd);
                loginuser.setRealname(realname);
                loginuser.setUserremark(remark);
                session.setAttribute("user", loginuser);
            }
        }

        // 使用阿里的插件，转为JSON字符串
        String jsonStr = JSON.toJSONString(result);
        out.print(jsonStr);
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
