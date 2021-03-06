/*
* generated by Xtext
*/
package org.bonitasoft.studio.condition.ui.labeling;

import org.bonitasoft.studio.model.process.Data;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider; 
import com.google.inject.Inject;

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class ConditionModelLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	public ConditionModelLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}


	//Labels and icons can be computed like this:
	@Override 
	public String text(Object ele) {
		if (ele instanceof Data) {
			return ((Data)ele).getName();
		} 
		return null;
	}

}
