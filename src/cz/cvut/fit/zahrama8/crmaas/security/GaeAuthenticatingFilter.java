package cz.cvut.fit.zahrama8.crmaas.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

public class GaeAuthenticatingFilter extends AuthenticatingFilter {
	
	public GaeAuthenticatingFilter() {
		setLoginUrl(null);
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) throws Exception {

		
//		return createToken(username, password, request, response);
		return null;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest arg0, ServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
}
