package com.esterel.rental.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RentalPerspectiveFactory implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		layout.addView("com.esterel.rental.ui.views.RentalPropertyTreeView", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.esterel.rental.ui.views.RentalPropertyView", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
	}

}
