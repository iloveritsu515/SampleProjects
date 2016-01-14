//CS 520 fall 2015 HW#1 Programmer: Christian White
#include <iostream>
#include <string>
using namespace std;
//abstract or parent class 
class BatAndBallGames
{
// data members or variables
protected: int ballBoys;
protected: int waterBoys;
protected: int lengthOfGame;
protected: int playersPerTeam;
public:
//funtion member or method 
virtual int display() = 0;
virtual int rulesSummary() = 0;
virtual int betterThan() = 0;//come up with something
virtual int totalOnField() = 0;//come up with something like hall of fame
};
class Baseball : public BatAndBallGames
{
public:
	//Baseball() : BatAndBallGames(){}
	int score;
	int innings;
	//shows current game score, innings, etc.
	int display()
	{
		score = 10;
		innings = 5;
		cout <<"(Baseball)current score is: "<< score <<" padres " << score+1<< "A's"<<endl;
		cout <<"current inning is: "<<innings<<endl;
		lengthOfGame = 3;
		cout <<"games tend to go for about "<<lengthOfGame<<"hours"<<endl;
		return 0;
		waterBoys=3;
		ballBoys=0;
		cout<<"ball boys: "<<ballBoys<<"water boys: "<<waterBoys<<"."<<endl;
	}
	int rulesSummary()
	{
		cout <<"rules"<<endl;
		return 0;
		//betterThan();
	}
	int betterThan()
	{
		cout << "Baseball is way better than Cricket"<<endl;
		return 0;
		//totalOnField();
	}
	int totalOnField()
	{
		cout << "including the batter how many players would you like on base? enter a number"<<endl;
		cin >> playersPerTeam;
		if(playersPerTeam > 4){
			cout << "You cannot have more than four people"<<endl;
			totalOnField();
		}
		else{
			cout << "there are currently "<< playersPerTeam+9 <<" players on the field"<<endl;
		}
		return 0;

	}
	int bClassMem1()
	{
		cout<<"Baseball: Hello World!"<<endl;
		return 0;
	}
	int progComplete()
	{
		ballBoys=3;

		cout << "program complete!"<<endl;
		return 0;
		exit(0);
	}
};
class Cricket : public BatAndBallGames
{
public:
	//Cricket() : BatAndBallGames(){}
	int score;
	
	int display()
	{
		lengthOfGame = 4;
		score = 5;
		cout <<"(Cricket)current score is: "<< score << " team A "<< score-3 << " team B"<<endl;
		cout <<"game time left: "<< lengthOfGame <<"hours"<<endl;
		//rulesSummary();
		return 0;
	}
	int rulesSummary()
	{
		cout <<"rulesCricket"<<endl;
		return 0;
		//betterThan();
	}
	int betterThan()
	{
		cout << "Instead of watching/playing cricket you could play soccer which is way cooler"<<endl;
		return 0;
		//totalOnField();
	}
	int totalOnField()
	{
		cout << "there are a total of 11 players on the Field of a Cricket match"<<endl;
		return 0;
		youLike();
	}	
	int youLike()
	{
		cout << "Do you like cricket?(y/n)"<<endl;
		string yn;
		cin >> yn;
		//putchar(tolower(yn));
		if (yn.compare(0,1,"y") == 0)
		{
			cout<< "that's nice I suppose, I have no opinion either way."<<endl;//yes
		}
		else if (yn.compare(0,1,"n") == 0)
		{
			cout<<"I don't know enough to have much of an opinion on it but it seems you do"<<endl;//no
		}
		else
		{
			cout<<"incorrect input try again"<<endl;	
			youLike();//wrong input try again
		}
		return 0;
	}
	int cClassMem2()
	{
		cout << "Cricket: Hello World!"<<endl;
		return 0;
	}
};
int main(int argc, char const *argv[])
{
	Baseball bball;
	Cricket cball;
	BatAndBallGames * bbg1 = &bball;
	BatAndBallGames * bbg2 = &cball;
	bbg1->display();
	bbg2->display();
	bbg2->rulesSummary();
	bbg2->rulesSummary();
	bbg2->betterThan();
	bbg2->betterThan();
	bbg2->totalOnField();
	bbg2->totalOnField();
	bball.progComplete();
	bball.bClassMem1();
	cball.cClassMem2();

	/*bClassMem1();
	baseball.display();
	baseball.rulesSummary();
	cricket.display();
	cricket.rulesSummary();*/
	return 0;
}