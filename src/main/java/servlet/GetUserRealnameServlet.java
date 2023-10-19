package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import model.User;

@WebServlet("/GetUserRealnameServlet")
public class GetUserRealnameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetUserRealnameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      response.setContentType("text/html;charset=utf-8");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        User user = (User)request.getSession().getAttribute("user");

	        String  jsonStr = JSON.toJSONString(user);
	        out.print(jsonStr);
	        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
