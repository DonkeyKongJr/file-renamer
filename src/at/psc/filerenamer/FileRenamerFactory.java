package at.psc.filerenamer;

public class FileRenamerFactory {

	public FileRenamer getFileRenamer(String fileRenamerType) {
		if(fileRenamerType.equalsIgnoreCase("lastModified")){
			return new FileRenamerModifiedOn();
		}
		else if(fileRenamerType.equalsIgnoreCase("name")){
			return new FileRenamerName();
		}
		
		throw new IllegalArgumentException("Factory "+ fileRenamerType +" not found");
	}
}
