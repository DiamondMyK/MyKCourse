package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.EncodingUtil;
import util.MD5Util;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        UserService userService = new UserServiceImpl();
        String jsonStr = JSON.toJSONString(null);

        // 用户操作状态
        String status = EncodingUtil.changeEncoding(request.getParameter("status"));
        // 用户登陆
        if (status.equals("login")) {
            // 记录用户登陆状态  1：登陆成功；  0：登陆失败 ； -1：用户被禁用
            int resultStatus = 0;
            // 获得验证参数（用户名以及密码）
            String username = EncodingUtil.changeEncoding(request.getParameter("username"));
            String userpwd = EncodingUtil.changeEncoding(request.getParameter("userpwd"));
            userpwd = MD5Util.getEncryptedPwd(userpwd);

            // 开始验证（true：密码正确，false：密码错误）
            boolean result = userService.checkUser(username, userpwd);
            if (result) {
                resultStatus = 1;// 先设置为登陆成功，接下来验证该用户是否被禁用
            }
            // 获取当前用户的所有信息，判断是否被禁用
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", username);
            map.put("userpwd", userpwd);
            List<User> userList = userService.selectUsers(map);
            User user = null;// 用户记录登陆者的信息
            if (userList.size() != 0) {
                user = userList.get(0);
                // 判断用户状态是否为禁用
                if (user.getStatus().equals("0")) {
                    resultStatus = -1;// 设置该用户被禁用的返回值
                    result = false;// 登陆失败
                }
            }

            // 用户登陆成功
            if (result) {
                if (user != null) {
                    HttpSession session = request.getSession();// 获取Sesson
                    session.setAttribute("user", user);
                }
            }
            jsonStr = JSON.toJSONString(resultStatus);
        }
        // 用户退出
        if (status.equals("exit")) {
            HttpSession session = request.getSession();// 获取Sesson
            session.removeAttribute("user");
            jsonStr = JSON.toJSONString("true");
        }
        out.print(jsonStr);//返回给前端js的data
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
