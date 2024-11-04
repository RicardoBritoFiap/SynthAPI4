package com.fiap.Sprint4.cadastroEmpresas.security;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, 
            @NonNull HttpServletResponse response, 
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        // Obtenha o header de autorização
        String authHeader = request.getHeader("Authorization");

        // Verifique se o header contém um token JWT válido
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove o prefixo "Bearer "
            try {
                // Extraia o nome de usuário do token
                String username = jwtUtils.getUsernameFromToken(jwt);

                // Verifique se o nome de usuário está presente e se não há autenticação ativa
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Crie um objeto de autenticação com base no token JWT
                    UsernamePasswordAuthenticationToken authentication = 
                            new UsernamePasswordAuthenticationToken(username, null, null);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // Defina o contexto de segurança com a autenticação
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JwtException e) {
                // Token inválido - envie uma resposta de erro
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT inválido.");
                return;
            }
        }
        
        // Continue o fluxo do filtro
        filterChain.doFilter(request, response);
    }
}
