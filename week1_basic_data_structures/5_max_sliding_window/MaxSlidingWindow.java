import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;


public class MaxSlidingWindow {
	private static class SimpleStackException extends RuntimeException {
		SimpleStackException(String str) {
			super(str);
		}
	}

	private static class SimpleStack {
		int[] arr;
		int size;
		int pointer = -1;

		SimpleStack(int size) {
			this.size = size;
			this.arr = new int[size];
		}

		boolean isEmpty() {
			return pointer == -1;
		}

		boolean isFull() {
			return pointer == size - 1;
		}

		int size() {
			return pointer + 1;
		}

		void push(int val) {
			if(isFull()) {
				throw new SimpleStackException("Stack Full");
			}
			pointer++;
			arr[pointer] = val;
		}

		int peek() {
			if(isEmpty()) {
				throw new SimpleStackException("Stack Empty");
			}
			return arr[pointer];
		}

		int pop() {
			int result = peek();
			pointer--;
			return result;
		}
 
	}
	private int[] max(int[] numbers, int windowSize) {
		//Stack<Integer> stack1 = new Stack<>();
		//Stack<Integer> maxStack1 = new Stack<>();
		//Stack<Integer> stack2 = new Stack<>();
		//Stack<Integer> maxStack2 = new Stack<>();
		SimpleStack stack1 = new SimpleStack(windowSize);
		SimpleStack stack2 = new SimpleStack(windowSize);
		SimpleStack maxStack1 = new SimpleStack(windowSize);
		SimpleStack maxStack2 = new SimpleStack(windowSize);
		int[] result  = new int[numbers.length];

		for(int i = 0; i < numbers.length; i++) {
			//System.out.printf("**** numbers[%d]: %d **********\n", i, numbers[i]);
			if(stack2.size() + stack1.size() == windowSize) {
				if(stack2.isEmpty()) {
					while( !stack1.isEmpty() ) {
						stack2.push(stack1.pop());
						maxStack1.pop();
						maxStack2.push( maxStack2.isEmpty() ? 
							stack2.peek() : Integer.max(stack2.peek(), maxStack2.peek()));
					}
				}
				if( !stack2.isEmpty() ) {
					stack2.pop();
					maxStack2.pop();	
				}
			}

			stack1.push(numbers[i]);
			maxStack1.push(maxStack1.isEmpty() ? stack1.peek() : 
				Integer.max(stack1.peek(), maxStack1.peek()));
			int max1 = maxStack1.isEmpty() ? 0 : maxStack1.peek();
			int max2 = maxStack2.isEmpty() ? 0 : maxStack2.peek();
			result[i] = max1 > max2 ? max1 : max2;
			//System.out.printf("%s\n%s\n%s\n%s\n%s\n", stack1, maxStack1, stack2, maxStack2, result);
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        String[] strNumbers = reader.readLine().split(" ");
        for(int i = 0; i < n; i++)
        	numbers[i] = Integer.parseInt(strNumbers[i]);
        
        int windowSize = Integer.parseInt(reader.readLine());
        //System.out.printf("%d\n%s\n%d\n",n, Arrays.toString(numbers), windowSize);
        int[] result = new MaxSlidingWindow().max(numbers, windowSize);
        for(int i = windowSize - 1; i < n; i++) {
        	System.out.printf("%d ",result[i]);
        }
	}

	public static void main1(String[] args) {
		int n = 100000;
		int[] numbers = new int[n];
		int windowSize = 10;
		int[] result = new MaxSlidingWindow().max(numbers, windowSize);
        for(int i = windowSize - 1; i < n; i++) {
        	System.out.printf("%d ",result[i]);
        }
	}
}