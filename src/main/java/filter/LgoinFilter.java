package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

public class LgoinFilter implements Filter {

    public LgoinFilter() {
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() +  "/login.html");
        }else if(user.getStatus().equals("0")){
            session.setAttribute("user", null);
            session.removeAttribute("user");
            Cookie cookie = new Cookie("userpwd", null);
            cookie.setMaxAge(-1);
            httpServletResponse.addCookie(cookie);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() +  "/login.html");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
