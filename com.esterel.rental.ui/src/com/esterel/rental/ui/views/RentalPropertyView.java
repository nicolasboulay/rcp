package com.esterel.rental.ui.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.esterel.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalPropertyView extends ViewPart implements ISelectionListener {

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	Label rentedObjectLabel ;
	private Label customerName;
	private Group grpDateLocation;
	private Label lblDe;
	private Label lblDe_1;
	private Label startDate;
	private Label endData;
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		parent.setLayout(new GridLayout(1, false));
		Group infoGroup = new Group(parent, SWT.NONE); 
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Information");
		infoGroup.setLayout(new GridLayout(2,false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		Label label = new Label(infoGroup, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText("Loué à :");
		
		customerName = new Label(infoGroup, SWT.NONE);
		customerName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		customerName.setText("...");
		
		grpDateLocation = new Group(parent, SWT.NONE);
		grpDateLocation.setLayout(new GridLayout(2, false));
		grpDateLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpDateLocation.setText("Date location");
		
		lblDe_1 = new Label(grpDateLocation, SWT.NONE);
		lblDe_1.setText("de :");
		
		startDate = new Label(grpDateLocation, SWT.NONE);
		startDate.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		startDate.setText("New Label");

		lblDe = new Label(grpDateLocation, SWT.NONE);
		lblDe.setAlignment(SWT.CENTER);
		lblDe.setText("\u00E0 :");
		
		endData = new Label(grpDateLocation, SWT.NONE);
		endData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		endData.setText("New Label");
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
		
		setLabelAsDragSource(label);
		setLabelAsDragSource(endData);
		setLabelAsDragSource(rentedObjectLabel);
		setLabelAsDragSource(customerName);
		setLabelAsDragSource(startDate);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	
	public void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerName.setText(r.getCustomer().getDisplayName());
		startDate.setText(r.getStartDate().toString());
		endData.setText(r.getEndDate().toString());
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if(o instanceof Rental) {
				setRental((Rental)o);
			}
		}
	}
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
	
	private void setLabelAsDragSource(final Label label) {
		DragSource source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);
		source.setTransfer(new Transfer[] {TextTransfer.getInstance()} );
		
		source.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragSetData(DragSourceEvent event) {
				if(TextTransfer.getInstance().isSupportedType(event.dataType))
				{
					event.data = label.getText();
				}
			}
		});
	}
}
