public class Game {

	Deck deck;
	Player[] players;
	int curPlayer = 0;
	int direction = -1;

	public Game(Player... plyrs){
		players = plyrs;
		deck = new Deck();
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

	public void playGame(){
		Card c = deck.drawCard();
		while(c.type==Card.Type.WILD_DRAW_FOUR){
			deck.discarded(c);
			deck.refresh();
			c = deck.drawCard();
		}
		deck.played(c);

		while(!hasWinner()){

		}
	}

	public static void main(String[] args){
		Game g = new Game(new HumanPlayer());
		while(!g.deck.isEmpty()){
			System.out.println(g.deck.drawCard());
		}

	}

}

