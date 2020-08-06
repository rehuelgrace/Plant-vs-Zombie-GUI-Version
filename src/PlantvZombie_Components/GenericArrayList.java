package PlantvZombie_Components;

import javax.swing.*;
import java.util.ArrayList;

public class GenericArrayList<T> {
    ArrayList<ArrayList<T>> arrayOf ;
    
    //Constructor
    public GenericArrayList(){
        arrayOf = new ArrayList<ArrayList<T>>();
        
    }
    
    
    /* Method add to arraylist
     Manggilnya contoh kek gini
     
     GenericArray <ZombieIndie> zombieIndieTrack = new GenericArray<>();
     zombieIndieTrack.arrayOf.add(new ArrayList<>()); //line 1
     zombieIndieTrack.arrayOf.add(new ArrayList<>()); //line 2
     zombieIndieTrack.arrayOf.add(new ArrayList<>()); //line 3
     zombieIndieTrack.arrayOf.add(new ArrayList<>()); //line 4
     zombieIndieTrack.arrayOf.add(new ArrayList<>()); //line 5
     
    
    */
    
   
    public T get(int row, int col)throws GenericArrayListException{
        if (row > this.arrayOf.size()-1){
            throw new GenericArrayListException("Array Row Index Out of Bounds");
        }else{
            if (col > this.arrayOf.get(row).size()-1){
                throw new GenericArrayListException("Array Col Index Out of Bounds");
            }else{
                return this.arrayOf.get(row).get(col);
            }
        }
    }
    
    
    
}
