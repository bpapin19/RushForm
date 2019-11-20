import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.text.Document;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Rushee extends JFrame implements ActionListener {
	
	static JButton submit = new JButton("Submit");
	
	JPanel mainPanel = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel bottomPanel = new JPanel();


	static JTextField nameIn = new JTextField(8);
	static JTextField numberIn = new JTextField(8);
	static JTextField emailIn = new JTextField(8);
	static JTextField cwidIn = new JTextField(8);
	static JTextField textArray[] = {nameIn, numberIn, emailIn, cwidIn};
	
	JLabel title = new JLabel("Phi Kappa Tau Fall Rush 2019");
	JLabel nameOut = new JLabel("Name: ");
	JLabel yearOut = new JLabel("Year: ");
	JLabel numberOut = new JLabel("Phone Number: ");
	JLabel emailOut = new JLabel("Email: ");
	JLabel cwidOut = new JLabel("CWID: ");

	String[] comboArray = {"Freshman", "Sophomore", "Junior", "Senior"};
    JComboBox comboSize = new JComboBox(comboArray);
    
    static int count = 0;

	public static String[] events = {"Wednesday: Dodgeball", "Thursday: Football", "Friday: Car Destruction", "Monday: Driving Range", "Tuesday: Tacos", "Wednesday: Chill by the Grill", "Thursday: Bid Dinner", "End of Rush"};
	
	static JLabel day;

	static JLabel picture = new JLabel(); //Attach picture to JFrame
	
	private static String name;
	private static String year;
	private static long cwid;
	private static String number;
	private static String email;
		
	public Rushee() {
		super("Rush Form");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title.setFont(new Font("Gautami", Font.BOLD, 16));
		
		setLayout(new BorderLayout());
		
		add (mainPanel, BorderLayout.CENTER);
		add (topPanel, BorderLayout.NORTH);
		add (bottomPanel, BorderLayout.SOUTH);
		
		//************ Add image to JFrame ***********
		ImageIcon imgThisImage = new ImageIcon("/Users/brandonpapin/Downloads/barn.jpeg");
		Image transformed = imgThisImage.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		picture.setIcon(new ImageIcon(transformed));
		bottomPanel.add(picture);
		
		//************ Create document object ***********
		Document document = cwidIn.getDocument();
	    document.addDocumentListener(new JButtonController(submit));
	 
		mainPanel.setLayout(new GridLayout(6,2,2,2));
		topPanel.setLayout(new GridLayout(2,1,2,2));
		
		if ((getDateTime() == "11-19")) {
			day = new JLabel(events[0]);
		} else if (getDateTime() == "11-21") {
			day = new JLabel(events[1]);
		} else if (getDateTime() == "11-22") {
			day = new JLabel(events[2]);
		} else if (getDateTime() == "11-25") {
			day = new JLabel(events[3]);
		} else if (getDateTime() == "11-26") {
			day = new JLabel(events[4]);
		} else if (getDateTime() == "11-27") {
			day = new JLabel(events[5]);
		} else if (getDateTime() == "11-28") {
			day = new JLabel(events[6]);
		} else {
			day = new JLabel(events[7]);
		}
		
		topPanel.add(title);
		topPanel.add(day);
		day.setFont(new Font("Gautami", Font.BOLD, 14));
		mainPanel.add(nameOut);
		mainPanel.add(nameIn);
		mainPanel.add(numberOut);
		mainPanel.add(numberIn);
		mainPanel.add(emailOut);
		mainPanel.add(emailIn);
		mainPanel.add(cwidOut);
		mainPanel.add(cwidIn);
		
		mainPanel.add(yearOut);
		mainPanel.add(comboSize);
		
		submit.setEnabled(false);
		mainPanel.add(submit);
		
		submit.addActionListener(this);
		}
	
	   //****** Create initial file and write first entry *************
	   public static void fileWrite(String name, String number, String email, long cwid, String year) {
	      try{
	    // Create file 
	    FileWriter fstream;
	    
	    fstream = new FileWriter("Initial.txt");
	    BufferedWriter out = new BufferedWriter(fstream);
		day.setFont(new Font("Gautami", Font.BOLD, 20));
	    out.write(day.getText());
	    out.write("\n");
	    out.write("\nName: " + name);
	    out.write("\nPhone Number: " + number);
	    out.write("\nEmail: " + email);
	    out.write("\nCWID: " + cwid);
	    out.write("\nYear: " + year);
	    out.write("\n");
	    out.write("\n");

	    //Close the output stream
	    out.close();
	    }catch (Exception e){//Catch exception if any
	      System.err.println("Error: " + e.getMessage());
	    }
	  }
	   
	   //****** Open existing file and write other entries on it *************
	   private static void appendFile(String filePath, String name, String number, String email, long cwid, String year) {
			File file = new File(filePath);
			FileWriter fr = null;
			try {
				// Below constructor argument decides whether to append or override
				fr = new FileWriter(file, true);
			    fr.write("\nName: " + name);
			    fr.write("\nPhone Number: " + number);
			    fr.write("\nEmail: " + email);
			    fr.write("\nCWID: " + cwid);
			    fr.write("\nYear: " + year);

			    fr.write("\n");
			    fr.write("\n");

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	   
	   		
	@Override
    public void actionPerformed(ActionEvent ae) {
	 
	 Object source = ae.getSource();
	 	 
	 if (source == submit) {
		count ++;
		String filepath = "/Users/brandonpapin/eclipse-workspace/FinalProject/Initial.txt";
		name = nameIn.getText();
		number = numberIn.getText();
		email = emailIn.getText();
		cwid = Integer.parseInt(cwidIn.getText());
		
		nameIn.setText("");
		numberIn.setText("");
		emailIn.setText("");
		cwidIn.setText("");
		
		//********** JComboBox needs to be included in ActionListener to set variable 'year'
		String s = (String) comboSize.getSelectedItem();//get the selected item

        switch (s) {//check for a match
            case "Freshman":
                year = "Freshman";
                break;
            case "Sophomore":
                year = "Sophomore";
                break;
            case "Junior":
                year = "Junior";
                break;
            case "Senior":
                year = "Senior";
                break;
            default:
            	break;
        }
		
		if (count == 1) {
			fileWrite(name, number, email, cwid, year);
		} else {
			appendFile(filepath, name, number, email, cwid, year);
		}
	 }
	}
	
	private String getDateTime() {
        String dateFormat = new SimpleDateFormat("MM-dd").format(new Date());
        return dateFormat;
    }
	
	public static void main (String[] args) {
		Rushee frame = new Rushee();
		frame.setVisible(true);
		}
}
