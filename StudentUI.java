package assignment5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class StudentUI extends JPanel
implements PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	/**
	 * display name label
	 */
	private JLabel NameLabel;
	/**
	 * display address label
	 */
	private JLabel AddressLabel;
	/**
	 * display phone label
	 */
	private JLabel PhoneLabel;
	/**
	 * display student id label
	 */
	private JLabel StudentIDLabel;
	/**
	 * display student average label
	 */
	private JLabel StudentAverageLabel;
	/**
	 * display student grade label
	 */
	private JLabel StudentGradesLabel;
	/**
	 * display path label
	 */
	private JLabel PathLabel;
	/**
	 * display gender label
	 */
	private JLabel lblGender;
	
	/**
	 * displays name of the student
	 */
	private JFormattedTextField NameField;
	/**
	 * displays address of the student
	 */
	private JFormattedTextField AddressField;
	/**
	 * displays gender of the student
	 */
	private JFormattedTextField txtGender;
	/**
	 * displays phone number of the student
	 */
	private JFormattedTextField PhoneField;
	/**
	 * displays student id of the student
	 */
	private JFormattedTextField StudentIDField;
	/**
	 * displays average of the student
	 */
	private JFormattedTextField StudentAverageField;
	/**
	 * displays grade of the student
	 */
	private JFormattedTextField StudentGradesField;
	/**
	 * displays path of the student csv
	 */
	private JFormattedTextField PathField;

	/**
	 * stores the index of the student displayed
	 */
	private int studentIndex;
	/**
	 * store all student details
	 */
	private ArrayList<Student> sList_0;
	/**
	 * stores the file system
	 */
	private FileSystem f;
	/**
	 * stores the path of the student csv file
	 */
	private Path path;
	/**
	 * stores the file name of the student csv
	 */
	private String FileName;
	/**
	 * exits the application when clicked
	 */
	private JButton btnExit;
	
	/**
	 * Constructor  to create StudentUI object
	 * @param FileName: student csv file path
	 */
	public StudentUI(String FileName){
		//Create Jpanel.
		super(new BorderLayout());
		//Assingns the class variable file name.
		this.FileName= FileName;
		f= FileSystems.getDefault();
		//Reads the absolute path of file
		path= f.getPath(FileName);
		//Reads the file and creates the student list
		readFile();
		studentIndex= 0;
		//Creates the UI and fills with the student at current student index.
		update();
	}
	/**
	 * creates the UI to display a student details 
	 */
	private void update()
	{
		//Clears the JPanel.
		removeAll();
		//Creates Label objects
		NameLabel = new JLabel("Name: ");
		AddressLabel = new JLabel("Address: ");
		PhoneLabel = new JLabel("Phone: ");
		StudentIDLabel = new JLabel("StudentID: ");
		StudentAverageLabel= new JLabel("Average: ");
		StudentGradesLabel= new JLabel("Grades: ");
		PathLabel = new JLabel("FileName");
		lblGender = new JLabel("Gender");
		//Creates Text field 
		NameField = new JFormattedTextField();
		//Sets with respective student value
		NameField.setValue(new String(sList_0.get(studentIndex).getName()));
		//sets the size of the text field
		NameField.setColumns(40);
		//adds the listener to the field.
		NameField.addPropertyChangeListener("value", this);

		txtGender = new JFormattedTextField();
		txtGender.setValue(new String(sList_0.get(studentIndex).getGender()));
		txtGender.setColumns(40);
		txtGender.addPropertyChangeListener("value", this);
		
		AddressField = new JFormattedTextField();
		AddressField.setValue(new String(sList_0.get(studentIndex).getAddress()));
		AddressField.setColumns(40);
		AddressField.addPropertyChangeListener("value", this);

		PhoneField = new JFormattedTextField();
		PhoneField.setValue(new String(sList_0.get(studentIndex).getPhone()));
		PhoneField.setColumns(40);
		PhoneField.addPropertyChangeListener("value", this);

		StudentIDField = new JFormattedTextField();
		StudentIDField.setValue(new String(sList_0.get(studentIndex).getStudentID()));
		StudentIDField.setColumns(40);
		StudentIDField.addPropertyChangeListener("value", this);

		StudentAverageField = new JFormattedTextField();
		StudentAverageField.setValue(new String(sList_0.get(studentIndex).getAverage()));
		StudentAverageField.setColumns(40);
		StudentAverageField.setEditable(false);
		StudentAverageField.addPropertyChangeListener("value", this);

		StudentGradesField = new JFormattedTextField();
		StudentGradesField.setValue(new String(sList_0.get(studentIndex).getGrades()));
		StudentGradesField.setColumns(40);
		StudentGradesField.addPropertyChangeListener("value", this);
		
		PathField = new JFormattedTextField();
		PathField.setValue(new String(FileName));
		PathField.setColumns(40);
		PathField.addPropertyChangeListener("value", this);

		//Sets the Label to the Fields 
		NameLabel.setLabelFor(NameField);
		AddressLabel.setLabelFor(AddressField);
		PhoneLabel.setLabelFor(PhoneField);
		StudentIDLabel.setLabelFor(StudentIDField);
		StudentAverageLabel.setLabelFor(StudentAverageField);
		StudentGradesLabel.setLabelFor(StudentGradesField);
		PathLabel.setLabelFor(PathField);
		//Creates a JPanel pane to hold labels
		JPanel labelPane = new JPanel(new GridLayout(0,1));
		//Adds the Labels to the panel
		labelPane.add(PathLabel);
		labelPane.add(NameLabel);
		labelPane.add(lblGender);
		labelPane.add(AddressLabel);
		labelPane.add(PhoneLabel);
		labelPane.add(StudentIDLabel);
		labelPane.add(StudentGradesLabel);
		labelPane.add(StudentAverageLabel);
		
		//Creates a JPanel pane to hold text fields.
		JPanel fieldPane = new JPanel(new GridLayout(0,1));
		//Adds the text Fields to the panel.
		fieldPane.add(PathField);
		fieldPane.add(NameField);
		fieldPane.add(txtGender);
		fieldPane.add(AddressField);
		fieldPane.add(PhoneField);
		fieldPane.add(StudentIDField);
		fieldPane.add(StudentGradesField);
		fieldPane.add(StudentAverageField);
		
		//Creates the panel for buttons
		JPanel buttonPane = new JPanel(new GridLayout(0,3));
		//Creates a button
		JButton buttonPrev = new JButton("<<");
		//Creates a listener for the button.
		ActionListener listenerP = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("The button was clicked!");
				if(studentIndex>0)
				{
					--studentIndex;
					update();
				}
			}        
		};
		//Adds the listener to the button.
		buttonPrev.addActionListener(listenerP);		

		JButton buttonNext = new JButton(">>");
		ActionListener listenerN = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("The button was clicked!");
				if(studentIndex<sList_0.size()-1)
				{
					++studentIndex;
					update();
				}
			}        
		};
		buttonNext.addActionListener(listenerN);
		//Adds the buttons to the panel.
		buttonPane.add(buttonPrev);
		buttonPane.add(buttonNext);
		
		//Create sand sets the border
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		//adds the label panel to main panel.
		add(labelPane, BorderLayout.CENTER);
		//adds the field panel to main panel.
		add(fieldPane, BorderLayout.LINE_END);
		//adds the button panel to main panel.
		add(buttonPane,BorderLayout.AFTER_LAST_LINE);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		buttonPane.add(btnExit);
		repaint();
		revalidate();
	}

	/**
	 * updates the value of the filed modified
	 */
	public void propertyChange(PropertyChangeEvent e) {
		//Gets the source in which the property has changed. 
		Object source = e.getSource();
		//Updates the student list according to the change.
		if (source == NameField) {
			sList_0.get(studentIndex).setName(new StringTokenizer(NameField.getText()," "));
		} else if (source == AddressField) {
			sList_0.get(studentIndex).setAddress(new StringTokenizer(AddressField.getText()," "));
		} else if (source == PhoneField) {
			StringTokenizer st= new StringTokenizer(PhoneField.getText(),"()-");
			sList_0.get(studentIndex).setPhone(st);
		} else if (source == StudentIDField) {
			sList_0.get(studentIndex).setStudentID(StudentIDField.getText());
		} else if (source == StudentGradesField) {
			StringTokenizer st= new StringTokenizer(StudentGradesField.getText(),"[],");
			sList_0.get(studentIndex).setGrades(st);
			sList_0.get(studentIndex).computeAverage();
		}else if(source == txtGender){
			sList_0.get(studentIndex).setGender(txtGender.getText());
		}else if (source == PathField) {
			FileName= PathField.getText();
			path= f.getPath(FileName);
			//If path is not present writes the student data to file.
			if(!path.toFile().exists())
				writeFile();
			else
			{
				//If new path exists it reads the list from that file.
				readFile();
				studentIndex= 0;
				update();
			}
		}
	}
	
	/**
	 * reads the student csv file from path, parses it and create the list of students from it
	 */
	private void readFile()
	{
		String delims= ",";
		int c;
		char ch;
		String text= "";
		//Creates array list to hold student objects.
		sList_0= new ArrayList<Student>();
		//Opens the fire to read.
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) 
		{
			//reads each character at a time.
			c= reader.read();
			//loops until file completes.
			while (c>=0)
			{
				ch= (char)c;
				if(ch == '\n')
					ch= ',';
				else if(ch == '\r' || ch == '\t')
					ch= ' ';
				text+= ch;
				c= reader.read();
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		//Tokenizes the file data.
		StringTokenizer st= new StringTokenizer(text,",");			
		while (st.hasMoreTokens())
		{
			//Creates a student from each token.
		    sList_0.add(new Student(st));
		    //Prints the last student created.
			sList_0.get(sList_0.size()-1).print();
		}
	}
	
	/**
	 * writes the student list to a file specified in file name field.
	 */
	private void writeFile()
	{
		FileWriter fileWriter = null;
		String str;
		try {
			File file = path.toFile();
			//Creates the file.
			file.createNewFile();
			fileWriter= new FileWriter(file);
			//For each student creates a string and writes to the file.
			for (Student s : sList_0)
			{
				str= s.toString();
				fileWriter.write(str.substring(0,str.length()-1)+"\n");
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally
		{ 
			try{
				if(fileWriter!=null)
					fileWriter.close();
			}catch(Exception ex){
				System.out.println("Error in closing the FileWriter"+ex);
			}
		}
	}
	
	/**
	 * creates the UI to display the student details.
	 * @param p : file path of the student csv
	 */
	private static void createAndShowGUI(String p) {
		//Create and set up the window.
		JFrame frame = new JFrame("StudentUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add contents to the window.
		frame.getContentPane().add(new StudentUI(p));

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * starting point of the execution 
	 * @param args : contains the path of the student csv file
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				//Creates UI admin display
				createAndShowGUI(args[0]);
			}
		});
	}
}
