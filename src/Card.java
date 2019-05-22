public class Card {

	public enum Color {NONE, RED, GREEN, BLUE, YELLOW};
	Color color;
	public enum Type {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, PLUS_TWO, REVERSE, SKIP, WILD, WILD_DRAW_FOUR};
	Type type;

	public Card(Color col, Type typ){
		color = col;
		type = typ;
	}

	public String toString(){
		String ret = "";
		switch(color){
			case NONE:
				ret+="W";
				break;
			case RED:
				ret+="R";
				break;
			case BLUE:
				ret+="B";
				break;
			case GREEN:
				ret+="G";
				break;
			case YELLOW:
				ret+="Y";
				break;
		}
		switch(type){
			case PLUS_TWO:
				ret+="P";
				break;
			case REVERSE:
				ret+="R";
				break;
			case SKIP:
				ret+="S";
				break;
			case WILD:
				ret+="W";
				break;
			case WILD_DRAW_FOUR:
				ret+="D";
				break;
			default:
				ret+=type.ordinal();
				break;
		}
		return ret;
	}

}

