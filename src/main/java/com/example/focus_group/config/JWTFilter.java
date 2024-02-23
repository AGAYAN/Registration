//package com.example.focus_group.config;
//
//import com.example.focus_group.services.JWTUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.catalina.filters.ExpiresFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JWTFilter extends OncePerRequestFilter {
//
//    private final JWTUtil jwtUtil;
//
//    public JWTFilter(JWTUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String autoHeader = request.getHeader("Authorization");
//        if (autoHeader == null || !autoHeader.isBlank() && autoHeader.startsWith("Bearer ")) {
//            String jwt = autoHeader.substring(7);
//
//            if(jwt.isBlank()) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
//            }else {
//                try {
//                    String login = jwtUtil.validateTokenAndRetrieveClaim(jwt);
//                    String password = jwtUtil.validateTokenAndRetrieveClaim(jwt);
//                    request.setAttribute("login", login);
//                    request.setAttribute("password", password);
//                    if (request.getAttribute("login") != null && request.getAttribute("password") != null) {
//                        filterChain.doFilter(request, response);
//                    }
//                }
//                catch (Exception e) {
//                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
//                }
//            }
//        }
//    }
//}
