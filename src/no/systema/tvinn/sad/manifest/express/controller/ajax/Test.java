package no.systema.tvinn.sad.manifest.express.controller.ajax;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tmp = "http://gw.systema.no:9886/api/files?companyid=a24&filename=4CR_4bb36639.pdf";
		int i = tmp.indexOf("companyid=");
		int j = tmp.indexOf("&filename=");
		int x = tmp.indexOf("/files?");
		System.out.println(tmp.substring(0,x));
		
		String rawCompanyId = tmp.substring(i,j);
		String rawFilename = tmp.substring(j);
		System.out.println(rawCompanyId);
		System.out.println(rawFilename);
		String companyId = rawCompanyId.replace("companyid=", "");
		System.out.println(companyId);
		String fileName = rawFilename.replace("&filename=", "");
		System.out.println(fileName);
		//
		new Test().run(companyId, fileName);
		
		
	}
	
	private void run(String companyId, String fileName) {
		try {
			DtoNodeGoogleCloudApiFile dtoFile = new DtoNodeGoogleCloudApiFile();
			dtoFile.setCompanyid(companyId);
			dtoFile.setFilename(fileName);
			//
			List list = new ArrayList();
			list.add(dtoFile);
			//
			DtoNodeGoogleCloudApiContainer dtoContainer = new DtoNodeGoogleCloudApiContainer();
			dtoContainer.setLocalWritePath("/temp/");
			dtoContainer.setFiles(list);
			
			ObjectWriter ow = new ObjectMapper().writer();//.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(dtoContainer);
			System.out.println(json);
			json = ow.writeValueAsString(dtoContainer.getFiles());
			System.out.println(json);
			//
			
		}catch(Exception e) {
			
		}
	}

}
