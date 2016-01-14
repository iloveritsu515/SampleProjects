//CS 520 fall 2015 HW#1 Programmer: Christian White
//abstract or parent class
import java.util.Scanner;
abstract class BatAndBallGames
{
// data members or variables
protected int ballBoys;
protected int waterBoys;
protected int lengthOfGame;
protected int playersPerTeam;

//funtion member or method
public abstract int display();
public abstract int rulesSummary();
public abstract int betterThan();//come up with something
public abstract int totalOnField();//come up with something like hall of fame
};
class Baseball extends BatAndBallGames
{
	int score;
	int innings;
public Baseball() {}
	//shows current game score, innings, etc.
	public int display()
	{
		score = 10;
		innings = 5;
		System.out.println("(Baseball)current score is: "+ score +" padres " + (score+1) + "A's");
		System.out.println("current inning is: "+innings);
		lengthOfGame = 3;
		System.out.println("games tend to go for about "+lengthOfGame+"hours");
		int waterBoys=≥  ,;
		int ballBoys=0;
		System.out.println("ball boys: "+ballBoys+"water boys: "+waterBoys+".");
		return 0;
	}
	public int rulesSummary()
	{
		System.out.println("For the rules of baseball go to http//mlb.mlb.com/mlb/downloads/y2015/official_baseball_rules.pdf");
		return 0;
		//betterThan();
	}
	public int betterThan()
	{
		System.out.println("Baseball is way better than Cricket");
		return 0;
		//totalOnField();
	}
	public int totalOnField()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("including the batter how many players would you like on base? enter a number");
		int playersPerTeam = input.nextInt();
		if(playersPerTeam > 4){
			System.out.println("You cannot have more than four people");
			totalOnField();
		}
		else{
			System.out.println("there are currently " +(playersPerTeam+9)+" players on the field");
		}
		return 0;

	}
	public int bClassMem1()
	{
		System.out.println("Baseball: Hello World!");
		return 0;
	}
	public int progComplete()
	{
		ballBoys=3;

		System.out.println("program complete!");
		return 0;
	}
}
class Cricket extends BatAndBallGames
{
	int scoreC;
	public Cricket(){}


	public int display()
	{
		lengthOfGame = 4;
		scoreC = 5;
		System.out.println("(Cricket)current score is: "+scoreC+" team A "+(scoreC-3)+" team B");
		System.out.println("game time left: "+lengthOfGame+"hours");
		//rulesSummary();
		return 0;
	}
	public int rulesSummary()
	{
		System.out.println("rulesCricket");
		return 0;
		//betterThan();
	}
	public int betterThan()
	{
		System.out.println("Instead of watching/playing cricket you could play soccer which is way cooler");
		return 0;
		//totalOnField();
	}
	public int totalOnField()
	{
		System.out.println("there are a total of 11 players on the Field of a Cricket match");
		youLike();
		return 0;
	}
	public int youLike()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Do you like cricket?(y/n)");
		String yn = in.nextLine();
		//putchar(tolower(yn));
		if (yn.equals("y"))
		{
			System.out.println("that's nice I suppose, I have no opinion either way.");
		}
		else if (yn.equals("n"))
		{
			System.out.println("I don't know enough to have much of an opinion on it but it seems you do");
		}
		else
		{
			System.out.println("incorrect input try again");
			youLike();//wrong input try again
		}
		return 0;
	}
	public int cClassMem2()
	{
		System.out.println("Cricket: Hello World!");
		return 0;
	}
}
public class HW1 {
public static int main(String[] args)
{
	Baseball bball = new Baseball();
	Cricket cball = new Cricket();
	bball.display();
	cball.display();
	bball.rulesSummary();
	cball.rulesSummary();
	bball.betterThan();
	cball.betterThan();
	bball.totalOnField();
	cball.totalOnField();
	bball.bClassMem1();
	cball.cClassMem2();

	/*bClassMem1();
	baseball.display();
	baseball.rulesSummary();
	cricket.display();
	cricket.rulesSummary();*/
	return 0;
}
}
