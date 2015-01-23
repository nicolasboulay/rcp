package com.esterel.rental.ui;

import java.util.HashMap;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.esterel.rental.ui.RentalUIActivator.Palette;
import com.esterel.rental.ui.views.RentalUIConstant;

public class RentalPalettePreferencePages extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage, RentalUIConstant {

	public RentalPalettePreferencePages() {
		// TODO Auto-generated constructor stub
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental palette");
	}

	public RentalPalettePreferencePages(int style) {
		super(style);
		// TODO Auto-generated constructor stub
	}

	public RentalPalettePreferencePages(String title, int style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}

	public RentalPalettePreferencePages(String title, ImageDescriptor image, int style) {
		super(title, image, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		HashMap<String, Palette> pm = RentalUIActivator.getDefault().getPaletteManager();
		
		String[][] comboValues = new String[pm.size()][2];
		int i = 0;
		for(RentalUIActivator.Palette p : pm.values()) {
			comboValues[i][0] = p.name;
			comboValues[i][1] = p.ID;
			i++;
		}
		// TODO Auto-generated method stub
		addField(new ComboFieldEditor(P_PALETTE_RENTAL,"palette :", comboValues, getFieldEditorParent()));

	}
	

}
