
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class javaFormat extends FileFilter{

	public boolean accept(File file) {
		if (file.isDirectory()) return true;
		    String fname = file.getName().toLowerCase();
		    return fname.endsWith("java");
	}

    
	public String getDescription() {
		return ".java";
	}
}