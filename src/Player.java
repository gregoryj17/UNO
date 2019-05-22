import java.util.ArrayList;

public interface Player {

	ArrayList<Card> hand = new ArrayList<>();

	public Card playCard();

	public default void drawCard(Card c){
		hand.add(c);
	}

	public boolean hasWon();

}
