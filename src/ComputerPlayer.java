import java.util.ArrayList;

public class ComputerPlayer implements Player{

	ArrayList<Card> hand = new ArrayList<>();

	private String name;

	public ComputerPlayer(){
		if(Game.names.isEmpty())name = "Computer Player";
		else name = Game.names.pop();
	}

	public ComputerPlayer(String name){
		this.name = name;
	}

	public Card playCard(Card curCard) {
		for(int c = 0; c < hand.size(); c++){
			if(validPlay(curCard, hand.get(c))){
				return hand.remove(c);
			}
		}
		return null;
	}

	public Card.Color pickColor() {
		int r=0,g=0,b=0,y=0;
		for(Card c : hand){
			if(c.color==Card.Color.RED)r++;
			else if(c.color==Card.Color.GREEN)g++;
			else if(c.color==Card.Color.BLUE)b++;
			else if(c.color==Card.Color.YELLOW)y++;
		}
		if(y>b&&y>g&&y>r)return Card.Color.YELLOW;
		if(b>g&&b>r)return Card.Color.BLUE;
		if(g>r)return Card.Color.GREEN;
		return Card.Color.RED;
	}

	public void invalidMove(){

	}

	public String name(){
		return name;
	}

	public boolean validPlay(Card c1, Card c2){
		return (c1.color==c2.color||c1.type==c2.type||c2.type==Card.Type.WILD_DRAW_FOUR||c2.type==Card.Type.WILD);
	}

	public void drawCard(Card c){
		hand.add(c);
	}

	public boolean hasWon(){
		return hand.isEmpty();
	}

}
