package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import model.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        UserService userService = new UserServiceImpl();
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        if(null != username && !username.equals("")) {
            map.put("username", username);
        }
        String realname = request.getParameter("realname");
        if(null != realname && !realname.equals("")) {
            map.put("realname", realname);
        }
        String userrole = request.getParameter("userrole");
        if(null != userrole && !userrole.equals("")) {
            map.put("userrole", userrole);
        }

        List<User> userList = userService.selectUsers(map);

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("data", userList);

        // 使用阿里的插件，转为JSON字符串
        String jsonStr = JSON.toJSONString(obj);
        out.print(jsonStr);
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
