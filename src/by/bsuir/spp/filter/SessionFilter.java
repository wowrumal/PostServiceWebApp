package by.bsuir.spp.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public class SessionFilter implements Filter {

    private List<String> urls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
