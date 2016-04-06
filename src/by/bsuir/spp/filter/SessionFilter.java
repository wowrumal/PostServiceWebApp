package by.bsuir.spp.filter;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.UserType;
import by.bsuir.spp.controller.command.CommandName;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SessionFilter implements Filter {

    private List<String> exceptUrls;
    private List<String> userUrls;
    private List<String> adminUrls;
    private List<String> managerUrls;

    private UserDao userDao = MySqlUserDao.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        StringTokenizer tokenizer = new StringTokenizer(filterConfig.getInitParameter("avoid-urls"), ",");
        exceptUrls = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            exceptUrls.add(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(filterConfig.getInitParameter("user_pages"), ",");
        userUrls = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            userUrls.add(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(filterConfig.getInitParameter("admin_pages"), ",");
        adminUrls = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            adminUrls.add(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(filterConfig.getInitParameter("manager_pages"), ",");
        managerUrls = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            managerUrls.add(tokenizer.nextToken());
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String url = request.getServletPath();
        boolean allowedRequest = false;
        if (exceptUrls.contains(url)) {
            allowedRequest = true;
        }

        if (request.getParameter(RequestParameterName.COMMAND_NAME) != null) {
            if (request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase().equals(CommandName.LOGIN_COMMAND.toString()) ||
                    request.getParameter(RequestParameterName.COMMAND_NAME).equals(CommandName.REGISTRATION_COMMAND.toString())) {
                filterChain.doFilter(request, response);
                return;
            }
        }


        if (!allowedRequest) {
            allowedRequest = true;
            HttpSession session = request.getSession();
            if (session != null) {
                User user = (User)(request.getSession().getAttribute(RequestParameterName.USER));
                if (user != null) {
                    UserType type = user.getUserRole();

                    switch (type) {
                        case ADMIN: {
                            allowedRequest = adminUrls.contains(url);
                            break;
                        }
                        case CLIENT: {
                            allowedRequest = userUrls.contains(url);
                            break;
                        }
                        case POST_MANAGER: {
                            allowedRequest = managerUrls.contains(url);
                            break;
                        }
                    }

                    try {
                        if (userDao.read(user.getId()).getUserRole() != user.getUserRole()) {
                            allowedRequest = false;
                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                        allowedRequest = false;
                    }

                    if (!allowedRequest) {
                        request.getSession().invalidate();
                        response.sendRedirect(JspPageName.LOGIN_PAGE);
                        return;
                    }
                }
                else {
                    request.getSession().invalidate();
                    response.sendRedirect(JspPageName.LOGIN_PAGE);
                    return;
                }
            }
            else {
                request.getSession().invalidate();
                response.sendRedirect(JspPageName.LOGIN_PAGE);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
