import java.util.ArrayList;

public interface Player {

	public Card playCard(Card curCard);

	public void drawCard(Card c);

	public Card.Color pickColor();

	public boolean hasWon();

	public void invalidMove();

	public String name();

}
