package com.esterel.rental.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class HandlerHelloWorld extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window;
		
		window = HandlerUtil.getActiveWorkbenchWindow(event);
		MessageDialog.openInformation(window.getShell(), "PluginID", "Hello world");
		return null;
	}

}
