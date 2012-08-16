package cz.cvut.fit.zahrama8.crmaas.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean(name="locale")
@SessionScoped
public class LocaleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale selectedLocale;

	private static Map<String, Locale> locales;
	
	private static final Locale ENG = Locale.ENGLISH;
	private static final Locale CZE = new Locale("cs", "CZ");
	
	static {
		loadLocaleMap();
	}
	private static void loadLocaleMap(){
		locales = new LinkedHashMap<String, Locale>();
		locales.put("en", ENG);
		locales.put("cz", CZE);
	}
	
//	public enum LocaleEnum {
//		EN("en"),
//		CS("cs"){
//			public Locale getLocale(){
//				return new Locale("cs", "CZ");
//			}
//		};
//		
//		private String code;
//		
//		private LocaleEnum(String code){
//			this.code = code;
//		}
//		
//		public Locale getLocale(){
//			return Locale.ENGLISH;
//		}
//	}
	
	public Map<String, Locale> getLocales() {
		return locales;
	}

	public void handleLocaleChanged(SelectEvent event){
		System.out.println("event: " + event.getObject());
		System.out.println("locale: " + selectedLocale.getDisplayCountry());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(selectedLocale);
	}
	
	public String changeLocale(){
		System.out.println("locale: " + selectedLocale.getDisplayCountry());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(selectedLocale);
		return "";
	}

	public Locale getSelectedLocale() {
		return selectedLocale;
	}

	public void setSelectedLocale(Locale selectedLocale) {
		this.selectedLocale = selectedLocale;
	}
	
	
}
