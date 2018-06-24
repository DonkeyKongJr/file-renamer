package at.psc.filerenamer;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JFileChooser;

public class FileRenamerMain extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String appName ="File Renamer";
	
	JLabel dirLbl = new JLabel();
	
	public FileRenamerMain() {
        initUI();
    }

    private void initUI() {
        setLayout(null);

        JButton okBtn = new JButton("Select Folder...");
        okBtn.setBounds(20, 50, 180, 25);

        JButton clsBtn = new JButton("Close");
        clsBtn.setBounds(250, 50, 80, 25);
        
        dirLbl.setBounds(20, 150, 300, 25);
        
        clsBtn.addActionListener((ActionEvent e) -> {
        	System.exit(0);
        });
        
        okBtn.addActionListener((ActionEvent e) ->{
        	this.addFileChooser();
        });

        add(okBtn);
        add(clsBtn);
        add(dirLbl);

        setTitle(appName);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void addFileChooser() {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		int returnVal = chooser.showOpenDialog(this);
    		if(returnVal == JFileChooser.APPROVE_OPTION) {
    			dirLbl.setText("Target Folder: " +
    		        chooser.getSelectedFile().getName());
    		}

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            FileRenamerMain ex = new FileRenamerMain();
            ex.setVisible(true);
        });
    }

}
