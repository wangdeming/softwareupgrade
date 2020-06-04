package cn.ibdsr.web.core.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 跨域
 * @Version V1.0
 * @CreateDate 2019/5/20 8:42
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/5/20      Wujiayun            类说明
 */
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Accept,Content-Type,Cache-Control,X-Requested-With,X-Nideshop-Token,Authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.addHeader("Allow", "GET, POST, DELETE, PUT");
        chain.doFilter(request, response);
    }
}
