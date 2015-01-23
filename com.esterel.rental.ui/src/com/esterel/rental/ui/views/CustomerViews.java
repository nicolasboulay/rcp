package com.esterel.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Customer;

public class CustomerViews extends ViewPart implements ISelectionListener {

	public static final String ID = "com.esterel.rental.ui.views.CustomerViews"; //$NON-NLS-1$
	private Label lblNewLabel;

	public CustomerViews() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(24, 10, 55, 15);
		lblNewLabel.setText("New Label");
	}

	

	@Override
	public void setFocus() {
		// Set the focus
	}
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection.isEmpty()) {
			return;
		}
		
		if(selection instanceof IStructuredSelection) {
			Object sel = ((IStructuredSelection) selection).getFirstElement();
			
			Customer c = (Customer) Platform.getAdapterManager().getAdapter(sel, Customer.class);
		
			if(c != null) {
				lblNewLabel.setText(c.getDisplayName());
			}
		}
		
	}
}
