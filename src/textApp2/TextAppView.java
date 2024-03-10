package textApp2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class TextAppView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton openButton = new JButton("Open");
	JButton saveButton = new JButton("Save");
	JButton saveAsButton = new JButton("Save As");
	JFileChooser fileChoose = new JFileChooser();
	JTextArea textArea = new JTextArea();
	private TextAppController controller = new TextAppController(this);
	JButton deleteButton = new JButton("Delete");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextAppView frame = new TextAppView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TextAppView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		openButton.addActionListener(controller);
		saveButton.addActionListener(controller);
		saveAsButton.addActionListener(controller);
		deleteButton.addActionListener(controller);
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel.add(openButton);
		
		panel.add(saveButton);
		
		panel.add(saveAsButton);
		
		panel.add(deleteButton);
	}
}
