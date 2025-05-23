package Expense.example.ExpenseTrackerSystem.Config;

import Expense.example.ExpenseTrackerSystem.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
   private JWTService jwtService;

    @Autowired
    UserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path=request.getRequestURI();
        if(path.equals("/users/login") || path.startsWith("/users/register") || path.equals("/users/register_Admin")){
            filterChain.doFilter(request,response);
            return;
        }

        String authHeader=request.getHeader("Authorization");
        String token=null;
        String username=null;

         if(authHeader!=null && authHeader.startsWith("Bearer")) {

             token = authHeader.substring(7);
             username = jwtService.extractUsername(token);

         }

       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

         if(username!=null && authentication==null){

             UserDetails userDetails= customUserDetailsService.loadUserByUsername(username);

             if(jwtService.isValid(token, userDetails)){
                 UsernamePasswordAuthenticationToken authToken=
                         new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authToken);

             }
         }
        filterChain.doFilter(request,response);

    }
}
