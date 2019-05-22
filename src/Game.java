public class Game {

	Deck deck;
	Player[] players;
	int curPlayer = 0;
	int direction = -1;

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
			return;
		}
		if(c.type==Card.Type.WILD||c.type==Card.Type.WILD_DRAW_FOUR){
			c.color=p.pickColor();
		}
		if(!deck.validPlay(c)){
			p.drawCard(c);
			p.invalidMove();
		}
		else if(c.type==Card.Type.REVERSE){
			deck.played(c);
			reverse();
			nextTurn();
		}
		else if(c.type==Card.Type.SKIP){
			deck.played(c);
			for(int i=0;i<2;i++)nextTurn();
		}
		else if(c.type==Card.Type.PLUS_TWO){
			deck.played(c);
			nextTurn();
			for(int i=0;i<2;i++)drawCard();
			nextTurn();
		}
		else if(c.type==Card.Type.WILD_DRAW_FOUR){
			deck.played(c);
			nextTurn();
			for(int i=0;i<4;i++)drawCard();
			nextTurn();
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
		while(!hasWinner()){
			takeTurn();
		}
		System.out.println(winner().name()+" wins!");
	}

	public static void main(String[] args){
		Game g = new Game(new HumanPlayer());
		g.playGame();
	}

}

