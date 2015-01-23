package com.esterel.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.esterel.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstant {

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if(inputElement instanceof Collection) {
			return ((Collection<?>) inputElement).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof RentalAgency) {
			RentalAgency ra = (RentalAgency) parentElement;
			return new Node[] {new Node(CUSTOMERS,ra), new Node(LOCATIONS,ra), new Node(OBJET_À_LOUER,ra)};
		} else if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return (element instanceof RentalAgency) || element instanceof Node;
	}
	
	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		
		if(element instanceof RentalAgency) {
			return ((RentalAgency)element).getName();
		}
		if(element instanceof Customer) {
			return ((Customer)element).getDisplayName();
		}
		if(element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		return super.getText(element);
	}

	class Node {
		private String label;
		private RentalAgency ra;
		public Object[] getChildren() {
			if (label == CUSTOMERS) {
				return ra.getCustomers().toArray();
			} else if (label == LOCATIONS) {
				return ra.getRentals().toArray();
			} if (label == OBJET_À_LOUER) {
				return ra.getObjectsToRent().toArray();
			}
			return null;
		};
		public Node(String label, RentalAgency ra) {
			super();
			this.label = label;
			this.ra = ra;
		}
		@Override
		public String toString() {
			return label;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			result = prime * result + ((ra == null) ? 0 : ra.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			if (ra == null) {
				if (other.ra != null)
					return false;
			} else if (!ra.equals(other.ra))
				return false;
			return true;
		}
		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
	}
	
	private IColorProvider getPalette() {
		String pid =
		(RentalUIActivator.getDefault().getPreferenceStore().getString(P_PALETTE_RENTAL));
		return (IColorProvider) RentalUIActivator.getDefault().getPaletteManager().get(pid);

	}
	
	@Override
	public Color getForeground(Object element) {
		return getPalette().getForeground(element);

	}

	@Override
	public Color getBackground(Object element) {
		return getPalette().getBackground(element);
	}
	
	@Override
	public Image getImage(Object element) {
		ImageRegistry imageRegistry = RentalUIActivator.getDefault().getImageRegistry();
		if(element instanceof Customer) {
			return imageRegistry.get(ICONE_CUSTOMER);
		} else if (element instanceof RentalAgency) {
			return imageRegistry.get(ICONE_AGENCY);
		} else if (element instanceof RentalObject) {
			return imageRegistry.get(ICONE_RENTAL_OBJECT);
		} else if (element instanceof Rental) {
			return imageRegistry.get(ICONE_RENTAL);
		}
		return null;
	}
		
}
