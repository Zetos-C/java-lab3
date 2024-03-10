package textApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TextAppController implements ActionListener {
	TextAppView view;
	String text = "";

	public TextAppController(TextAppView view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.openButton) {
			int show = view.fileChoose.showOpenDialog(view);
			if (show == JFileChooser.APPROVE_OPTION) {
				view.textArea.setText("");
				text = "";
				try {
					FileInputStream fileIn = new FileInputStream(view.fileChoose.getSelectedFile());
					int content;
					while ((content = fileIn.read()) != -1) {
						text += (char) content;
					}
					view.textArea.setText(text);
					fileIn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == view.saveButton) {
			if (view.fileChoose.getSelectedFile() == null) {
				JFileChooser fileChooseNew = new JFileChooser();
				int option = fileChooseNew.showSaveDialog(view);
				if (option == JFileChooser.APPROVE_OPTION) {
					view.fileChoose.setSelectedFile(fileChooseNew.getSelectedFile());
					saveFile(fileChooseNew.getSelectedFile());
				}
			} else {

				saveFile(view.fileChoose.getSelectedFile());
			}
		} else if (e.getSource() == view.saveAsButton) {
			JFileChooser fileChooseNew = new JFileChooser();
			
			int option = fileChooseNew.showSaveDialog(view);
			if (option == JFileChooser.APPROVE_OPTION) {
				saveFile(fileChooseNew.getSelectedFile());
			}
		}
	}

	private void saveFile(File linkSave) {
		try {
			FileOutputStream fos = new FileOutputStream(linkSave);
			text = view.textArea.getText();
			byte textToByte[] = text.getBytes();
			fos.write(textToByte);
			fos.close();
			JOptionPane.showMessageDialog(view, "Luu thanh cong");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
