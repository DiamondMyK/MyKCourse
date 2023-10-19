package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Course;
import service.CourseService;
import service.CourseServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/FindCourseServlet")
public class FindCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindCourseServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        CourseService courseService = new CourseServiceImpl();

        Map<String, Object> map = new HashMap<String, Object>();

        String cname = request.getParameter("cname");
        if(!cname.equals("")) {
            map.put("cname", cname);
        }
        List<Course> courses = courseService.selectCourses(map);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("data", courses);

        // 使用阿里的插件，转为JSON字符串
        String jsonStr = JSON.toJSONString(obj);
        out.print(jsonStr);
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
