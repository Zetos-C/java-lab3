package textApp2;

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
				int option = view.fileChoose.showSaveDialog(view);
				if (option == JFileChooser.APPROVE_OPTION) {
					saveFile(view.fileChoose.getSelectedFile());
				}
			} else {

				saveFile(view.fileChoose.getSelectedFile());
			}
		} else if (e.getSource() == view.saveAsButton) {
			JFileChooser fileChooseNew = new JFileChooser();
			fileChooseNew.setCurrentDirectory(view.fileChoose.getSelectedFile());
			int option = fileChooseNew.showSaveDialog(view);
			if (option == JFileChooser.APPROVE_OPTION) {
				saveFile(fileChooseNew.getSelectedFile());
			}
		} else if (e.getSource() == view.deleteButton) {
			if(view.fileChoose.getSelectedFile() != null) {
				if (view.fileChoose.getSelectedFile().delete()) {
					JOptionPane.showMessageDialog(view, "Xoa file thanh cong");
					view.fileChoose.setSelectedFile(null);
					view.textArea.setText("");
				}
			}
			else {
				JOptionPane.showMessageDialog(view, "Chua chon file de xoa");
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
			e1.printStackTrace();
			JOptionPane.showMessageDialog(view, "Luu khong thanh cong");
		}
	}

}
