package com.example.apigateway.config;

import com.example.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements WebFilter {

    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebRequest request, WebFilterChain chain) {
        ServerHttpRequest req = request.getRequest();
        if (!req.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return chain.filter(request);
        }

        String token = req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (!jwtUtil.validateToken(token)) {
                return chain.filter(request);
            }
        }

        return chain.filter(request);
    }
}
