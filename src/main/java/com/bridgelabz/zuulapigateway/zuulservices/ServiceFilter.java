package com.bridgelabz.zuulapigateway.zuulservices;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ServiceFilter extends ZuulFilter {

	@Override
	public Object run() throws ZuulException {

		final String key = "sasi";
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		//System.out.println(request.getRequestURI() + "iiiiiiiiiiiiiii");
		if (!request.getRequestURI().startsWith("/user")) {

			String tokenId = request.getHeader("token");
			//System.out.println(tokenId + "------token");

			if (tokenId != null) {

				Claims claim = Jwts.parser().setSigningKey(key).parseClaimsJws(tokenId).getBody();
				ctx.addZuulRequestHeader("userId", claim.getId());
			}
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public int filterOrder() {

		return 1;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	
	/*
	 * public static void main(String[] args) throws ZuulException { ServiceFilter
	 * serviceFilter=new ServiceFilter(); serviceFilter.run(); }
	 */

}
