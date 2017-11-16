package com.imnachos.coffeepad.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class FileManager {


	public static String openFile(File selectedFile){
		
        try{
            byte[] contents = Files.readAllBytes(selectedFile.toPath());
            return new String(contents, "ISO-8859-1");
        }catch(Exception e){
            //TODO HANDLE. Should not happen.
        	return "";
        }
	}
	
	public static boolean saveFile(File outputFile, String fileContent){
		
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(outputFile);
            fileWriter.println(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //TODO Handle properly
            return false;
        
        } finally {
            
        	if(fileWriter != null){
                fileWriter.close();
            }
        }
        return true;
	}


	
	


}
