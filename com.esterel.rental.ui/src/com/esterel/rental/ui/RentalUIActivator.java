package com.esterel.rental.ui;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
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
}
