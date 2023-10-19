package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Audit;
import model.User;
import service.AuditService;
import service.AuditServiceImpl;
import util.StatusEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/FindAuditServlet")
public class FindAuditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindAuditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        AuditService auditService = new AuditServiceImpl();
        String role = request.getParameter("role");
        String cname = request.getParameter("cname");
        String status = request.getParameter("status");
        Integer pageNo = Integer.parseInt(request.getParameter("page"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        pageNo = (pageNo - 1)*pageSize;
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        if (null != cname && !"".equals(cname)){
            map.put("cname",cname);
        }
        if (null != status && !"".equals(status)){
            map.put("status",status);//所有状态
        }

        if("0".equals(role)){//学生,查自己不为结束状态的
            map.put("uid",user.getUserid());
            map.put("finish", StatusEnum.FINISH.getStatus());//结束状态
        }else if("-1".equals(role)){//学生,分页查询
            map.put("uid",user.getUserid());
        }else if("9".equals(role)){//管理员
        } else{
            String userrole = user.getUserrole();
            if("课程主讲教师".equals(userrole)){
                map.put("oneid",user.getUserid());//主讲教师
            }else {
                map.put("twoid",user.getUserid());//主管教师
            }
            String uname = request.getParameter("uname");
            if (null != uname && !"".equals(uname)){
                map.put("uname",uname);
            }
        }
        List<Audit> datas  = auditService.selectPage(map);
        for (Audit data : datas) {
            data.setUserorle(user.getUserrole());
        }
        int count = auditService.selectPageCount(map);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("data", datas);
        obj.put("count", count);

        // 使用阿里的插件，转为JSON字符串
        String jsonStr = JSON.toJSONString(obj);
        out.print(jsonStr);
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
