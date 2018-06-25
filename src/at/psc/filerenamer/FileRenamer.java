package at.psc.filerenamer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface FileRenamer {
	void rename(ArrayList<File> files, String path) throws IOException;
}
