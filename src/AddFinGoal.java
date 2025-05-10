import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AddFinGoal extends verifyFinGoal implements FilesFunction{
    String choice;
    String userName;
    String goalType;
    double targetAmount;
    String deadline;
    double currentProgress;
    Scanner scanner = new Scanner(System.in);
    verifyFinGoal verify = new verifyFinGoal();
/// choose the goal name
    public String goalName(){
        System.out.println("Choose the goal name: \n");
        System.out.println("1 - Retirement.\n");
        System.out.println("2 - Wealth Accumulation.\n");
        choice = scanner.nextLine();
        if (choice.equals("1")){
            return "Retirement";
        }else{
            return "Wealth Accumulation";
        }
    }
    /// insert the details of your goal
    public void getDetails(){
        System.out.println("insert the goal type: ");
        goalType = scanner.nextLine();
        System.out.println("insert the target amount: ");
        targetAmount = scanner.nextDouble();
        System.out.println("insert the date in this format : dd-MM-yyyy");
        deadline = scanner.nextLine();
        System.out.println("insert the current progress: ");
        currentProgress = scanner.nextDouble();
    }
    /// Save goal details in file
    @Override
    public void saveToFile(){
        File file = new File("FinGoal");
        String goal = goalName();
        if (file.exists() && file.canWrite()){
            try (FileWriter writer = new FileWriter(file, true)){
                if (verify.verifyGoalType(goalType) && verify.verifyDeadline(deadline)
                     && verify.verifyCurrentProgress(currentProgress) && verify.verifyTargetAmount(targetAmount)){
                    writer.write(userName + ", ");
                    writer.write(goal + ", ");
                    writer.write(goalType + ", ");
                    writer.write(targetAmount + ", ");
                    writer.write(deadline + ", ");
                    writer.write(currentProgress + "\n");
                    System.out.println("-------------------------------------------------------------------\n");
                    System.out.println("your goal has added successfully");

                }else {
                    System.out.println("Insert a valid Data");
                }
            } catch (IOException e) {
                System.out.println("Failed to save data...");
            }
        }else {
            System.out.println("File doesn't exist...");
        }
    }
/// read financial goal data from file
    @Override
    public List<String[]> readFromFile() {
        String filePath = "FinGoal";
        List<String[]> financialGoals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] goalData = line.split(",");
                if (goalData.length > 1 && goalData[0].trim().equals(userName)) {
                    financialGoals.add(goalData);
                }
            }
        } catch (IOException e) {
            System.out.println("Can't read this file.");
        }
        return financialGoals;
    }

    /// Print goal's data
    public void printListGoal(List<String[]> data){
        for (String[] financial : data){
            System.out.println("User name: " + userName +
                    ", goal name: " + goalName() +
                    ", goal type: " + financial[0] +
                    ", target amount: " + financial[1] +
                    ", deadline: " + financial[2] +
                    ", current progress: " + financial[3] + "\n");
        }
    }


}
