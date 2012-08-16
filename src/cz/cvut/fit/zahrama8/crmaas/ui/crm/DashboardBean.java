package cz.cvut.fit.zahrama8.crmaas.ui.crm;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean(name="dashboard")
@RequestScoped
public class DashboardBean implements Serializable {

	private DashboardModel model;
	
	private Panel panel;
	private Panel r1;
	private Panel r2;
	
	public DashboardBean() {}
	
	@PostConstruct
	public void init(){
		model = new DefaultDashboardModel();
		
		DashboardColumn col1 = new DefaultDashboardColumn();
		DashboardColumn col2 = new DefaultDashboardColumn();
		DashboardColumn col3 = new DefaultDashboardColumn();
		
		panel = new Panel();
		panel.setId("report");
		panel.setHeader("Report");
		
		r1 = new Panel();
		r1.setId("r1");
		r1.setHeader("r1");
		
		r2 = new Panel();
		r2.setId("r2");
		r2.setHeader("r2");
		r2.setRendered(true);
		r2.setVisible(false);
		
		col2.addWidget("report");
		
		col1.addWidget("links");
		col1.addWidget("notes");
		col1.addWidget("onlineUsers");
		
		col2.addWidget("activities");
		
		col3.addWidget("analysisCases");
		col3.addWidget("analysisSales");
		
		model.addColumn(col1);
		model.addColumn(col2);
		model.addColumn(col3);
	}
	
	public void handleReorder(DashboardReorderEvent reorderEvent){
		System.out.println("reor");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Reordered: " + reorderEvent.getWidgetId(), 
				"Item index: " + reorderEvent.getItemIndex() + ", " +
				"col index: " + reorderEvent.getColumnIndex());
		getR2().setVisible(true);
		addMessage(message);
	}
	
	public void handleClose(CloseEvent event) {  
		System.out.println("close");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");  
        
        addMessage(message);  
    } 
	
	private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
	
	public DashboardModel getModel() {
		return model;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public Panel getR1() {
		return r1;
	}

	public void setR1(Panel r1) {
		this.r1 = r1;
	}

	public Panel getR2() {
		return r2;
	}

	public void setR2(Panel r2) {
		this.r2 = r2;
	}
	
	
}
