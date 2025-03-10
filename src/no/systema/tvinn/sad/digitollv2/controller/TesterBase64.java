package no.systema.tvinn.sad.digitollv2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class TesterBase64 {

	public static void main(String[] args) {
		//String payload = "image.png";
		//String payload = "Scan2024-09-17_185533.pdf";
		String payload = "Notification-Example.xml";
		try {
            String payloadPath = "/Users/oscardelatorre/" + File.separator + payload;
            String base64String = convertImageToBase64(payloadPath);
            System.out.println("Base64 String: " + base64String);
            //decode and write
            byte[] bytes = Base64.getDecoder().decode(base64String);
            Path filePath = Paths.get("/Users/oscardelatorre/" + File.separator + "ny-" + payload);
            Files.write(filePath, bytes);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static String convertImageToBase64(String payloadPath) throws IOException {
        File imageFile = new File(payloadPath);
        if (imageFile.exists()) {
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            byte[] imageData = new byte[(int) imageFile.length()];
            fileInputStream.read(imageData);
            fileInputStream.close();
            return Base64.getEncoder().encodeToString(imageData);
        } else {
            throw new IOException("Image file not found: " + payloadPath);
        }
    }

}
