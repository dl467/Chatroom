import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuesserHW {
	//CHANGE HERE
   int count = 1;
   private static int x;
   private int level = 1;
	private int strikes = 0;
	private int maxStrikes = 5;
	private int number = 0;
	private boolean isRunning = false;
	final String saveFile = "numberGuesserSave.txt";
   //CHANGE HERE
   final String saveFile2 = "numberGuesserStrike.txt";
   //CHANGE HERE
   final String saveFile3 = "numberGuesserRange.txt";

	/***
	 * Gets a random number between 1 and level.
	 * 
	 * @param level (level to use as upper bounds)
	 * @return number between bounds
	 */
	public static int getNumber(int level) {
		int range = 9 + ((level - 1) * 5);
		System.out.println("I picked a random number between 1-" + (range + 1) + ", let's see if you can guess.");
      x = new Random().nextInt(range) + 1;
      return x;
	}

	private void win() {
		System.out.println("That's right!");
		level++;// level up!
		saveLevel();
		strikes = 0;
		System.out.println("Welcome to level " + level);
		number = getNumber(level);
	}

	private void lose() {
		System.out.println("Uh oh, looks like you need to get some more practice.");
		System.out.println("The correct number was " + number);
		strikes = 0;
		level--;
		if (level < 1) {
			level = 1;
		}
		saveLevel();
		number = getNumber(level);
	}

	private void processCommands(String message) {
		if (message.equalsIgnoreCase("quit")) {
			System.out.println("Tired of playing? No problem, see you next time.");
			
         isRunning = false;
      }
   }

	private void processGuess(int guess) {
		if (guess < 0) {
			return;
		}
		System.out.println("You guessed " + guess);
		if (guess == number) {
         win();
		} else {
			System.out.println("That's wrong");
			strikes++;
			if (strikes >= maxStrikes) {
				lose();
			} else {
				int remainder = maxStrikes - strikes;
				System.out.println("You have " + remainder + "/" + maxStrikes + " attempts remaining");
            //CHANGE
            if (count%2 == 0)
            {
               if (guess > number) {
					System.out.println("Lower");
				} else if (guess < number) {
					System.out.println("Higher");
               }
				}
            count++;
         }     
      }
   }

	private int getGuess(String message) {
		int guess = -1;
		try {
			guess = Integer.parseInt(message);
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a number, please try again");

		}
		return guess;
	}
   
   //CHANGE HERE
   private void saveRange() 
   {
		try (FileWriter fw3 = new FileWriter(saveFile3)) 
      {
			fw3.write("" + x);
		} 
      catch (IOException e) 
      {
			e.printStackTrace();
		}
	}
   //CHANGE HERE
   private boolean loadRange() 
   {
		File file3 = new File(saveFile3);
		if (!file3.exists()) 
      {
			return false;
		}
		try (Scanner reader = new Scanner(file3)) 
      {
			while (reader.hasNextLine()) 
         {
				int _x = reader.nextInt();
				if (x > 0)
            {
					x = _x;
					break;
				}
			}
		} 
      catch (FileNotFoundException e) 
      {
			e.printStackTrace();
			return false;
		} 
      catch (Exception e2)
      {
			e2.printStackTrace();
			return false;
		}
		return level > 1;
	}

   //CHANGE HERE
   private void saveStrikes() 
   {
		try (FileWriter fw2 = new FileWriter(saveFile2)) 
      {
			fw2.write("" + strikes);
		} 
      catch (IOException e) 
      {
			e.printStackTrace();
		}
	}
   //CHANGE HERE
   private boolean loadStrikes() 
   {
		File file2 = new File(saveFile2);
		if (!file2.exists()) 
      {
			return false;
		}
		try (Scanner reader = new Scanner(file2)) 
      {
			while (reader.hasNextLine()) 
         {
				int _strikes = reader.nextInt();
				if (_strikes >= 0) 
            {
					strikes = _strikes;
					break;
				}
			}
		} 
      catch (FileNotFoundException e) 
      {
			e.printStackTrace();
			return false;
		} 
      catch (Exception e2)
      {
			e2.printStackTrace();
			return false;
		}
		return level > 1;
	}


	private void saveLevel() {
		try (FileWriter fw = new FileWriter(saveFile)) {
			fw.write("" + level);// here we need to convert it to a String to record correctly
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean loadLevel() {
		File file = new File(saveFile);
		if (!file.exists()) {
			return false;
		}
		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				int _level = reader.nextInt();
				if (_level > 1) {
					level = _level;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
		return level > 1;
	}

	void run() {
		try (Scanner input = new Scanner(System.in);) {
			System.out.println("Welcome to Number Guesser 4.0!");
			System.out.println("I'll ask you to guess a number between a range, and you'll have " + maxStrikes
					+ " attempts to guess.");
         if (loadLevel()) {
				System.out.println("Successfully loaded level " + level + " let's continue then");
         }
         //CHANGE HERE
         if (loadStrikes())
         {
            System.out.println("Successfully loaded strikes, you have strikes " + strikes + " so far.");
         }
			number = getNumber(level);
			isRunning = true;
			while (input.hasNext()) {
				String message = input.nextLine();
				processCommands(message);
				if (!isRunning) {
					break;
				}
				int guess = getGuess(message);
				processGuess(guess);
			}
         
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		NumberGuesserHW guesser = new NumberGuesserHW();
		guesser.run();
	}
}