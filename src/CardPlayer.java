import java.util.ArrayList;
import java.util.Iterator;


//A class that represents a card player.
public class CardPlayer extends GeneralPlayer<Card> {

    private int points;// tracks how many points one player won

    private boolean turn; //tracks their turns

    private ArrayList<Card> hand = new ArrayList<Card>(); //this arrayList the players hand

    public ArrayList<Card> bank = new ArrayList<Card>(); // this arrayList holds the players bank


    public CardPlayer() {
        super();
    } //constructor initializing player with general name

    public CardPlayer(String name) {this.name = name;} //constructor initializing player with specific name

    public int getPoints() {return points;} //this method returns the number of points a player has

    public void setPoints(int points) {this.points = points;} //sets the number of points for a player

    public boolean isTurn() {return turn;} //checks if  players turn

    public void setTurn(boolean turn) {this.turn = turn;} //turns players turn to true or false

    public void addToHand(Card card) {hand.add(card);} // adds a card to players hand

    public ArrayList<Card> getHand() {return hand;} //returns a reference to their hand


    //this method returns your cards in hand as a String
    public String handToString() {
        String handString = this.name + " hand has " + this.hand.size() + " cards: ";
        for (Card c : hand) {
            handString += c.identifier + " ";
        }
        return handString;
    }

    //this method returns a string of the cards in player's bank
    public String bankToString() {
        String bankString = this.name + " bank has " + this.bank.size() + " cards: ";
        for (Card c : bank) {
            bankString += c.identifier + " ";
        }
        return bankString;
    }
    //this method removes the top card from the players hand and returns it.
    public Card play() {
        if(hand.size() == 0) {
            return null;
        }
        Card topCard = hand.get(0); //holds the topCard

        hand.remove(0);

        return topCard;
    }

}
