package cz.cvut.fit.zahrama8.crmaas;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CrmassExceptionHandlerFactory extends ExceptionHandlerFactory {
	
	private ExceptionHandlerFactory factory;
	
	public CrmassExceptionHandlerFactory(ExceptionHandlerFactory factory) {
		this.factory = factory;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new CrmassExceptionHandler(factory.getExceptionHandler());
	}

}
