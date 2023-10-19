package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import util.StatusEnum;
import model.Audit;
import model.User;
import service.AuditService;
import service.AuditServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AddAuditServlet")
public class AddAuditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddAuditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        AuditService auditService = new AuditServiceImpl();
        String cid = request.getParameter("cid");
        String reason = request.getParameter("reason");
        String prove = request.getParameter("prove");
        String status = StatusEnum.AUDIT_ONE.getStatus();
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cid", cid);
        map.put("uid", user.getUserid());
        List<Audit> audits = auditService.selectAudits(map);
        boolean create = true;
        if (audits.size() > 0) {
            create = false;
            for (Audit audit : audits) {
                if (audit.getStatus().equals(StatusEnum.REJECTED.getStatus())) {
                    auditService.removeAudit(audit.getAid());
                    create = true;
                    break;
                }
            }
        }
        if(!create){
            String jsonStr = JSON.toJSONString("exists");
            out.print(jsonStr);
        }else {
            Audit audit = new Audit(Integer.parseInt(cid), user.getUserid(), reason, prove, status);
            boolean result = auditService.insertAudit(audit);
            // 使用阿里的插件，转为JSON字符串
            String jsonStr = JSON.toJSONString(result);
            out.print(jsonStr);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
