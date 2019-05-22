import java.util.ArrayList;

public interface Player {

	ArrayList<Card> hand = new ArrayList<>();

	public Card playCard(Card curCard);

	public default void drawCard(Card c){
		hand.add(c);
	}

	public Card.Color pickColor();

	public boolean hasWon();

	public void invalidMove();

	public String name();

}
