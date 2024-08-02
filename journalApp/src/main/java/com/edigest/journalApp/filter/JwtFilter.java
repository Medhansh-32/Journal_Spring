package com.edigest.journalApp.filter;

import com.edigest.journalApp.service.UserDetailsServiceImpl;
import com.edigest.journalApp.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authrizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authrizationHeader != null && authrizationHeader.startsWith("Bearer ")) {
            jwt = authrizationHeader.substring(7);
            username=jwtUtil.extractUsername(jwt);
        }
        if(username!=null){
            UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt)){
                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
    response.addHeader("admin","Medhansh Sharma");
        filterChain.doFilter(request,response);
    }


}
/*    Request Interception:
        The filter intercepts each request (doFilterInternal method).

    Extract JWT:
        Checks for an Authorization header starting with Bearer and extracts the JWT.

    Validate JWT:
        Extracts the username from the JWT and validates the token.

    Set Authentication:
        If the token is valid and the user is not already authenticated, it loads the user details and sets the authentication in the SecurityContextHolder.

    Add Header:
        Adds a custom header to the response (e.g., for testing or tracing).

Summary of How Authentication Happens

    Request Interception:
        The JwtFilter intercepts incoming HTTP requests.

    Extract and Validate JWT:
        It extracts the JWT from the Authorization header.
        Validates the JWT using JwtUtil.

    Load User Details:
        If the JWT is valid, it loads user details from the database using UserDetailsServiceImpl.

    Set Authentication:
        It creates an Authentication object and sets it in the SecurityContextHolder, establishing the user's authentication context for the current request.

    Proceed with Filter Chain:
        The request continues through the filter chain with the authentication context set.*/
