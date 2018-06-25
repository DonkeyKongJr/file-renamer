package at.psc.filerenamer;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

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
        okBtn.setBounds(400, 350, 140, 25);

        JButton clsBtn = new JButton("Close");
        clsBtn.setBounds(550, 350, 80, 25);
        
        dirLbl.setBounds(20, 320, 500, 25);
        
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
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        JList list = new JList(listOfNames.toArray());
        list.setBounds(50, 50, 400, 250);
        add(list);

        invalidate();
        validate();
        repaint();
    }

    private ArrayList<String> getFilesFromDirectory(String path){
	    if(path.length() == 0){
	        throw new IllegalArgumentException("path is empty");
        }

        ArrayList<String> fileNames = new ArrayList<String>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileNames.add(file.getName());
                System.out.println(file.getName());
            }
        }

        return  fileNames;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            FileRenamerMain ex = new FileRenamerMain();
            ex.setVisible(true);
        });
    }
}
