/**
 * 
 */
package no.systema.z.main.maintenance.model;

import java.util.Collection;

/**
 * @author Fredrik Möller
 * @date Apr 3, 2018
 *
 */
public class JsonMaintMainChildWindowKofastContainer implements IJsonMaintMainContainer {
	private String user = null;
	private String errMsg = null;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	private Collection<JsonMaintMainChildWindowKofastRecord> list;

	public void setList(Collection<JsonMaintMainChildWindowKofastRecord> value) {
		this.list = value;
	}

	@Override
	public Collection<JsonMaintMainChildWindowKofastRecord> getDtoList() {
		return list;
	}

}
