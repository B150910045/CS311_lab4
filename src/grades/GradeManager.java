package grades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/** 
 * A GradeManager will create a command-line prompt that will let someone add grades
 * and display grades in histogram format.
 * 
 */

public class GradeManager {
	
	// Keeps track of the number of each grade this has
	private HashMap<LetterGrade,Integer> allGrades;

	/**
	 * Creates a new GradeManager.
	 */
	public GradeManager() {
		// Create a new HashMap of the grades
		this.allGrades = new HashMap<LetterGrade,Integer>();
		
		// Add in all grades and set the occurance to 0
		for (LetterGrade gl : LetterGrade.values()) {
			allGrades.put(gl, 0);
		}
	}
		
	/**
	 * Adds grade to this GradeManager.
	 * @param grade - grade to add to this grad manager
	 * @throws InvalidGradeException 
	 */
	public void addGrade(int grade) throws InvalidGradeException {
		if (grade <= 100 && grade >= 90) {
			allGrades.replace(LetterGrade.A, allGrades.get(LetterGrade.A) + 1);
		} else if (grade <= 89 && grade >= 80) {
			allGrades.replace(LetterGrade.B, allGrades.get(LetterGrade.B) + 1);
		} else if (grade <= 79 && grade >= 70) {
			allGrades.replace(LetterGrade.C, allGrades.get(LetterGrade.C) + 1);
		} else if (grade <= 69 && grade >= 60) {
			allGrades.replace(LetterGrade.D, allGrades.get(LetterGrade.D) + 1);
		} else if (grade <= 59 && grade >= 0) {
			allGrades.replace(LetterGrade.F, allGrades.get(LetterGrade.F) + 1);
		} else {
			throw new InvalidGradeException("Invalid grade");
		}
	}

	/**
	 * Prints out a histogram of the grades to the console.
	 *
	 */
	public void printHistogram() {
		System.out.println("Grades:");
		System.out.println(getHistString());
	}
	
	/**
	 * Returns a string representation of the histogram of the grades.
	 * @return a string representation of the histogram of the grades.
	 */
	public String getHistString() {
		StringBuffer sb = new StringBuffer();
		for (LetterGrade gl : LetterGrade.values()) {
			sb.append( gl+":");
			for (int i = 0; i < this.allGrades.get(gl); i++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Simple loop that accepts 3 commands from System.in:
	 *    add <some grade> : for example, "add a" or "add b"
	 *                       adds the given grade to the GradeManager
	 *    print            : prints out all the grades in this GradeManager
	 *                       in a histogram format
	 *    exit             : exits the program
	 * @param args
	 * @throws InvalidGradeException 
	 */
	public static void main(String[]  args) throws InvalidGradeException {
		GradeManager gm = new GradeManager();
		
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Starting the grade manager");
		
		while (true) {
			try {
				String input = cin.readLine();
				String[] split = input.split(" ");
				if (input.startsWith("add")) {
					gm.addGrade(Integer.parseInt(split[1]));
				} else if (input.equals("print")) {
					gm.printHistogram();
				}  else if (input.equals("exit")) {
					break;
				} else {
					System.out.println("wrong command");
				}
			}catch(InvalidGradeException | IOException e) {e.printStackTrace();}
		}
	}

}