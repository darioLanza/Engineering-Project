import java.io.File;
import javax.swing.filechooser.FileFilter;

public class docFormat extends FileFilter{

	public boolean accept(File file) {
		if (file.isDirectory()) return true;
		    String fname = file.getName().toLowerCase();
		    return fname.endsWith("doc");
	}

    
	public String getDescription() {
		return ".doc";
	}
}