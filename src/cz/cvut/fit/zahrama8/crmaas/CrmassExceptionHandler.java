package cz.cvut.fit.zahrama8.crmaas;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class CrmassExceptionHandler extends ExceptionHandlerWrapper {
	
	private ExceptionHandler handler;
	
	public CrmassExceptionHandler(ExceptionHandler handler) {
		this.handler = handler;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.handler;
	}
	
	@Override
	public void handle() throws FacesException {
        Iterable<ExceptionQueuedEvent> events = this.handler.getUnhandledExceptionQueuedEvents();
        for(Iterator<ExceptionQueuedEvent> it = events.iterator(); it.hasNext();) {
            ExceptionQueuedEvent event = it.next();
            ExceptionQueuedEventContext eqec = event.getContext();
            
            if(eqec.getException() instanceof ViewExpiredException) {
                FacesContext context = eqec.getContext();
                NavigationHandler navHandler = context.getApplication().getNavigationHandler();
 
                try {
                    navHandler.handleNavigation(context, null, "hello?faces-redirect=true&expired=true");
                }
                finally {
                    it.remove();
                }
            }
        }

        this.handler.handle();
	}

}
