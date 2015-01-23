package com.esterel.rental.ui.cmd;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.opcoach.training.rental.Customer;

public class RentalCopy extends AbstractHandler {

	Object getSelection(ExecutionEvent event) {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if(currentSelection instanceof IStructuredSelection) {
			IStructuredSelection isel = (IStructuredSelection)currentSelection;
			return isel.getFirstElement();
		}
		return null;
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Object sel = getSelection(event);
		if (sel != null && sel instanceof Customer) {
			Clipboard clipboard = new Clipboard(Display.getCurrent());
			String textData = ((Customer)sel).getDisplayName();
			String rtfData = "{\\rtf1\\b\\i " + textData +"}";
			TextTransfer textTransfer = TextTransfer.getInstance();
			RTFTransfer rtfTransfer = RTFTransfer.getInstance();
			Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
			Object[] data = new Object[]{textData, rtfData};
			clipboard.setContents(data, transfers);
			clipboard.dispose();
		}
		
		return null;
	}

}
