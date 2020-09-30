public class Recursion 
{
  
	public static int callRecursion(int input)
   {
   int total = 0;
      for (int i = 1; i <= input; i++)
      {
         total = total + i;
      }
      return total;
   }
   public static void main(String[] args)
   {
      System.out.println(callRecursion(6));
   }
   
}