package at.psc.filerenamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.commons.io.FilenameUtils;

public class FileRenamerModifiedOn implements FileRenamer {

	@Override
	public void rename(ArrayList<File> files, String prefix) throws IOException {
		if(files.isEmpty()) {
    		throw new IllegalArgumentException("no files selected");
    	}
    	
    	if(prefix == "") {
    		throw new IllegalArgumentException("no prefix is provided");
    	}
    	
    	int count = 0;
    	
    	files.sort(Comparator.comparing(File::lastModified));
    	
    	 for( File file: files ) {
    		count ++;
    		
    		String extensions = FilenameUtils.getExtension(file.toPath().toString());
    		
    		if(extensions.length() > 0) {
    			extensions = "." + extensions;
    		}
    		
    		File newFile = new File(file.getParent(), prefix + "-" + count + extensions);
    		
    		try {
				Files.move(file.toPath(), newFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
    	}
	}

}
