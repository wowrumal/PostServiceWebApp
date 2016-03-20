package by.bsuir.spp.filter;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.UserType;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;

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

        if (!allowedRequest) {
            allowedRequest = true;
            HttpSession session = request.getSession();
            if (session != null) {
                User user = (User)(request.getSession().getAttribute(RequestParameterName.USER));
                if (user != null) {
                    UserType type = user.getUserRole();

                    switch (type) {
                        case ADMIN: {
                            break;
                        }
                        case CLIENT: {
                            allowedRequest = userUrls.contains(url);
                            break;
                        }
                        case POST_MANAGER: {
                            break;
                        }
                    }
                    if (!allowedRequest) {
                        response.sendRedirect(JspPageName.LOGIN_PAGE);
                    }
                }
                else {
                    response.sendRedirect(JspPageName.LOGIN_PAGE);
                }
            }
            else {
                response.sendRedirect(JspPageName.LOGIN_PAGE);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
