import java.util.*;

class Card {
    private String symbol; 
    private String rank;    

    public Card(String symbol, String rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return Objects.equals(symbol, card.symbol) && Objects.equals(rank, card.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, rank);
    }
}

class CardCollection {
    private Collection<Card> cards;

    public CardCollection() {
        cards = new HashSet<>(); 
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> findCardsBySymbol(String symbol) {
        List<Card> matchingCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }
}

public class CardCollector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        collection.addCard(new Card("Hearts", "Ace"));
        collection.addCard(new Card("Spades", "King"));
        collection.addCard(new Card("Diamonds", "10"));
        collection.addCard(new Card("Clubs", "Queen"));
        collection.addCard(new Card("Hearts", "7"));

        System.out.println("Enter a symbol to search (Hearts, Diamonds, Clubs, Spades): ");
        String symbol = scanner.nextLine();

        List<Card> result = collection.findCardsBySymbol(symbol);

        if (result.isEmpty()) {
            System.out.println("No cards found for the symbol: " + symbol);
        } else {
            System.out.println("Cards found for " + symbol + ":");
            for (Card card : result) {
                System.out.println(card);
            }
        }

        scanner.close();
    }
}
