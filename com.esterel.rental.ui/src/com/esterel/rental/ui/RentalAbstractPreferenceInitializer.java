package com.esterel.rental.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.esterel.rental.ui.views.RentalUIConstant;

public class RentalAbstractPreferenceInitializer extends
		AbstractPreferenceInitializer  implements RentalUIConstant {

	public RentalAbstractPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub
		IPreferenceStore ps = RentalUIActivator.getDefault().getPreferenceStore();
		
		ps.setDefault(P_COLOR_CUSTOMERS, StringConverter.asString(new RGB(200,100,50)));
		ps.setDefault(P_COLOR_OBJECT, StringConverter.asString(new RGB(100,200,50)));
		ps.setDefault(P_COLOR_RENTAL, StringConverter.asString(new RGB(50,100,200)));
	}

}
