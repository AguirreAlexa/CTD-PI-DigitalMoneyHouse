package dh.pi.userservice.security.filters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenParamFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getParameter("token") != null && !request.getParameter("token").isBlank()) {
            MutableHttpServletRequest requestWithHeaders = new MutableHttpServletRequest(request);
            String authorization = "Bearer "+ request.getParameter("token");
            requestWithHeaders.putHeader("Authorization", authorization);
            filterChain.doFilter(requestWithHeaders, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
