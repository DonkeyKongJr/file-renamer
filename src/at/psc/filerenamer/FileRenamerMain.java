package at.psc.filerenamer;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.*;

public class FileRenamerMain extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String appName ="File Renamer";
	private JList fileList = new JList();
	private ArrayList<File> files = new ArrayList<File>();
	JTextField newNameField = new JTextField();
	JLabel dirLbl = new JLabel();
	JComboBox<String> renameMethods = new JComboBox<String>();
	
	public FileRenamerMain() {
        initUI();
    }

    private void initUI() {
        setLayout(null);

        JButton selectBtn = new JButton("Select Folder...");
        selectBtn.setBounds(470, 350, 140, 25);
        
        JButton renameBtn = new JButton("Start renaming");
        renameBtn.setBounds(470, 380, 140, 25);

        JButton clsBtn = new JButton("Close");
        clsBtn.setBounds(610, 380, 80, 25);
        
        newNameField.setBounds(470, 280, 200, 20);
        
        JLabel prefixLbl = new JLabel("New Name Prefix:");
        prefixLbl.setBounds(472, 260, 200, 20);
        
        JLabel filesLbl = new JLabel("Selected Files:");
        filesLbl.setBounds(50, 30, 200, 20);
        
        fileList.setBounds(50, 50, 400, 250);
        
        dirLbl.setBounds(20, 320, 500, 25);
        
        renameBtn.addActionListener((ActionEvent e) -> {
        	try {
				this.renameFiles();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        
        clsBtn.addActionListener((ActionEvent e) -> {
        	System.exit(0);
        });
        
        selectBtn.addActionListener((ActionEvent e) ->{
        	this.addFileChooser();
        });
        
        add(renameBtn);
        add(filesLbl);
        add(prefixLbl);
        add(newNameField);
        add(selectBtn);
        add(clsBtn);
        add(dirLbl);
        add(fileList);
        
        addComboBox();

        setTitle(appName);
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void addComboBox() {
    	JLabel renameMethodLbl = new JLabel("Rename Method:");
    	renameMethodLbl.setBounds(472, 210, 200, 20);
    	
    	renameMethods.addItem("lastModified");
    	renameMethods.addItem("createdOn");
    	renameMethods.addItem("name");
    	
    	renameMethods.setBounds(472, 230, 200, 20);
    	
    	add(renameMethodLbl);
    	add(renameMethods);
    }
    
    private void addFileChooser() {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		int returnVal = chooser.showOpenDialog(this);
    		if(returnVal == JFileChooser.APPROVE_OPTION) {
    			dirLbl.setText("Folder: " +
    		        chooser.getSelectedFile().getPath());
                addListOverview(chooser.getSelectedFile().getPath());
    		}
    }

	private void addListOverview(String path){
        ArrayList<String> listOfNames = getFilesFromDirectory(path);
        fileList.setListData(listOfNames.toArray());

        invalidate();
        validate();
        repaint();
    }

    private ArrayList<String> getFilesFromDirectory(String path){
    	DirectoryManager dirManager = new DirectoryManager();
    	Tuple<ArrayList<String>, ArrayList<File>> fileInfos = dirManager.getFilesFromDirectory(path);
    	
    	files = fileInfos.y;
    	
    	return fileInfos.x;
    }
    
    private void renameFiles() throws IOException {
    	String selectedMethod = renameMethods.getSelectedItem().toString();
    	FileRenamerFactory renamerFactory = new FileRenamerFactory();
    	FileRenamer fileRenamer = renamerFactory.getFileRenamer(selectedMethod);
    	
    	fileRenamer.rename(files, newNameField.getText());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            FileRenamerMain ex = new FileRenamerMain();
            ex.setVisible(true);
        });
    }
}
