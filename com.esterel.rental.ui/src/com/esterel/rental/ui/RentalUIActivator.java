package com.esterel.rental.ui;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.esterel.rental.ui.views.RentalUIConstant;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements RentalUIConstant {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.esterel.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;
	
	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}
	
	private void printExtension() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for(IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
			
			System.out.println("Plugin : " + e.getNamespaceIdentifier() + " " + e.getName()+ " : " +e.getAttribute("name"));
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		printExtension();
		readPaletteExtension();
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}
 
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(getClass());
		
		reg.put(ICONE_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(ICONE_CUSTOMER)));
		reg.put(ICONE_RENTAL, ImageDescriptor.createFromURL(b.getEntry(ICONE_RENTAL)));
		reg.put(ICONE_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(ICONE_RENTAL_OBJECT)));
		reg.put(ICONE_AGENCY, ImageDescriptor.createFromURL(b.getEntry(ICONE_AGENCY)));
	
	}
	class Palette implements IColorProvider {
		public String ID, name;
		public IColorProvider icp;
		@Override
		public Color getForeground(Object element) {
			return icp.getForeground(element);
		}
		@Override
		public Color getBackground(Object element) {
			return icp.getBackground(element);
		}
		
	}
	private HashMap<String,Palette> paletteManager = new HashMap<String, RentalUIActivator.Palette>();
	private void readPaletteExtension() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for(IConfigurationElement e : reg.getConfigurationElementsFor("com.esterel.rental.ui.palette")) {
			System.out.println("Plugin : " + e.getNamespaceIdentifier() + " " + e.getName()+ " : " +e.getAttribute("name"));
			Palette p = new Palette();
			p.name = e.getAttribute("name");
			p.ID = e.getAttribute("ID");
			try {
				p.icp = (IColorProvider) e.createExecutableExtension("class");
				paletteManager.put(p.ID, p);
			} catch (CoreException ex) {
				getLog().log(new Status(IStatus.ERROR, e.getNamespaceIdentifier(), 
						"Unable to create extension.", ex));
			}

			
		}
		
	}
	public HashMap<String,Palette> getPaletteManager()
	{
		return paletteManager;
	} 
	
}
