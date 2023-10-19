package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import util.StatusEnum;
import model.Audit;
import model.Course;
import model.User;
import service.AuditService;
import service.AuditServiceImpl;
import service.CourseService;
import service.CourseServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ModifyAuditServlet")
public class ModifyAuditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifyAuditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        AuditService auditService = new AuditServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        boolean result = false;
        String level = request.getParameter("level");
        String op = request.getParameter("op");//通过拒绝
        String aid = request.getParameter("aid");
        Audit audit = auditService.getById(Integer.parseInt(aid));
        Course course = courseService.getById(audit.getCid()+"");
        User user = (User) request.getSession().getAttribute("user");
        if("one".equals(level)){
            //默认到下一级审批
            audit.setStatus(StatusEnum.AUDIT_TWO.getStatus());
            if(course.getAudittwo() == -1){
                //如果不需要二次，默认就是完成
                audit.setStatus(StatusEnum.SUCCESS.getStatus());
            }
            //表示驳回
            if("0".equals(op)){
                String reason = request.getParameter("rejectedreason");
                audit.setStatus(StatusEnum.REJECTED.getStatus());
                audit.setRejectedreason(reason);
            }
            audit.setFinaloneid(user.getUserid());
        }else if("two".equals(level)){
            audit.setStatus(StatusEnum.SUCCESS.getStatus());
            //表示驳回
            if("0".equals(op)){
                String reason = request.getParameter("rejectedreason");
                audit.setStatus(StatusEnum.REJECTED.getStatus());
                audit.setRejectedreason(reason);
            }
            audit.setFinaltwoid(user.getUserid());
        }else if("self".equals(level)){//op是否为0
            audit.setStatus(StatusEnum.FINISH.getStatus());
        }else if("modifyAudit".equals(level)){
            audit.setProve(request.getParameter("prove"));
            audit.setReason(request.getParameter("reason"));
        }
        result = auditService.modifyAudit(audit);
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
