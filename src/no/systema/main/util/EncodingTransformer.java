/**
 * 
 */
package no.systema.main.util;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.UnmappableCharacterException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.log4j.Logger;

/**
 * This class is used to transform encodings. E.g. from ISO-8859-1 to UTF-8 and vice-versa
 * @author oscardelatorre
 *
 */
public class EncodingTransformer {
	private static final String CLASSNAME = "EncodingTransformer";
	private static final String ENCODING_RPG_BACKEND_LATIN_1 = "ISO-8859-1";
	private static final String ENCODING_RPG_BACKEND_UTF_8 = "UTF-8";
	private static final Logger logger = Logger.getLogger(EncodingTransformer.class.getName());
	
	
	
	/**
	 * This method is mandatory on every RPG call.
	 * The reason is because the RPG payload character set(Latin 1 at Systema AS) is different than the JSON required payload in UTF-8
	 * We must covert back and forward when integrating with the back-end.  (Latin 1 to UTF-8 and vice-versa)
	 *
	 * This implementation ensures that the source to be converted really is in Latin 1 (ISO-8859-1) or UTF-8 BOTH WAYS!
	 * 
	 * @param rawPayload
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public String transformToJSONTargetEncoding(String rawPayload, String encoding) throws Exception{
		String method = EncodingTransformer.CLASSNAME + ".transformToJSONTargetEncoding";
		
		// Create the encoder and decoder for the character set
		Charset charset = Charset.forName(EncodingTransformer.ENCODING_RPG_BACKEND_UTF_8);
		CharsetDecoder decoder = charset.newDecoder();
		CharsetEncoder encoder = charset.newEncoder();
		//default in case of an Exception
		String payload = rawPayload;
		//logger.info("##### payloadLatin1: -->" + payloadLatin1);
		try {
		    // Convert a string to char encoding bytes in a ByteBuffer
		    // The new ByteBuffer is ready to be read.
		    ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(rawPayload));
		    //logger.info("##### bbuf: -->" + bbufLatin1.toString());

		    // The new ByteBuffer is ready to be read.
		    CharBuffer cbuf = decoder.decode(bbuf);
		    payload = cbuf.toString();
		    
		} catch (UnmappableCharacterException e) {
			logger.info("[ERROR] on: " + method);
			//logger.info(rawPayload);
			e.printStackTrace();
			
		}
		//Ensures that we are returning an UTF8-string (Json-mandatory) from the RPG back-end (that should be returning UTF-8
		return new String(payload.getBytes(encoding));
		
	}

	
	
}
