package com.taurusandchicken.web.utility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetEncodeFilter implements Filter{
	
	

protected String defaultEncoding = null; 

/* (non-Javadoc) 
 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
 */ 
public void init(FilterConfig filterConfig) throws ServletException { 
    // TODO Auto-generated method stub 
    
    this.defaultEncoding = filterConfig.getInitParameter("defaultencoding"); 
} 

/* (non-Javadoc) 
 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
 */ 
public void doFilter( 
    ServletRequest request, 
    ServletResponse response, 
    FilterChain chain) 
    throws IOException, ServletException { 
    // TODO Auto-generated method stub 
    request.setCharacterEncoding(selectEncoding(request)); 
    response.setContentType("text/html;charset="+defaultEncoding);
    chain.doFilter(request, response); 
} 


public void destroy() { 
     
    this.defaultEncoding = null; 
   
} 


protected String selectEncoding(ServletRequest request) { 

    return this.defaultEncoding; 
} 

}
