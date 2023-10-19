package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Audit;
import service.AuditService;
import service.AuditServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ExportAuditServlet")
public class ExportAuditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExportAuditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        AuditService auditService = new AuditServiceImpl();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("export","export");
        List<Audit> datas = auditService.selectAudits(map);
        //datas.get(1).getCname();
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("data", datas);

        // 使用阿里的插件，转为JSON字符串
        String jsonStr = JSON.toJSONString(obj);
        out.print(jsonStr);
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
