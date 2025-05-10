import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class verifyFinGoal {

    /// The goal type must be < 50 character
    public boolean verifyGoalType(String type){
        int len = type.length();
        if ((len > 50)){
            System.out.println("Too long goal type it must be < 50 character\n");
            return false;
        }else if ((len == 0)){
            System.out.println("Empty goal type\n");
            return false;
        }else {
            System.out.println("Valid goal type\n");
            return true;
        }
    }

    /// The target amount must be > 0

    public boolean verifyTargetAmount(double target){
        if (target <= 0){
            System.out.println("The target amount must be > 0 :(\n");
            return false;
        }else {
            System.out.println("Valid target amount\n");
            System.out.println("I hope to get it soon :) \n");
            return true;
        }
    }

    /// The current progress must be >= 0

    public boolean verifyCurrentProgress(double current){
        if (current < 0){
            System.out.println("The current progress must be >= 0 :(\n");
            return false;
        }else {
            System.out.println("Valid current progress\n");
            return true;
        }
    }

    /// it must be a valid format for date and be in the future

    public boolean verifyDeadline(String deadline){
        String pattern = "dd-MM-yyyy";
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate date  = LocalDate.parse(deadline, formatter);
            LocalDate now = LocalDate.now();

            if (date.isAfter(now)){
                System.out.println("Valid deadline :)");
                return true;
            }else {
                System.out.println("the date must be after today");
                return false;
            }
        }catch (DateTimeParseException e){
            System.out.println("Invalid date format or value");
            return false;
        }
    }
}
