package unfosprey;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class osprey {
	
	/*TO DO LIST*/
	/*Kyle - workning on adding course, adding and generating grades*/
	/*Atahan - working on adding student department */
	/*Kristina - */
	/*Fionia - find offered courses and courses teacher has taught*/
	
	
	//addcourse is finished and working
	//addStudent is FInsihed and working
	//addSection is finished and working
	
	
	public static boolean insertStudent = true;
	public static boolean insertDepartment = false;
	
	public static int totalHoursforCourse = 0;
	public static int hoursForCourse = 0;
	
	public static void main(String[] args) throws SQLException{
		String uid = "G04";		
		String pword = "wYzd2Bdj";
		String url ="jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		Connection conn =
				DriverManager.getConnection(url, uid, pword);
		
		boolean done = false;
		
		while(done == false) {
			
		System.out.println("Please make a selection: ");
		System.out.println("1) Add a student");			//Done
		System.out.println("2) Add a deparment");		//Kyle
		System.out.println("3) Add a course");			//Done
		System.out.println("4) Add a section");			//Done
		System.out.println("5) Add an Instructor to section"); //Done		
		System.out.println("6) Generate grade report");
		System.out.println("7) Find offered courses"); // Done
		System.out.println("8) List all the courses an instructor have taught "); 
		System.out.println("9) Add a grade to a student's course");			//Troubleshoot
		System.out.println("10) Add instructor"); // Done
		System.out.println("0) Quit Program "); // Done
		System.out.println();
		
		
		
		Scanner option = new Scanner (System.in);
		String options = option.next();
		
		
		
		switch(options) {
		case "1":
			insertStudent = true;
			insertDepartment = false;
			addStudent(conn);
			break;
		case "2":
			addDepartment(conn);
			break;
			
		case "3"://adding a course
			addCourse(conn);
			break;
			
		case "4":
			addSection(conn);
			break;
			
		case "5":
			addInstructortoSection(conn, options, options, options, options, options);
			break;
			
		case "6": 
			genGradeReport(conn);
			break;
			
		case "7":
			offeredCourses(conn);
			break;
			
		case "8":
			getCoursebyInstructor(conn);
			break;
			
		case "9":
			addGrade(conn);
			break;
			
		case "10":
			addInstructor(conn, options, options, options, options, options, options, options, options, options);
			break;
			
		case "0":
			System.out.println("The Program has Ended. Thank you.");
			done = true;
			option.close();
			
		
		}  
	}
		conn.close();
	}//end of main
	
	
	public static void addStudent(Connection conn) throws SQLException{
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO STUDENT (N_Number, First_Name, Middle_Initial, Last_Name, Ssn, DoB, Degree_Program, Student_Class, Sex, Perm_Street_Name, Perm_City, Perm_State, Perm_Zip_Code, Perm_Phone, Curr_Phone, Curr_Street_Name, Curr_City, Curr_State, Curr_Zip_Code)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); // insert SQL command
		
		int finished = 1; // initiates done as 1 so the program will keep working
		String nNumberDig = "^[nN]\\d{8}$"; // sets the N_Number pattern as N########
		String ssnDigCount = "^\\d{9}$"; // sets the SSN pattern as #########
		String nNumber = "";
		String ssn ="";
		boolean validID = false;
		
		while (finished !=0) 
			{
			PreparedStatement preparedStatement = conn.prepareStatement ("INSERT INTO STUDENT(N_NUMBER, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, SSN, DOB, DEGREE_PROGRAM, STUDENT_CLASS, SEX, PERM_STREET_NAME, PERM_CITY, PERM_STATE, PERM_ZIP_CODE, PERM_PHONE, CURR_PHONE, CURR_STREET_NAME, CURR_CITY, CURR_STATE, CURR_ZIP_CODE) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			
			System.out.println("Please enter the Student N-Number: ");
			String studentID = getString();
			if(studentID.matches(nNumberDig))
			{
				System.out.println("You entered: " + studentID);
				validID = true;
			}
			else
			{
				System.out.println("The student ID is not valid, Please try again.");
			}
			
			if(validID)
			{
				System.out.println("Please enter the Student SSN: ");
				ssn = getString();
				boolean valid_ssn = false;
				if(ssn.matches(ssnDigCount))
				{
					System.out.println("You have entered a valid SSN Number");
					valid_ssn = true;
				}
				else
				{
					System.out.println("You have entered an invalid SSN. Please try again.");
				}
				if(valid_ssn)
				{
					System.out.println("Please enter the Students First Name: ");
					String firstName = getString();
					
					System.out.println("Please enter the Student's Middle Initial: ");
					String m_initial = getString();
					
					System.out.println("Please enter the Students Last Name: ");
					String lastName = getString();
					
//					System.out.println("Please enter the Student'd Birth Month: ");
//					String birthMonth = getString();
//					
//					System.out.println("Please enter the Sudents Birth Year: ");
//					String birthYear = getString();
					
					System.out.println("Please enter the Student's BirthDay(YYYY-MM-DD): ");
					String birthDay = getString();
					
					System.out.println("Please enter the Student's Degree Program: ");
					String degreeProgram = getString();
					
					System.out.println("Please enter the Student's Class: ");
					String studentClass = getString();
					
					System.out.println("Please enter the Student's Sex: ");
					String studentSex = getString();
					
					System.out.println("Please enter the Student's Perm Street Address: ");
					String permStreet = getString();
					
					System.out.println("Please enter the Student's Perm City: ");
					String permCity = getString();
					
					System.out.println("Please enter the Student's Perm State: ");
					String permState = getString();
					
					System.out.println("Please enter the Student's Perm Zip Code: ");
					String permZip = getString();
					
					System.out.println("Please enter the Student's Perm Phone Number: ");
					String permPhone = getString();
					
					System.out.println("Please enter the Student's Current Phone Number: ");
					String currPhone = getString();
					
					System.out.println("Please enter the Student's Current Address: ");
					String currStreet = getString();
					
					System.out.println("Please enter the Students Current City: ");
					String currCity = getString();
					
					System.out.println("Please enter the Student's Current State: ");
					String currState = getString();
					
					System.out.println("Please enter the Student's Current Zip Code: ");
					String currZip = getString();
					
					
					//"INSERT INTO STUDENT(N_NUMBER, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, SSN, DOB, DEGREE_PROGRAM, STUDENT_CLASS, SEX, 
					//PERM_STREET_NAME, PERM_CITY, PERM_STATE, PERM_ZIP_CODE, PERM_PHONE, CURR_PHONE, CURR_STREET_NAME, CURR_CITY, CURR_STATE, CURR_ZIP_CODE) "
					
//					String dob = birthYear + "-" + birthMonth + "-" + birthDay;
					
//					System.out.println(dob);
					
//					SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
//					Date date = (Date) dateInput.parse(birthDay);
					//Date date = birthDay;
					
					preparedStatement.setString(1, studentID);
					preparedStatement.setString(2, firstName);
					preparedStatement.setString(3, m_initial);
					preparedStatement.setString(4, lastName);
					preparedStatement.setString(5, ssn);
					preparedStatement.setString(6, birthDay);
					preparedStatement.setString(7, degreeProgram);
					preparedStatement.setString(8, studentClass);
					preparedStatement.setString(9, studentSex);
					preparedStatement.setString(10, permStreet);
					preparedStatement.setString(11, permCity);
					preparedStatement.setString(12, permState);
					preparedStatement.setString(13, permZip);
					preparedStatement.setString(14, permPhone);
					preparedStatement.setString(15, currPhone);
					preparedStatement.setString(16, currStreet);
					preparedStatement.setString(17, currCity);
					preparedStatement.setString(18, currState);
					preparedStatement.setString(19, currZip);
					
					
					int NumRows = preparedStatement.executeUpdate();
					System.out.println("\n" + NumRows + " row(s) inserted");
					
					System.out.println("\nTo exit add course, enter 0, to continue, enter 1 ");
					finished = getInt();
					
					}//end of ssn check
				}//end of ID check
			
			}//end of while
		
		}//end of add student
	
	
	public static void addDepartment(Connection conn) throws SQLException
	{
		int finished = 1;
		while(finished !=0)
		{
			PreparedStatement preparedStatement =
					conn.prepareStatement ("INSERT INTO DEPARTMENT(DEPT_CODE, DEPT_NAME, DEPT_OFFICENUMBER, DEPT_PHONENUMBER, DEPT_COLLEGE) " +
							"VALUES (?, ?, ?, ?, ?)");
			
			System.out.println("Please enter the Department Code: ");
			String deptCode = getString();
			
			System.out.println("Please enter the Department Name: ");
			String deptName = getString();
			
			System.out.println("Please enter the Department Office Number: ");
			String deptOfficeNum = getString();
			
			System.out.println("Please enter the Department Phone Number: ");
			String deptPhone = getString();
			
			System.out.println("Please enter the Department College: ");
			String deptCollege = getString();
			
			preparedStatement.setString(1,  deptCode);
			preparedStatement.setString(2,  deptName);
			preparedStatement.setString(3,  deptOfficeNum);
			preparedStatement.setString(4, deptPhone);
			preparedStatement.setString(5, deptCollege);
			
			int NumRows = preparedStatement.executeUpdate();
			System.out.println("\n" + NumRows + " row(s) inserted");
			
			System.out.println("If you would like to add another department, Please press 1. If you are finished, Please press 0 to close addDepartment.");
			finished = getInt();
		}
	}
	
	
	
	
	
	
	public static void addCourse(Connection conn) throws SQLException
	{
		PreparedStatement preparedStatement =
				conn.prepareStatement ("INSERT INTO COURSE(course_number, course_name, course_description, course_level, hours, dept_code) " +
						"VALUES (?, ?, ?, ?, ?, ?)");
			
		
		int finished = 1;
		
		String courseNumber = "";
		String courseName = "";
		String courseDescription = "";
		String courseLevel = "";
		String courseHours = "";
		String courseDeptCode = "";
		
		
		while(finished != 0)
		{
			
			System.out.println("Please enter the Course Number");
			courseNumber = getString();
			System.out.println("You entered " + courseNumber + "for the course number");
			//preparedStatement.setString(1,  courseNumber);
			
			System.out.println("Please enter the Course Name");
			courseName = getString();
			System.out.println("You entered " + courseName + "for the course name");
//			preparedStatement.setString(2,  courseName);
			
			System.out.println("Please enter a Description of the course");
			courseDescription = getString();
			System.out.println("You entered " + courseDescription + "for the course Description");
//			preparedStatement.setString(3,  courseDescription);
			
			System.out.println("Please enter the Course Level");
			courseLevel = getString();
			System.out.println("You entered " + courseLevel + "for the course Level");
//			preparedStatement.setString(4,  courseLevel);
			
			System.out.println("Please enter the course hours: ");
			courseHours = getString();
			System.out.println("You entered " + courseHours + "for the course Hours");
//			preparedStatement.setString(5,  courseHours);
			
			System.out.println("Please enter the Course Department Code: ");
			courseDeptCode = getString();
			System.out.println("You entered " + courseDeptCode + "for the course Department Code");
//			preparedStatement.setString(6,  courseDeptCode);
			
			//System.out.println("test1");
			preparedStatement.setString(1, courseNumber);
			preparedStatement.setString(2,  courseName);
			preparedStatement.setString(3,  courseDescription);
			preparedStatement.setString(4,  courseLevel);
			preparedStatement.setString(5,  courseHours);
			preparedStatement.setString(6,  courseDeptCode);
			//System.out.println("test2");
			int NumRows = preparedStatement.executeUpdate();
			System.out.println("\n" + NumRows + " row(s) inserted");
			//preparedStatement.executeUpdate();
			//System.out.println("test3");
			System.out.println("\nTo exit add course, enter 0, to continue, enter 1 ");
			finished = getInt();
		}//end of while loop
	}
	
	
	
	public static void addSection(Connection conn) throws SQLException
	{
		PreparedStatement preparedStatement =
				conn.prepareStatement ("INSERT INTO SECTION(SECTION_NUMBER, SECTION_YEAR, SECTION_SEMESTER, SECTION_COURSENUMBER) " +
						"VALUES (?, ?, ?, ?)");
		
		int finished = 1;
		while(finished == 1)
		{
			System.out.println("Please enter the Section Number: ");
			String sectionNumber = getString();
			System.out.println("You entered: " + sectionNumber);
			
			System.out.println("Please enter the Section Year: ");
			String sectionYear = getString();
			System.out.println("You entered: " + sectionYear);
			
			System.out.println("Please enter the Section Semester: ");
			String sectionSemester = getString();
			System.out.println("You entered: " + sectionSemester);
			
			System.out.println("Please enter the Section Course Number: ");
			String sectionCN = getString();
			System.out.println("You entered: " + sectionCN);
			
			preparedStatement.setString(1 , sectionNumber);
			preparedStatement.setString(2 , sectionYear);
			preparedStatement.setString(3 , sectionSemester);
			preparedStatement.setString(4 , sectionCN);
			
			int NumRows = preparedStatement.executeUpdate();
			System.out.println("\n" + NumRows + " row(s) inserted");
			
			System.out.println("If you are finished, please press 0 to quit. If not, Please press 1 to conitnue");
			finished = getInt();
		}
	}
	
	
	
	
	public static void genGradeReport(Connection conn) throws SQLException
	{
		int finished = 1;
		String studentRegex = "^[nN]\\d{8}$";
		boolean validID = false;
		Statement s  = conn.createStatement();
		
		
		while(finished == 1)
		{
			System.out.println("Plese enter the Student ID number: ");
			String studentID = getString();
			
			
			if(studentID.matches(studentRegex))
			{
			
			System.out.println("you have entered a valid ID number");
			validID = true;
			}
			else
			{
				System.out.println("You have entered an invalid student ID number. Please enter an ID that starts with N and is followed by 8 numbers");
			}
			
			
			if(validID)
			{
				//System.out.println("entered");
				
				
				String q = "SELECT FIRST_NAME, MIDDLE_INITIAL, LAST_NAME " +  
						"FROM STUDENT  " + 
						"WHERE N_NUMBER = " +  "'" + studentID + "'";
				
				ResultSet r = s.executeQuery(q);
				//r.first();
				//System.out.println(r.next());
				
				if(r.next());
				{
				String first_name = r.getString("FIRST_NAME");
				String last_name = r.getString("LAST_NAME");
				String middle_initial = r.getString("MIDDLE_INITIAL");
				System.out.println("Student name: " + first_name + " " + middle_initial +  " " + last_name);
				}
				
				
				gradeReportSection(conn, studentID);
				
//				if(r.next())
//				{
//					System.out.println("entered");
//					String first_name = r.getString("FIRST_NAME");
//					String last_name = r.getString("LAST_NAME");
//					String middle_initial = r.getString("MIDDLE_INITIAL");
//					//String course_number = r.getString("COURSE_NUMBER");
//					//String grade = r.getString("GRADE");
//					
//					
//					System.out.println("Student name: " + first_name + middle_initial + last_name);
//					//System.out.println("Course : " + course_number);
//					//System.out.println("Grade Received: " + grade);
//				}
				r.close();
				//s.close();
			}
			
			
			System.out.println("Please enter 0 to exit Grade Report Generator, or press 1 to continue");
			finished = getInt();
			

//				}
			}
			
			
			
			
		}
	public static void gradeReportSection(Connection conn, String studentID) throws SQLException
	{
		
		
		Statement s  = conn.createStatement();
		String q = "SELECT COURSE_NUMBER, E_SECTION_NUMBER, E_SECTION_YEAR, E_SECTION_SEMESTER, GRADE " +  
				"FROM ENROLL  " + 
				"WHERE N_NUMBER = " +  "'" + studentID + "'";
		
		ResultSet r = s.executeQuery(q);
		boolean b = true;
		float totalPoints = 0;
		float totalHours = 0;
		while(b)
		{
		if(r.next ())
		{
		String courseNum = r.getString("COURSE_NUMBER");
		String sectionNum = r.getString("E_SECTION_NUMBER");
		String sectionSemester = r.getString("E_SECTION_SEMESTER");
		String sectionYear = r.getString("E_SECTION_YEAR");
		String grade = r.getString("GRADE");
		System.out.println(courseNum + " " + sectionNum + " " + sectionSemester + " " + sectionYear + " " + grade);
		getHours(conn, courseNum, totalHours);
		//System.out.println("The total hours test is: " + totalHours);
		
		if(grade.equalsIgnoreCase("a"))
		{
			totalPoints += (4.0 * hoursForCourse);
		}
		else if(grade.equalsIgnoreCase("a-"))
		{
			totalPoints += (3.7 * hoursForCourse);
		}
		else if(grade.equalsIgnoreCase("b+"))
		{
			totalPoints += (3.3 * hoursForCourse);
		}
		else if(grade.equalsIgnoreCase("b"))
		{
			totalPoints += (3.0 * hoursForCourse);
		}
		else if(grade.equalsIgnoreCase("b-"))
		{
			totalPoints += (2.7 * hoursForCourse);
		}
		else if(grade.equalsIgnoreCase("c"))
		{
			totalPoints += (2.0 * hoursForCourse);
		}
		else if(grade.equalsIgnoreCase("c+"))
		{
			totalPoints += (2.3 * hoursForCourse);
		}
		else if(grade.equalsIgnoreCase("d"))
		{
			totalPoints += (1.0* hoursForCourse);
		}
		
		else if(grade.equalsIgnoreCase("f"))
		{
			totalPoints += 0.0;
		}
		else
		{
			System.out.println("No Information. Try Again");
		}
		
		}//end of r.next if
		else
		{
			b = false;
		}
		
		}//end of while
		gpaCalculation(totalPoints, totalHoursforCourse);
		//r.close();
		//s.close();
	}
	
public static void gpaCalculation(float totalPoints, int totalHourseforCourse)
{
	float gpaResult = totalPoints / totalHoursforCourse;
	
	System.out.println("The students GPA is: ");
	System.out.println(String.format("%.2f", gpaResult));
}

public static void getHours(Connection conn, String course, float totalHours) throws SQLException
{
	Statement s  = conn.createStatement();
	String q = "SELECT HOURS " +  
			"FROM COURSE  " + 
			"WHERE COURSE_NUMBER = " +  "'" + course + "'";
	ResultSet r = s.executeQuery(q);
	if(r.next())
	{
	int hours = r.getInt("HOURS");
	hoursForCourse = hours;  
	//System.out.println(hours + " is the number of hours");
	totalHoursforCourse += hours;
	//System.out.println(totalHoursforCourse);
	
	}
}
	

	
	
	
	
	public static void addGrade(Connection conn) throws SQLException
	{
		PreparedStatement preparedStatement = conn.prepareStatement("Insert into ENROLL(N_NUMBER, COURSE_NUMBER, E_SECTION_NUMBER, E_SECTION_YEAR, E_SECTION_SEMESTER, GRADE)" +
				"VALUES(?, ?, ?, ?, ?, ?)");
		int finished = 1;
		while(finished == 1)
		{
			System.out.println("Please enter the Student's Student ID number: ");
			String studentID = getString();
			System.out.println("You entered: " + studentID);
			
			System.out.println("Please enter the Course Number of the class: ");
			String courseNum = getString();
			System.out.println("You entered: " + courseNum);
			
			System.out.println("Please enter the Section Number of the Course: ");
			String sectionNum = getString();
			System.out.println("You entered: " + sectionNum);
			
			System.out.println("Please enter the Year: ");
			String year = getString();
			System.out.println("You entered: " + year);
			
			System.out.println("Please enter the semester: ");
			String semester = getString();
			System.out.println("You entered: "+ semester);
			
			System.out.println("Please enter the letter grade: ");
			String grade = getString();
			System.out.println("You entered: " + grade);
			
			preparedStatement.setString(1, studentID);
			preparedStatement.setString(2, courseNum);
			preparedStatement.setString(3, sectionNum);
			preparedStatement.setString(4, year);
			preparedStatement.setString(5, semester);
			preparedStatement.setString(6, grade);
			
			int NumRows = preparedStatement.executeUpdate();
			System.out.println("\n" + NumRows + " row(s) inserted");
			
			System.out.println("If you are finished entering grades, please press 0 to exit. If not, Please press 1 to contine");
			finished = getInt();
			
			
		}//end of while loop
	
		
	}//END OF ADDGRADE METHOD
	
	
	
	public static String getString() {
		try {
		    StringBuffer buffer = new StringBuffer();
		    int input = System.in.read();
		    while (input != '\n' && input != -1) {
			  buffer.append((char)input);
			  input = System.in.read();
		    }
		    return buffer.toString().trim();
		}
		catch (IOException e){return "";}
	    }

	  public static int getInt() 

	  {
	      String intInput= getString();
	      return Integer.parseInt(intInput);
	  }
	  
	  
	  
	  public static void offeredCourses(Connection conn) {
		    Scanner scanner = new Scanner(System.in);

		    System.out.println("Enter the department name or code:");
		    String departmentInput = scanner.nextLine();

		    try {
		        // Prepare the statement
		        String findCoursesQuery = "SELECT * FROM course WHERE dept_code = ?";
		        PreparedStatement findCoursesStatement = conn.prepareStatement(findCoursesQuery);

		        // Set the department parameter
		        findCoursesStatement.setString(1, departmentInput);

		        // Execute the query
		        ResultSet coursesResult = findCoursesStatement.executeQuery();

		        if (coursesResult.next()) {
		            // Print the courses
		            System.out.println("Offered courses in the department:");
		            do {
		                String courseCode = coursesResult.getString("dept_code");
		                String courseName = coursesResult.getString("course_name");
		                System.out.println(courseCode + " - " + courseName);
		            } while (coursesResult.next());
		        } else {
		            System.out.println("No courses found for the department.");
		        }

		        // Close the statement
		        findCoursesStatement.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		public static void addInstructor(Connection conn, String instructorNum, String instructorSsn, String firstName, String lastName, String officeNumber, String address, String age, String phoneNumber, String deptCode) throws SQLException{		
			
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO INSTRUCTOR(instructor_num, fname, lname, ssn, office_num, address, age, phone_num, dept_code)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"); // get the insertion values from the user
			
			String nNumberDig = "^[nN]\\d{8}$"; // sets the N_Number pattern as N########
			String ssnDigCount = "^\\d{9}$"; // sets the SSN pattern as #########
			boolean validID = false;
			boolean valid_ssn = false;
			int finished = 1;			

			while (finished == 1) { // initiate the while loop
			    System.out.println("Please enter the Instructor N-Number: ");
			    instructorNum = getString();
			    
			    if (instructorNum.matches(nNumberDig)) {
			        System.out.println("You entered: " + instructorNum);
			        validID = true;
			        finished = 0; // Set finished to 0 to exit the loop
			    } else {
			        System.out.println("The instructor ID is not valid, Please try again.");
			    }
			}
				
				
//				try {
//				System.out.println("Please enter the instructor's N-number: ");
//				instructorNNumber = getString(); // load the instructor nnumber
//
//				if (instructorNNumber.matches(nNumberDig)) {
//					System.out.println("You entered: " + instructorNNumber);
//					validID = true;
//				}
//				else { 
//				System.out.println("You entered a invalid N Number, Please Try again");
//				
//				
//				}
//				} catch (IllegalArgumentException e) {
//					System.out.println("Please enter a valid N-number");
				//}
				
				
				
				
				if (validID) {
				System.out.println("Please enter the first name: ");
				firstName = getString(); // load the instructor firstname
				System.out.println("You entered: " + firstName);
				
				System.out.println("Please enter the last name: ");
				lastName = getString(); // load the instructor lastname
				System.out.println("You entered: " + lastName);
				
				System.out.println("Please enter the instructor's SSN: ");
				instructorSsn = getString(); // load the instructorssn
				if (instructorSsn.matches(ssnDigCount)) {
					System.out.println("You entered: " + instructorSsn);
					valid_ssn = true;
				}
				else {
					System.out.println("Please enter a valid SSN");
				}
				
				System.out.println("Please enter the instructor's office number: ");
				officeNumber = getString(); // load the instructor officenumber
				System.out.println("You entered: " + officeNumber);
				
				System.out.println("Please enter the instructor's address: ");
				address = getString(); // load the instructor address
				System.out.println("You entered: " + address);
				
				System.out.println("Please enter the instructor's age: ");
				age = getString(); // load the instructor's age
				System.out.println("You entered: " + age);
				
				System.out.println("Please enter the instructor's phone number: ");
				phoneNumber = getString(); // load the instructor's phone number
				System.out.println("You entered: " + phoneNumber);
				
				System.out.println("Please enter the instructor's department code: ");
				deptCode = getString(); // load the instrucotr's department code
				System.out.println("You entered: " + deptCode);
				}
				
				preparedStatement.setString(1, instructorNum);
				preparedStatement.setString(2, firstName);
				preparedStatement.setString(3, lastName);
				preparedStatement.setString(4, instructorSsn);
				preparedStatement.setString(5, officeNumber);
				preparedStatement.setString(6, address);
				preparedStatement.setString(7, age);
				preparedStatement.setString(8, phoneNumber);
				preparedStatement.setString(9, deptCode);
				
				int NumRows = preparedStatement.executeUpdate();
				System.out.println("\n" + NumRows + "row(s) inserted");
				
				System.out.println("If you are finished inserting instructor's info, please press 0 to exit. If not, Please press 1 to contine");
				finished = getInt();
			} // end of while loop
			
			

			
		// end of addInstructor


		
		public static void addInstructortoSection(Connection conn, String instructorNum, String courseNum, String sectionNum, String sectionYear, String semester) throws SQLException{
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO TEACHES(T_INSTRUCTOR_NUM, T_COURSE_NUM, T_SECTION_NUM, T_SECTION_YEAR, T_SEMESTER)" + "VALUES(?, ?, ?, ?, ?)");
			
			String nNumberDig = "^[nN]\\d{8}$"; // sets the N_Number pattern as N########
			boolean validID = false;
			int finished = 1;
			while(finished == 1) {
			    System.out.println("Please enter the Instructor N-Number: ");
			    instructorNum = getString();
			    
			    if (instructorNum.matches(nNumberDig)) {
			        System.out.println("You entered: " + instructorNum);
			        validID = true;
			        finished = 0; // Set finished to 0 to exit the loop
			    } else {
			        System.out.println("The instructor ID is not valid, Please try again.");
			    }
				
			}
			if (validID) {
				System.out.println("Please enter the course number: ");
				courseNum = getString();
				System.out.println("You entered: " + courseNum);
				
				System.out.println("Please enter the section number: ");
				sectionNum = getString();
				System.out.println("You entered: " + sectionNum);
				
				System.out.println("Please enter section year: ");
				sectionYear = getString();
				System.out.println("You entered: " + sectionYear);
				
				System.out.println("Please enter the semester: ");
				semester = getString();
				System.out.println("You entered: " + semester);
			}
			
			preparedStatement.setString(1, instructorNum);
			preparedStatement.setString(2, courseNum);
			preparedStatement.setString(3, sectionNum);
			preparedStatement.setString(4, sectionYear);
			preparedStatement.setString(5, semester);
			
			int NumRows = preparedStatement.executeUpdate();
			System.out.println("\n" + NumRows + " row(s) inserted");
			
			
		}
		public static void getCoursebyInstructor(Connection conn) throws SQLException {
			

			String tNumberDig = "^[nN]\\d{8}$";
			System.out.println("Enter the teachers N number");
			String teacher = getString();
			boolean teachersNumber = false;
		    while (teachersNumber == false) {  
		    	  

		    	if (teacher.matches(tNumberDig)) {
		    		teachersNumber = true;
		    	}
		    	else {
		    		System.out.println("Teachers ID must start with N followed by 8 digits");
		    		teacher = getString();
		    	}
		     }
			

			String sql= "SELECT Fname,T_COURSE_NUM,T_SECTION_NUM,T_SEMESTER,T_SECTION_YEAR"+
			            " FROM INSTRUCTOR JOIN TEACHES ON INSTRUCTOR_NUM = T_INSTRUCTOR_NUM "+
			            "WHERE INSTRUCTOR_NUM= ?";
			PreparedStatement pstmt = conn.prepareStatement (sql);
			System.out.println(teacher);
			pstmt.setString(1, teacher);
			

		   ResultSet rset = pstmt.executeQuery();
		   //System.out.println("\n");
		      // Iterate through 
		      while (rset.next ()) {
		        String insName = rset.getString("Fname");
		        String sCourse = rset.getString("T_COURSE_NUM");
		        String sNum = rset.getString("T_SECTION_NUM");
		        String sSemester = rset.getString("T_SEMESTER");
		        String syear = rset.getString("T_SECTION_YEAR");
		        System.out.println("Instructors name: "+insName+" Section Course: "+sCourse+" Section number: "+
		        		sNum+" Section semester "+sSemester+" Section year: "+syear);
		      } // while rset
		      rset.close();
		      pstmt.close();
		}
}
	
		
	

