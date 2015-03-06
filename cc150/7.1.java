public class Card {
	public enum Suit {
		CLUBS (1), SPAEDS (2), HEARTS (3), DIAMONDS (4);
		int val;
		private Suit(int v)
		{
			val = v;
		}
	}

	private int card;
	private Suit suit;

	public Card(int c, Suit s)
	{
		card = c;
		suit = s;
	}

	public int value() {
		return card;
	}

	public Suit suit() {
		return suit;
	}
}



public class BlackJackCard extends Card {
	public BlackJackCard(int c, Suit s)
	{
		super(c, s);
	}

	public int value() {
		int c = super.value();
		if(c==1)
			return 11;
		if(c<10)
			return c;
		return 10;
	}

	public boolean isAce() {
		return super.value()==1;
	}
}

Note: 复习enum类