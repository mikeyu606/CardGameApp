import java.util.ArrayList;

//this class represents the CardTable in the game
public class CardTable implements Table<Card,CardPlayer>{
    public static final int NUMBER_OF_PLACES = 4;
    private ArrayList<Card>[] places; //this variable holds the 4 places in an array
    private int currentPlace; //this variable is used to keep track of current place in the game.

    //no argument constructor creating the cardTable.
    public CardTable() {
        places = new ArrayList[NUMBER_OF_PLACES];
        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            places[i] = new ArrayList<Card>(); //in each of the places create a new ArrayList
        }
        currentPlace = 0;
    }

    //this method adds a card to the current place on the table
    public void addCardToPlace(Card card) {
        places[currentPlace].add(card);
        currentPlace = (currentPlace + 1) % NUMBER_OF_PLACES; //increment spot by 1 and set to 0 if greater than 4 spots
    }

    //this method returns the identifiers of the cards on places 1, 2, 3, and 4 on the table
    public int[] getPlaces() {
        int[] placeIdentifiers = new int[NUMBER_OF_PLACES]; //this array represents the identifier from each place

        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            if (places[i].isEmpty()) {
                placeIdentifiers[i] = -1; // indicate that the place is empty
            } else {
                placeIdentifiers[i] = places[i].get(places[i].size() - 1).identifier; // identifier of top card
            }
        }
        return placeIdentifiers;
    }

    //This method takes a player input and checks the places on the table to see if any player occupies a place and
    //removes any cards that they played and the matched card and moves it to their bank
    public void checkPlaces(CardPlayer player) {

        int previousPlace = currentPlace-1; //we subtract one from current place because addCardToPlace displaces it forward by one.
        if(previousPlace == -1) { //if negative that means the last check was at place 3
            previousPlace = 3;
        }
        for (int i = 0; i < NUMBER_OF_PLACES; i++) {
            if(places[i].size() == 0)  {continue; }
            Card checkCard = places[previousPlace].get(places[previousPlace].size()-1); //gets the Card that we are checking
            if(i == previousPlace) {continue;}

            Card cardTemp = places[i].get(places[i].size()-1); //temporary card that we are checking for a match
            if(checkCard.getRank() == cardTemp.getRank()) {
                System.out.println("Matched ranks: " + cardTemp.identifier + " (on table) and " + checkCard.identifier + " (" + player.name + "'s card)" );
                player.setPoints(player.getPoints() + 1); //add one point
                player.bank.add(checkCard);
                player.bank.add(cardTemp);
                places[i].remove(cardTemp); //remove the top card
                places[previousPlace].remove(checkCard);
                break; //needs to stop checking
            }

        }
        player.setTurn(false); //end players turn
    }

}
