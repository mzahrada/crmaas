package cz.cvut.fit.zahrama8.crmaas.beans;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="messageProvider")
@SessionScoped
public class MessageProviderBean implements Serializable {
	
	private ResourceBundle bundle;
	
	public MessageProviderBean() {}
	
	public ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msg");
		}
		return bundle;
	}
	
	public String getLocalizedMessage(String key) {
		String result = null;
		try {
			result = getBundle().getString(key);
		} catch (MissingResourceException e) {
			result = "???" + key + "??? not found";
		}
		return result;
	}
}
