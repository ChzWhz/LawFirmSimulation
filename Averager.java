// Written by Michael Main

public class Averager
{
   private int count;  // How many numbers have been given to this averager
   private double sum; // Sum of all the numbers given to this averager                  
   
   public Averager( )
   {
       count =0;
       sum = 0;
   }
   
   public void addNumber(double value)
   {
      if (count == Integer.MAX_VALUE)
         throw new IllegalStateException("Too many numbers");
      count++;
      sum += value;
   }
 
   public double average( )
   {
      if (count == 0)
         return Double.NaN;
      else
         return sum/count;
   } 

   public int howManyNumbers( )
   {
      return count;
   }
}