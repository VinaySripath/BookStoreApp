package com.sprint.bspro.security;


import java.io.IOException; 

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sprint.bspro.service.IBookStoreUserService;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	//@Value("${token}")
	String orgKey = "Capg";
	
	@Autowired
	IBookStoreUserService UserService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/*
		 * Method used to extract the JWT Token 
		 * check starts from our key or not like IBACG
		 * then validate token also
		 * 
		 * */
		
		String requestedTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		 
		System.out.println("\n\n ---->> orgKey "+orgKey+"\n"+requestedTokenHeader);
		if(requestedTokenHeader !=null && requestedTokenHeader.startsWith(orgKey))
		{
			System.out.println("\n\n  ***---->> code Inside filter "+requestedTokenHeader+"\n");
			int cutToken = orgKey.length();	
			jwtToken = requestedTokenHeader.substring(cutToken);
				
				try {
					
					username = jwtUtil.extractUsername(jwtToken);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				// --- code execute if no exception
				
				 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			            UserDetails userDetails = this.UserService.loadUserByUsername(username);

			            if (jwtUtil.validateToken(jwtToken, userDetails)) {

			                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
			                		new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			               
			                usernamePasswordAuthenticationToken
			                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			            }
			        }//end if username != null
				 
				
				
		}//end if
		else
		{
			System.out.println("Token is not validated ...");
		}
		
		
		
		filterChain.doFilter(request, response);
		// NOTE :- always put outside the if ... otherwise leads exception in first login request
		
		
		
		// ----- response time code 
		
	}

	
	
	
}
