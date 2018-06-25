package at.psc.filerenamer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryManager {

    public Tuple<ArrayList<String>, ArrayList<File>> getFilesFromDirectory(String path){
	    if(path.length() == 0){
	        throw new IllegalArgumentException("path is empty");
        }

        ArrayList<String> fileNames = new ArrayList<String>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(listOfFiles));

        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileNames.add(file.getName());
                System.out.println(file.getName());
            }
        }

        return new Tuple<ArrayList<String>, ArrayList<File>>(fileNames, files);
    }
}
