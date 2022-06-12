import java.util.Scanner;
import java.io.*;

public class RockPaperScissors
{
   public static int wins;
   public static int totalGames;

   //Main method that plays the game
   public static void main(String [] args)throws IOException //main method
   {
      playGame();
   }

   //Method with all the code needed to play the RPS game and store user data
   public static void playGame()throws IOException
   {
      System.out.println("Starting Stats");
      readDataFromFile();//reads the previous data in from your last game session

      // loop to continue playing till user enters "quit"
      do{
         String computer = getComputersChoice();
         String user = getUserChoice();
         determineWinner(user,computer);
         if(user.equalsIgnoreCase("Stats")){
            writeDataToFile();//method that writes to file 
            readDataFromFile();//method that then reads in new stats displaying these to user
         }
         if(user.equalsIgnoreCase("Quit")){
           writeDataToFile();
            break;
         }      
      }while(true);
      System.out.println("Thanks for playing the rock paper scissors mini game, see you again soon!!");//thanks user for playing and encourages them to come back upon quit   
   }
   public static String getComputersChoice()//method used to get the computer's input
   {
      //computers choice is always randomized to 1 of the 3 choices using Math and if statements
      double computerRandom = Math.floor(Math.random()*3+1);
      String computerHand = null;
      if ( computerRandom == 1){
         computerHand = "Rock";
         }
      if (computerRandom ==2 ){
         computerHand = "Paper";
         }
      if(computerRandom == 3){
         computerHand = "Scissors";
         }
      return computerHand;
   }
   public static String getUserChoice()//method used to get the users input
   {
     Scanner keyboard = new Scanner (System.in);
     String userHand = null;
     do{
         System.out.print("Choose between Rock, Paper,or Scissors to play; Stats to view your stats, or Quit to exit: ");
         userHand = keyboard.next();
     }while(!(isValid(userHand)));
     return userHand;
   }
   public static boolean isValid(String userChoice)//method used to make sure user inputs valid entry
   {
      boolean validChoice;
      //if else statements to validate the users entry 
      if(userChoice.equalsIgnoreCase("Rock")){
         validChoice = true;
      }
      else if(userChoice.equalsIgnoreCase("Paper")){
         validChoice = true;
      }
      else if(userChoice.equalsIgnoreCase("Scissors")){
         validChoice = true;
      }
      else if(userChoice.equalsIgnoreCase("Quit")){
         validChoice = true;
      }
      else if(userChoice.equalsIgnoreCase("Stats")){
         validChoice = true;
      }
      else{
         validChoice = false;
         System.out.println("Try again, please note that spelling must be identical to one of the choices.");
      }
      return validChoice;
      
   }
   public static void determineWinner(String userHand, String computerHand)throws IOException//method to pick a winner
   {
      if(!(userHand.equalsIgnoreCase("Quit") || userHand.equalsIgnoreCase("Stats"))){
      System.out.println("The computer chose: " + computerHand);
      System.out.println("You have chosen: " + userHand);
      }
      totalGames++;
      //if statements to compare computer and user inputs, and find the winner
      if(computerHand.equalsIgnoreCase("Rock") && userHand.equalsIgnoreCase("Scissors")){
         System.out.println("Computer has won...");
      }
      if(computerHand.equalsIgnoreCase("Rock") && userHand.equalsIgnoreCase("Paper")){
         System.out.println("User has won!!!");
         wins++;
      }
      if(computerHand.equalsIgnoreCase("Paper") && userHand.equalsIgnoreCase("Rock")){
         System.out.println("Computer has won...");
      }
      if(computerHand.equalsIgnoreCase("Paper") && userHand.equalsIgnoreCase("Scissors")){
         System.out.println("User has won!!!");
         wins++;
      }
      if(computerHand.equalsIgnoreCase("Scissors") && userHand.equalsIgnoreCase("Paper")){
         System.out.println("Computer has won...");
      }
      if(computerHand.equalsIgnoreCase("Scissors") && userHand.equalsIgnoreCase("Rock")){
         System.out.println("User has won!!!");
         wins++;
      }
      if(computerHand.equalsIgnoreCase(userHand)){
         System.out.println("It is a tie! Try again.");
         totalGames--;
      }
      if(userHand.equalsIgnoreCase("Stats")){
         totalGames--;
        }
      if(userHand.equalsIgnoreCase("Quit")){
         totalGames--;
         }
   }
   public static void writeDataToFile()throws IOException//method to write the stats(total games, user wins and win percentage)
   {
      PrintWriter of = new PrintWriter("RPSdataTEST.txt");
      of.println(wins);
      of.println(totalGames);
      of.close();
   }
   public static void readDataFromFile() throws IOException//method to read the data from the file and display it to the user
   {
      File statsFile = new File("RPSdataTEST.txt");
      Scanner iFile = new Scanner(statsFile);
 

      wins = iFile.nextInt();
      iFile.nextLine();
      totalGames = iFile.nextInt();
      System.out.println("Wins: " + wins);
      System.out.println("Total Games: " + totalGames);
      //calculation of win percentage
      double winPercentage = (((double)wins/(double)totalGames) * 100);
      System.out.println("Win percentage: " + String.format("%.1f",winPercentage) + "%");
      iFile.close();  
      
   } 
}
