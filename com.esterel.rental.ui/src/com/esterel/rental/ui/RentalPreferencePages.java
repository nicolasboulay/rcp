package com.esterel.rental.ui;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.esterel.rental.ui.views.RentalUIConstant;

public class RentalPreferencePages extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage, RentalUIConstant {

	public RentalPreferencePages() {
		// TODO Auto-generated constructor stub
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental color");
	}

	public RentalPreferencePages(int style) {
		super(style);
		// TODO Auto-generated constructor stub
	}

	public RentalPreferencePages(String title, int style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}

	public RentalPreferencePages(String title, ImageDescriptor image, int style) {
		super(title, image, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		addField(new ColorFieldEditor(P_COLOR_RENTAL,"Rental :", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_COLOR_OBJECT,"Object :", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_COLOR_CUSTOMERS,"Customers :", getFieldEditorParent()));
	}
	

}
