import java.util.*;

public class Hand implements Comparable {
   private ArrayList<Card> hand;
   private int high;

   public Hand(){
      hand = new ArrayList<Card>();
   }

   public void add(Card c){
      hand.add(c);
   }

   public void sortHand(){
      Collections.sort(hand);
   }

   public String toString(){
      return hand.toString();
   }

   /*
   BEST
   Royal Flush
   Straight Flush
   Four of a Kind
   Full House
   Flush
   Straight
   Three of a Kind
   Two Pair
   One Pair
   High Card
   WORST
   */

   public String handValue()
   {
     //return "TODO: String of Best Hand; may need helper methods";
     HashMap<Integer, String> cardsHand = new HashMap<Integer,String>();
     HashMap<Integer, Integer> cardsHand1 = new HashMap<Integer, Integer>();
     HashSet<Integer> royal = new HashSet<Integer>();
     royal.add(10); royal.add(11); royal.add(12); royal.add(13); royal.add(14);
     int handValue = 0;
     for(Card x : hand)
     {
       cardsHand.put(x.value,x.suit);
       //handValue = handValue + x.value;
       if(cardsHand1.containsKey(x.value))
         cardsHand1.put(x.value, (cardsHand1.get(x.value)+1));
       else
         cardsHand1.put(x.value, 1);
     }

     if(cardsHand.keySet().containsAll(royal) && cardsHand.values().size() == 1)
       return "Royal Flush";

     if(straightChecker() == true && cardsHand.keySet().size() == 5 && cardsHand.values().size() == 1)
       return "Straight Flush";

     //if(cardsHand.keySet().size() <= 2 && cardsHand.values().size() >= 4)
     if(counters() == 3 && cardsHand1.containsValue(4))
       return "Four of a Kind";

     if(counters() == 3)
       return "Full House";

     if(cardsHand.keySet() != royal && cardsHand.values().size() == 1)
       return "Flush";

     if(straightChecker() == true && cardsHand.values().size() != 1)
       return "Straight";

     if(counters() == 2 && cardsHand1.containsValue(3))
       return "Three of a Kind";

     if(counters() == 2)
       return "Two Pairs";

     if(counters() == 1)
       return "One Pair";

     else
     {
       high = hand.get(4).value;
       return "High Card";
     }

   }

   public int counters()
   {
     int count = 0;
     for(int o = 1; o < hand.size(); o++)
     {
       if(hand.get(o).value == hand.get(o-1).value)
       {
         count++;
         high = hand.get(o).value;
       }
     }
     return count;
   }

   public boolean straightChecker()
   {
     int count = 0;
     for(int o = 1; o < hand.size(); o++)
     {
       if(hand.get(o).value - hand.get(o-1).value == 1)
         count++;
     }
     if(count == 4)
       return true;
     else
       return false;
   }

   public int conversion (String f)
   {
     if(f.equals("Royal Flush"))
       return 10;
     if(f.equals("Straight Flush"))
       return 9;
     if(f.equals("Four of a Kind"))
       return 8;
     if(f.equals("Full House"))
       return 7;
     if(f.equals("Flush"))
       return 6;
     if(f.equals("Straight"))
       return 5;
     if(f.equals("Three of a Kind"))
       return 4;
     if(f.equals("Two Pairs"))
       return 3;
     if(f.equals("One Pair"))
       return 2;
     if(f.equals("High Card"))
       return 1;
     return 0;
   }

   public int compareTo(Object x){
      Hand other = (Hand)x;
      //TODO: Compare hands by ordering above; return -1, 1, or 0
      if(this.conversion(this.handValue()) == other.conversion(other.handValue()))
      {
        if(this.high > other.high)
          return 1;
        if(this.high == other.high)
          return 0;
        if(this.high < other.high)
          return -1;
      }
      else
      {
        if(this.conversion(this.handValue()) > other.conversion(other.handValue()))
          return 1;
        if(this.conversion(this.handValue()) == other.conversion(other.handValue()))
          return 0;
        if(this.conversion(this.handValue()) < other.conversion(other.handValue()))
          return -1;
      }
      return -2;
   }
}
