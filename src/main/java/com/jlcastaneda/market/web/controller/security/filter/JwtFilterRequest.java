package com.jlcastaneda.market.web.controller.security.filter;

import com.jlcastaneda.market.domain.service.UserDatailsService;
import com.jlcastaneda.market.web.controller.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDatailsService userDatailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

            //verificamos que en el contexto no exista ninguna autenticación para este usuario
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                //obtenemos userDeltails
                UserDetails userDetails = userDatailsService.loadUserByUsername(username);

                //validamos el token
                if(jwtUtil.validateToken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //verificamos los detalles de conexión , SO, navegador, etc
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    //le ponemos la autenticaicón para que la proxima vez no vuelva a entrar a esta verificacion
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
