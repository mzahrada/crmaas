package cz.cvut.fit.zahrama8.crmaas.ui.crm;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import cz.cvut.fit.zahrama8.crmaas.beans.MessageProviderBean;

@ManagedBean(name="appMenu")
@RequestScoped
public class AppMenuModel {
	
	@ManagedProperty(value="#{messageProvider}")
	private MessageProviderBean messageProvider;
	
	private MenuModel model;
	
	public AppMenuModel() {
	}
	
	@PostConstruct
	public void init(){
		model = new DefaultMenuModel();
		
		// Dashboard
		MenuItem item = new MenuItem();
		item.setIcon("ico-menuitem ico-home");
		item.setValue(" " + messageProvider.getLocalizedMessage("Dashboard"));
		item.setUrl("/admin/admin.xhtml");
		model.addMenuItem(item);
		
		
		// Contact list
		Submenu submenu = new Submenu(); 
		submenu.setLabel("Contact list");
		
		item = new MenuItem();
		item.setIcon("ico-menuitem ico-accounts");
		item.setValue("Accounts");
		item.setUrl("/app/app.xhtml");
		submenu.getChildren().add(item);
		
		item = new MenuItem();
		item.setIcon("ico-menuitem ico-contacts");
		item.setValue("Contacts");
		item.setUrl("pretty:admin");
		submenu.getChildren().add(item);
		
		model.addSubmenu(submenu);
		
		
		//Business menu  
        submenu = new Submenu();  
        submenu.setLabel("Business");  	//activities, events, (meeting), task, phone calls
          
        item = new MenuItem();
        item.setIcon("ico-menuitem ico-businesscase");
        item.setValue("Business cases");
        item.setUrl("/app/app.xhtml");
        submenu.getChildren().add(item);  
        
        item = new MenuItem();
        item.setIcon("ico-menuitem ico-products");
		item.setValue("Products");
		item.setUrl("/app/app.xhtml");
        submenu.getChildren().add(item);  
        
		item = new MenuItem();
		item.setIcon("ico-menuitem ico-pricelist");
		item.setValue("Price lists");
		item.setUrl("/app/app.xhtml");
        submenu.getChildren().add(item);       
          
        model.addSubmenu(submenu);
		
		
		// Actvities submenu  
        submenu = new Submenu();  
        submenu.setLabel("Activity");  	//activities, events, (meeting), task, phone calls
          
        item = new MenuItem();
        item.setIcon("ico-menuitem ico-activities");
        item.setValue("Activities");
        item.setUrl("/app/app.xhtml");
        submenu.getChildren().add(item);  
        
        item = new MenuItem();
        item.setIcon("ico-menuitem ico-events");
		item.setValue("Events");
		item.setUrl("/app/app.xhtml");
        submenu.getChildren().add(item);  
        
		item = new MenuItem();
		item.setIcon("ico-menuitem ico-tasks");
		item.setValue("Tasks");
		item.setUrl("/app/app.xhtml");
        submenu.getChildren().add(item);  
        
		item = new MenuItem();
		item.setIcon("ico-menuitem ico-phone");
		item.setValue("Phone calls");
		item.setUrl("/app/app.xhtml");
        submenu.getChildren().add(item);       
          
        model.addSubmenu(submenu);
        
        
        // Reports 
		submenu = new Submenu(); 
		submenu.setLabel("Reports");
		
		item = new MenuItem();
		item.setIcon("ico-menuitem ico-chart-bar");
		item.setValue("Report");
		item.setUrl("/app/app.xhtml");
		submenu.getChildren().add(item);
		
		model.addSubmenu(submenu);
	}
	
	public MenuModel getModel(){
		return model;
	}

	public MessageProviderBean getMessageProvider() {
		return messageProvider;
	}
	public void setMessageProvider(MessageProviderBean messageProvider) {
		this.messageProvider = messageProvider;
	}
	
	

}
