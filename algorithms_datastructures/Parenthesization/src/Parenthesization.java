import java.util.Stack;

public class Parenthesization {

	
	public static void main(String[] args)
	{
		System.out.println(isParenthesized("(()()((()))(())(((((((())))))))()()"));
	}
	
	public static boolean isParenthesized(String toCheck)
	{
		Stack<String> stack1 = new Stack<>();
		stack1.add(toCheck.charAt(0)+"");
		for(int i = 1; i< toCheck.length(); i++)
		{
			String chart = toCheck.charAt(i)+"";
			String stackHead = stack1.peek();
			if( (stackHead + chart).equals("()")  )
			{
				stack1.pop();
			}
			else
			{
				stack1.add(chart);
			}
		}
		
		return stack1.isEmpty();
	}
}
