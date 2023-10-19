package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import model.Course;
import service.CourseService;
import service.CourseServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ModifyCourseServlet")
public class ModifyCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifyCourseServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        CourseService courseService = new CourseServiceImpl();
        boolean result = false;
        String status = request.getParameter("status");
        if("modifycourse".equals(status)){
            String cid = request.getParameter("cid");
            String cname = request.getParameter("cname");
            String cremark = request.getParameter("cremark");

            Course course = new Course(cname, cremark);
            course.setCid(Integer.parseInt(cid));
            result = courseService.modifyCourse(course);
        }else if("configcourse".equals(status)){
            String cid = request.getParameter("cid");
            String auditone = request.getParameter("auditone");
            String audittwo = request.getParameter("audittwo");

            Course course = new Course(Integer.parseInt(cid), Integer.parseInt(auditone),Integer.parseInt(audittwo));
            result = courseService.configCourse(course);
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
