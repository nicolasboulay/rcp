package com.esterel.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.esterel.rental.core.RentalCoreActivator;
import com.esterel.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropertyTreeView extends ViewPart implements IPropertyChangeListener {

	public RentalPropertyTreeView() {
		// TODO Auto-generated constructor stub
	}

	RentalProvider provider = new RentalProvider();
	private TreeViewer tv;
	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent);
		tv.setContentProvider(provider);
		tv.setLabelProvider(provider);

		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		
		getSite().setSelectionProvider(tv);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site);
		
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	
	@Override
	public void dispose() {
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		tv.refresh();
	}

}
