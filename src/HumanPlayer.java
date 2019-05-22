import java.util.Scanner;

public class HumanPlayer implements Player {

	public Card playCard(Card curCard){
		System.out.println("Playing on top of "+curCard);
		for(int i = 0; i < hand.size(); i++){
			System.out.println("Card "+i+": "+hand.get(i));
		}
		Scanner scan = new Scanner(System.in);
		System.out.print("Play which card? (-1 to draw a card): ");
		int toPlay = scan.nextInt();
		if(toPlay<0||toPlay>hand.size())return null;
		return hand.remove(toPlay);
	}

	public Card.Color pickColor(){
		Scanner scan = new Scanner(System.in);
		System.out.print("What color?: ");
		String in = scan.nextLine();
		char sel = in.toLowerCase().charAt(0);
		if(sel=='b')return Card.Color.BLUE;
		else if(sel=='r')return Card.Color.RED;
		else if(sel=='g')return Card.Color.GREEN;
		else if(sel=='y')return Card.Color.YELLOW;
		else return Card.Color.NONE;
	}

	public boolean hasWon(){
		return hand.isEmpty();
	}

	public void invalidMove(){
		System.out.println("That move was invalid!");
	}

	public String name(){
		return "Human Player";
	}

}
