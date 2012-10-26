import java.io.File;
import javax.swing.filechooser.FileFilter;

public class pdfFormat extends FileFilter{

	public boolean accept(File file) {
		if (file.isDirectory()) return true;
		    String fname = file.getName().toLowerCase();
		    return fname.endsWith("pdf");
	}

    
	public String getDescription() {
		return ".pdf";
	}
}