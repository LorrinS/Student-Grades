/*
 * Description 		   Stores, lists and averages student grades 
 * modified     	   20220414
 * date         	   20220414
 * @filename    	   StudentGrades.java
 * @author      	   Lorrin Shen
 * @version            1.0
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class StudentGrades extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtTest1;
	private JTextField txtTest2;
	private JTextField txtTest3;
	private JTextField txtTest4;
	private int[][] grades = new int[15][4];
	private String[] students = new String[15];
	private int count =0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGrades frame = new StudentGrades();
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
	public StudentGrades() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Student Grades");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBounds(195, 11, 154, 14);
		contentPane.add(lblTitle);

		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setBounds(10, 57, 86, 14);
		contentPane.add(lblFirstName);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(100, 54, 126, 20);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(251, 57, 77, 14);
		contentPane.add(lblLastName);

		txtLastName = new JTextField();
		txtLastName.setBounds(338, 54, 131, 20);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);

		JLabel lblTest1 = new JLabel("Test 1");
		lblTest1.setBounds(10, 109, 46, 14);
		contentPane.add(lblTest1);

		JLabel lblTest2 = new JLabel("Test 2");
		lblTest2.setBounds(10, 134, 46, 14);
		contentPane.add(lblTest2);

		JLabel lblTest3 = new JLabel("Test 3");
		lblTest3.setBounds(10, 159, 46, 14);
		contentPane.add(lblTest3);

		JLabel lblTest4 = new JLabel("Test 4");
		lblTest4.setBounds(10, 184, 46, 14);
		contentPane.add(lblTest4);

		txtTest1 = new JTextField();
		txtTest1.setBounds(51, 106, 53, 20);
		contentPane.add(txtTest1);
		txtTest1.setColumns(10);

		txtTest2 = new JTextField();
		txtTest2.setBounds(51, 131, 53, 20);
		contentPane.add(txtTest2);
		txtTest2.setColumns(10);

		txtTest3 = new JTextField();
		txtTest3.setBounds(51, 159, 53, 20);
		contentPane.add(txtTest3);
		txtTest3.setColumns(10);

		txtTest4 = new JTextField();
		txtTest4.setBounds(51, 184, 53, 20);
		contentPane.add(txtTest4);
		txtTest4.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 232, 247, 218);
		contentPane.add(scrollPane);

		JTextArea txtaOut = new JTextArea();
		txtaOut.setEditable(false);
		scrollPane.setViewportView(txtaOut);

		JLabel lblMessage = new JLabel("");
		lblMessage.setBounds(10, 82, 514, 14);
		contentPane.add(lblMessage);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(221, 105, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstName, lastName, name, a, b, c, d;
				int test1, test2, test3, test4;
				boolean notListed =true;

				lblMessage.setText("");

				firstName = txtFirstName.getText();
				lastName = txtLastName.getText();

				a = (txtTest1.getText()).replaceAll(" ", "");
				b = (txtTest2.getText()).replaceAll(" ", "");
				c = (txtTest3.getText()).replaceAll(" ", "");
				d = (txtTest4.getText()).replaceAll(" ", "");

				if(firstName.isEmpty() || lastName.isEmpty() || a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty()) {
					lblMessage.setText("Invalid input. Please enter a full name and all grades.");
				}
				else if(intCheck(a) && intCheck(b) && intCheck(c) && intCheck(d)) {
					name= firstName.replaceAll(" ","")+" "+lastName.replaceAll(" ", "");
					test1 = Integer.parseInt(a);
					test2 = Integer.parseInt(b);
					test3 = Integer.parseInt(c);
					test4 = Integer.parseInt(d);

					if(gradeCheck(test1) && gradeCheck(test2) && gradeCheck(test3) && gradeCheck(test4)) {
						for (int i=0; i<count; i++) {
							if(name.equals(students[i])) { //if student name is already stored, update their grades
								grades[i][0]=test1;
								grades[i][1]=test2;
								grades[i][2]=test3;
								grades[i][3]=test4;

								notListed=false; 

								lblMessage.setText("Student "+name+"'s test grades have been updated.");
								break;
							}
						}
						if (count>=15) {
							lblMessage.setText("The maximum number of students is 15.");
						}
						else if (notListed) {
							students[count]=name;
							grades[count][0]=test1;
							grades[count][1]=test2;
							grades[count][2]=test3;
							grades[count][3]=test4;

							count++;
							lblMessage.setText("Student "+name+"'s grades have been stored.");
						}
					}
					else {
						lblMessage.setText("Invalid input. Grades should range from 0 to 100.");
					}
				}
				else {
					lblMessage.setText("Invalid input. Grades should be integers.");
				}
			}
		});

		JButton btnList = new JButton("List");
		btnList.setBounds(221, 150, 89, 23);
		contentPane.add(btnList);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblMessage.setText("");
				txtaOut.setText("");
				txtTest1.setText("");
				txtTest2.setText("");
				txtTest3.setText("");
				txtTest4.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");

				for(int i=0; i<count; i++) {
					txtaOut.append(students[i]+": ");
					for(int j=0; j<4; j++) {
						txtaOut.append(grades[i][j]+"% ");
					}
					txtaOut.append("\n");
				}
				if(count==0) {
					lblMessage.setText("There are currently no student grades stored.");
				}
			}
		});

		JButton btnStudentAvg = new JButton("Student Average");
		btnStudentAvg.setBounds(334, 105, 148, 23);
		contentPane.add(btnStudentAvg);
		btnStudentAvg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				String firstName, lastName, name;
				double avg=.0;
				boolean noStudent=true;

				firstName = txtFirstName.getText();
				lastName = txtLastName.getText();
				name = firstName+" "+lastName;

				lblMessage.setText("");
				txtaOut.setText("");
				txtTest1.setText("");
				txtTest2.setText("");
				txtTest3.setText("");
				txtTest4.setText("");

				for (int i=0; i<count; i++) {
					if(name.equals(students[i])) {
						for(int j=0; j<4; j++) {
							avg+=grades[i][j];
						}
						avg/=4;
						txtaOut.append("Student "+name+"'s Average: "+avg+"%");
						noStudent=false;
					}
				}
				if(noStudent) {
					lblMessage.setText("Student is not stored.");
				}
			}
		});

		JButton btnCourseAvg = new JButton("Course Averages");
		btnCourseAvg.setBounds(334, 150, 148, 23);
		contentPane.add(btnCourseAvg);
		btnCourseAvg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double avg=0.0;

				lblMessage.setText("");
				txtaOut.setText("");
				txtTest1.setText("");
				txtTest2.setText("");
				txtTest3.setText("");
				txtTest4.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");

				if(count!=0) {
					for(int i=0; i<4; i++) {
						avg=0.0;
						for(int j=0; j<count; j++) {
							avg+=grades[j][i];
						}
						avg=Math.round((avg*100)/count)/100.0;
						txtaOut.append("Test "+(i+1)+" Average: "+avg+"% \n");
					}
				}
				else {
					lblMessage.setText("No Student grades have been recorded.");
				}
			}
		});

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(287, 201, 89, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});

	}
	static public boolean intCheck(String in){
		int x = 0;
		try {
			x = Integer.parseInt(in);
			return true;
		}
		catch (Exception e ) {
			return false;
		}
	}
	static public boolean gradeCheck(int in) {
		if(in<=100 && in>=0)
			return true;
		return false;
	}
}
