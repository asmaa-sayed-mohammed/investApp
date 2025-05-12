import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * The {@code verifyFinGoal} class provides validation methods for
 * financial goal attributes such as goal type, target amount,
 * current progress, and deadline date.
 * <p>
 * This class is used to ensure data integrity before storing user-defined
 * financial goals.
 */
public class verifyFinGoal {
    /**
     * Validates the goal type.
     * The type must be a non-empty string with less than 50 characters.
     *
     * @param type the goal type entered by the user
     * @return {@code true} if valid, {@code false} otherwise
     */
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
    /**
     * Validates the target amount.
     * The amount must be greater than 0.
     *
     * @param target the target financial amount
     * @return {@code true} if the target is valid, {@code false} otherwise
     */
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
    /**
     * Validates the current progress.
     * Progress must be greater than or equal to 0.
     *
     * @param current the current progress value
     * @return {@code true} if valid, {@code false} otherwise
     */
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
    /**
     * Validates the deadline date.
     * The date must be in the format {@code dd-MM-yyyy} and must be in the future.
     *
     * @param deadline the deadline date as a string
     * @return {@code true} if the deadline is valid and in the future, {@code false} otherwise
     */
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
