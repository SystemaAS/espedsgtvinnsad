package no.systema.tvinn.sad.z.maintenance.nctsexport.controller;

/**
 * DTO of search criteria using NCTS Exort -Koderegister
 * 
 * @author Fredrik Möller
 * @date Okt 20, 2016
 *
 */
public class SearchFilterTransitKoder {

	private String searchTkunik = null;
	private String searchTkkode = null;
	private String searchTktxtn = null;
	
	
	public String getSearchTkunik() {
		return searchTkunik;
	}
	public void setSearchTkunik(String searchTkunik) {
		this.searchTkunik = searchTkunik;
	}
	public String getSearchTkkode() {
		return searchTkkode;
	}
	public void setSearchTkkode(String searchTkkode) {
		this.searchTkkode = searchTkkode;
	}
	public String getSearchTktxtn() {
		return searchTktxtn;
	}
	public void setSearchTktxtn(String searchTktxtn) {
		this.searchTktxtn = searchTktxtn;
	}

	
}
