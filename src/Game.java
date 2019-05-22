import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	Deck deck;
	Player[] players;
	int curPlayer;
	int direction = -1;

	static FIRO<String> names = new FIRO<String>("Computer Player", "John Blizzard", "Millard Fillmore", "Killbot9000", "Regis Philbin", "Milhouse", "Rain Gardener", "Leviathan East", "Jared the Echidna", "Nakolas Gruffuth", "Rasputin, Russia's Greatest Love Machine", "Dennis Dixon", "Dave N. Sip", "Crater Wilhelm", "Jack Sun", "Yacht Oars", "Demotivated Lethargic Destroyed Lego Repairman");


	public Game(Player... plyrs){
		players = plyrs;
		deck = new Deck();
		for(int i = 0; i < players.length; i++){
			curPlayer = i;
			for(int j=0;j<7;j++)drawCard();
		}
		curPlayer=0;
	}

	public void takeTurn(){
		Player p = players[curPlayer];
		Card c = p.playCard(deck.lastPlayed);
		if(c==null){
			drawCard();
			nextTurn();
			System.out.println(p.name()+" drew a card!");
			return;
		}
		if(c.type==Card.Type.WILD||c.type==Card.Type.WILD_DRAW_FOUR){
			c.color=p.pickColor();
		}
		if(!deck.validPlay(c)){
			p.drawCard(c);
			p.invalidMove();
			System.out.println(p.name()+" made an invalid move!");
			return;
		}
		System.out.println(p.name()+" played "+c+"!");
		if(c.type==Card.Type.REVERSE){
			deck.played(c);
			reverse();
			nextTurn();
			System.out.println(p.name()+" reversed the turn order!");
			System.out.println("It is now "+players[curPlayer].name()+"'s turn.");
		}
		else if(c.type==Card.Type.SKIP){
			deck.played(c);
			nextTurn();
			System.out.println(players[curPlayer].name()+" was skipped!");
			nextTurn();
		}
		else if(c.type==Card.Type.PLUS_TWO){
			deck.played(c);
			nextTurn();
			for(int i=0;i<2;i++)drawCard();
			System.out.println(players[curPlayer].name()+" had to draw two cards!");
			nextTurn();
		}
		else if(c.type==Card.Type.WILD_DRAW_FOUR){
			deck.played(c);
			nextTurn();
			for(int i=0;i<4;i++)drawCard();
			System.out.println(players[curPlayer].name()+" had to draw four cards!");
			System.out.println(p.name()+" set the color to "+c.color+"!");
			nextTurn();
		}
		else if(c.type==Card.Type.WILD){
			deck.played(c);
			nextTurn();
			System.out.println(p.name()+" set the color to "+c.color+"!");
		}
		else{
			deck.played(c);
			nextTurn();
		}
	}

	public void nextTurn(){
		curPlayer = (curPlayer + direction + players.length) % players.length;
	}

	public void drawCard(){
		players[curPlayer].drawCard(deck.drawCard());
	}

	public void reverse(){
		direction *= -1;
	}

	public boolean hasWinner(){
		for(Player p : players){
			if(p.hasWon())return true;
		}
		return false;
	}

	public Player winner(){
		for(Player p : players){
			if(p.hasWon())return p;
		}
		return null;
	}

	public void playGame(){
		Card c = deck.drawCard();
		while(c.type.ordinal()>Card.Type.NINE.ordinal()){
			deck.discarded(c);
			c = deck.drawCard();
		}
		deck.refresh();
		deck.played(c);
		System.out.println("The first card is "+c+"!");
		while(!hasWinner()){
			takeTurn();
		}
		System.out.println(winner().name()+" wins!");
	}

	public static void main(String[] args){
		ArrayList<Player> plyrs = new ArrayList<Player>();
		Scanner scan = new Scanner(System.in);
		System.out.print("How many human players?: ");
		int human, comp;
		human = scan.nextInt();
		for(int i = 0; i < human; i++){
			plyrs.add(new HumanPlayer("Human Player "+(i+1)));
		}
		System.out.print("How many computer players?: ");
		comp = scan.nextInt();
		for(int i = 0; i < comp; i++){
			plyrs.add(new ComputerPlayer());
		}
		Player[] playrs = new Player[plyrs.size()];
		for(int i = 0; i < playrs.length; i++){
			playrs[i] = plyrs.get(i);
		}
		Game g = new Game(playrs);
		g.playGame();
	}

}

