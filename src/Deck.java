import java.util.ArrayList;

public class Deck {

	FIRO<Card> drawPile;
	ArrayList<Card> discPile;
	Card lastPlayed;

	public Deck(){
		drawPile = new FIRO<Card>();
		discPile = new ArrayList<Card>();

		for(Card.Color col : Card.Color.values()){
			for(Card.Type type : Card.Type.values()){
				if(col == Card.Color.NONE){
					if(type == Card.Type.WILD || type == Card.Type.WILD_DRAW_FOUR){
						for(int i = 0; i < 4; i++){
							drawPile.add(new Card(col, type));
						}
					}
				}
				else{
					if(type != Card.Type.WILD && type != Card.Type.WILD_DRAW_FOUR){
						drawPile.add(new Card(col, type));
						if(type!= Card.Type.ZERO)drawPile.add(new Card(col, type));
					}
				}
			}
		}
	}

	public Card drawCard(){
		Card ret = drawPile.pop();
		if(drawPile.isEmpty())refresh();
		return ret;
	}

	public boolean isEmpty(){
		return drawPile.isEmpty();
	}

	public void refresh(){
		for(Card c : discPile){
			drawPile.add(c);
		}
		discPile = new ArrayList<>();
	}

	public void played(Card c){
		discPile.add(lastPlayed);
		lastPlayed = c;
	}

	public void discarded(Card... cards){
		for(Card c : cards){
			if(c.type==Card.Type.WILD || c.type==Card.Type.WILD_DRAW_FOUR){
				c.color=Card.Color.NONE;
			}
			discPile.add(c);
		}
	}

	public boolean validPlay(Card c){
		return (c.color==lastPlayed.color||c.type==lastPlayed.type||c.type==Card.Type.WILD||c.type==Card.Type.WILD_DRAW_FOUR);
	}

}
