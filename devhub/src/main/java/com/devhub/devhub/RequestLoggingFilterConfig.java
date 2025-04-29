import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public Filter loggingFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                System.out.println("Запрос: " + request.getMethod() + " " + request.getRequestURI());
                filterChain.doFilter(request, response);
                System.out.println("Ответ статус: " + response.getStatus());
            }
        };
    }
}