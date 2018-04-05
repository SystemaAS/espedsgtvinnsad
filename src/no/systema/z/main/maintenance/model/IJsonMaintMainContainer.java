/**
 * 
 */
package no.systema.z.main.maintenance.model;

import java.util.Collection;

/**
 * This interface should be implemented by Containers that can use the generic {@link MaintMainGenericMapper}
 * 
 * @author Fredrik Möller
 * @date Apr 03, 2018
 *
 */
public interface IJsonMaintMainContainer {

	/**
	 * Get list of Records
	 * @return
	 */
	public Collection<?> getDtoList();

}
