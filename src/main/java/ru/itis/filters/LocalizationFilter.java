package ru.itis.filters;


import lombok.SneakyThrows;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebFilter("*")
public class LocalizationFilter implements javax.servlet.Filter {
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String lang = request.getParameter("lang");

        if (lang != null) {
            if (lang.equals("En") || lang.equals("Ru")) {
                Cookie cookie = new Cookie("locale", lang);
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
            } else {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("locale")) {
                        lang = cookie.getValue();
                    }
                }
            }
        } else {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("locale")) {
                    lang = cookie.getValue();
                }
            }
            if (lang == null) {
                lang = "En";
            }
        }

        Map<String, String> locale = (Map<String, String>) request.getServletContext().getAttribute("locale" + lang);
        request.setAttribute("locale", locale);

        filterChain.doFilter(request, response);
    }
}
