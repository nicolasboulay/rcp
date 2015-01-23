package com.esterel.rental.ui.palettes;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.esterel.rental.ui.RentalUIActivator;
import com.esterel.rental.ui.views.RentalUIConstant;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider,RentalUIConstant {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}
	
	private Color getAColor(String rgbKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry(); 
		Color col = colorRegistry.get(rgbKey);
		if(col == null) {
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}
	
	private Color getColorWithKey(String Key) {
		return getAColor (
		(RentalUIActivator.getDefault().getPreferenceStore().getString(Key)));
	}
	
	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer) {
			//return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);  
			return getColorWithKey(P_COLOR_CUSTOMERS);
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element instanceof RentalObject) {
			//turn Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
			return getColorWithKey(P_COLOR_OBJECT);
		}
		// TODO Auto-generated method stub
		return null;
	}

}
