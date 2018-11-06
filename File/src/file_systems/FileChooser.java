package file_systems;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileChooser {
	public FileChooser() {

	}

	public String SelectedFile(String []array) {
		JFileChooser textFile = new JFileChooser();

		JFrame text = new JFrame();
		int result = textFile.showOpenDialog(new JFrame());

		if (result == textFile.APPROVE_OPTION) {
			File selectedFile = textFile.getSelectedFile();
			String s = selectedFile.getAbsolutePath();
			if (s.endsWith(".du")) {
				int pr = s.lastIndexOf("\\");
				int qr = s.indexOf(".");
				char[] path = s.toCharArray();
				String fileName = new String(path, pr + 1, qr - pr - 1);
				array[0] =fileName;
				
				//return fileName;
				return s;
				
			}

			else{
				JOptionPane.showMessageDialog(null, "Can't open the file. File extension must have .du");
				s=null;
				return s;
			}
		}
		return null;
	}
}
