package cz.cvut.fit.zahrama8.crmaas.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="login")
@RequestScoped
public class LoginBean implements Serializable {

	private String username;
	private String password;

	
	public LoginBean() {}

	public String doLogin(){
		if (username.equals("admin")) {
			return "pretty:admin?faces-redirect=true";		// TODO correct?
		}
		if (username.equals("app")) {
			return "crm/app?faces-redirect=true";
		}
		return "hello?faces-redirect=true";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
