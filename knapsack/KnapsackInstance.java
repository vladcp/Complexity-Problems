/**
 * A knapsack instance class
 */
package knapsack;
import knapsack.Knapsack.Item;

public class KnapsackInstance {
    int C;
    Item[] items;
    int lower_bound;
    int upper_bound;
    //not a boolean[], should have 3 options: definitely chosen, definitely unchosen, to be determined.
    boolean[] item_choices;

    //constructor
    public void KnapsackInstance(int cap, Item[] items, boolean[] item_choices){
        this.C = cap;
        this.items = items;
        this.item_choices = item_choices;
    }

    public int CalcLowerBound(){
        int lower_bound = 0;

        for (int i = 0; i<item_choices.length; i++) {
            if (item_choices[i] == ) {
                C -= items[i].weight;
                lower_bound += items[i].weight;
            }
        }

        for (int i = 0; i<items.length; i++){
            if ((items[i].weight < C) && (item_choices[i] == true)) {
                item_choices[i] = true;
                C -= items[i].weight;
                lower_bound += items[i].weight;
            }
        }

        
    }


    public int CalcUpperBound(){

    }




}
