import java.util.*;
public class JavaLoops
{
   public static void main(String[] args)
   {
      int [] group = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      for (int x : group)
      {
         System.out.println(x);
      }
      
      System.out.println("Even numbers");
      
      for (int i=0; i<group.length; i++)
         if(group[i]%2 == 0)
         {
            System.out.println(group[i]);
         }
   }
}
      