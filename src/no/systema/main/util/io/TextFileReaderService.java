/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.systema.main.util.io;

import java.io.*;
import java.util.*;


import org.apache.log4j.Logger;
/**
 *
 * @author oscart
 */
public class TextFileReaderService  {
	private static final Logger logger = Logger.getLogger(TextFileReaderService.class.getName());
	
	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public List <String> getFileLines(InputStream inputStream){
        BufferedReader reader = null;
        List <String> list = new ArrayList<String>();
        
        try{
            //reader = new BufferedReader(new FileReader(file));
            InputStreamReader isr = new InputStreamReader(inputStream,"ISO-8859-1");
            reader = new BufferedReader(isr);
            String text = null;
 
            // repeat until all lines are read
            while ((text = reader.readLine()) != null){
                list.add(text);
                //logger.info(text);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try{
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        
        return list;

    }

	/**
	 * 
	 * @param inputStream
	 * @param encoding
	 * @return
	 */
	public List <String> getFileLines(InputStream inputStream, String encoding){
        BufferedReader reader = null;
        List <String> list = new ArrayList<String>();
        
        try{
            //reader = new BufferedReader(new FileReader(file));
            InputStreamReader isr = new InputStreamReader(inputStream, encoding);
            reader = new BufferedReader(isr);
            String text = null;
 
            // repeat until all lines are read
            while ((text = reader.readLine()) != null){
                list.add(text);
                //logger.info(text);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try{
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        
        return list;

    }
	
	/**
     * gets a list of lines from a text file
     *  
     * @param absolutPath
     * @return
     */
    public List <String> getFileLines(String absolutPath){
        File file = new File(absolutPath);
        BufferedReader reader = null;
        List <String> list = new ArrayList<String>();
        
        try{
            //reader = new BufferedReader(new FileReader(file));
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis,"ISO-8859-1");
            reader = new BufferedReader(isr);
            String text = null;
 
            // repeat until all lines are read
            while ((text = reader.readLine()) != null){
                list.add(text);
                //logger.info(text);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try{
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        
        return list;

    }

    /**
     * 
     * @param absolutPath
     * @param encoding (UTF-8, ISO-8859-1)
     * 
     * @return
     */
    public List <String> getFileLines(String absolutPath, String encoding){
        File file = new File(absolutPath);
        BufferedReader reader = null;
        List <String> list = new ArrayList<String>();
        
        try{
            //reader = new BufferedReader(new FileReader(file));
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, encoding);
            reader = new BufferedReader(isr);
            String text = null;
 
            // repeat until all lines are read
            while ((text = reader.readLine()) != null){
                list.add(text);
                //logger.info(text);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try{
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        
        return list;

    }

}
