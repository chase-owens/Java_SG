
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class View {
    UserIO io = new UserIOImplementation();

    public int getMenuSelection() {
        io.print("Choose your option by entering the number associated with you option.");
        String displayMessage = "1) Add student \t 2) Remove student \t 3) View a student's scores \n 4) Get student average \t 5) Display students \t 6) Quit";
        return io.readInt(displayMessage, 1, 6);
    }

    public void exitProgram() {
        io.print("You are such a good teacher. Have a nice day.");
    }

    public String getFirstName() {
        return io.readString("What is student's first name");
    }

    public String getLastName() {
        return io.readString("What is student's last name");
    }

    public int getGrade() {
        return io.readInt("Please enter grade"); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int[] setGrades() {
        int[] grades = new int[3];
        io.print("Quiz 1");
        grades[0] = getGrade();
        io.print("Quiz 2");
        grades[1] = getGrade();
        io.print("Quiz 3");
        grades[2] = getGrade();
        
        return grades;
    }

    public void printScores(ArrayList<Integer> grades) {
        for (int grade : grades) {
            io.print(Integer.toString(grade));
        }
    }

    void printAverage(int average) {
        io.print(Integer.toString(average));
    }

    
    
    
}
