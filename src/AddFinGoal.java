import java.io.*;
import java.util.ArrayList;
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
    String goal = goalName();
    List<String[]> result = readFromFile();
    /// constructor for take the userName
    public AddFinGoal(String name){
        this.userName = name;
    }
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
        scanner.nextLine();
        System.out.println("insert the date in this format : dd-MM-yyyy");
        deadline = scanner.nextLine();
        System.out.println("insert the current progress: ");
        currentProgress = scanner.nextDouble();
    }
    /// Save goal details in file
    @Override
    public void saveToFile(){
        File file = new File("FinGoal");

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
            System.out.println("User name: " + financial[0] +
                    ", goal name: " + financial[1] +
                    ", goal type: " + financial[2] +
                    ", target amount: " + financial[3] +
                    ", deadline: " + financial[4] +
                    ", current progress: " + financial[5] + "\n");
        }
    }
    /// print the progress of each goal
    public void trackProgress(List<String[]> track){
        double currentProgress = 0.0;
        for (String[] progress : track){
            // progress = (current / target) * 100
            double curPro = Double.parseDouble(progress[5]);
            double targetPro = Double.parseDouble(progress[3]);
            currentProgress = (curPro/targetPro) * 100;
            System.out.println("The progress for " + progress[0] + " is: " + String.format("%.2f",currentProgress) + "%" );
        }
    }
    /// implementation of add financial goal
    public void implementAddGoal(){

        while (true){
            System.out.println("choose: \n");
            System.out.println("1 - Add new goal.\n");
            System.out.println("2 - View list of goals.\n");
            System.out.println("3 - Track your progress.\n");
            String option = scanner.nextLine();
            if (option.equals("1")){
                getDetails();
                saveToFile();
                break;
            }else if (option.equals("2")){
                printListGoal(readFromFile());
                break;
            }else if (option.equals("3")){
                trackProgress(readFromFile());
                break;
            }else {
                System.out.println("please insert only 1, 2 or 3");
            }
        }



    }


}
