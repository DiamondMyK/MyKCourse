package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import service.AuditService;
import service.AuditServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RemoveAuditServlet")
public class RemoveAuditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveAuditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        AuditService auditService = new AuditServiceImpl();
        String aid = request.getParameter("aid");

        boolean result = auditService.removeAudit(Integer.parseInt(aid));

        // 使用阿里的插件，转为JSON字符串
        String jsonStr = JSON.toJSONString(result);
        out.print(jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
