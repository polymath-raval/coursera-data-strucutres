import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;


public class MaxSlidingWindow1 {
	
	private List<Integer> max(int[] numbers, int windowSize) {
		Deque<Integer> dq = new ArrayDeque<>();
		List<Integer> result  = new ArrayList<>();

		for(int i = 0; i < windowSize; i++) {
			Iterator<Integer> iterator = dq.iterator();
			while(iterator.hasNext()) {
				int idx = iterator.next();
				if(numbers[idx] < numbers[i])
					iterator.remove();
			}
			dq.addLast(i);
			//System.out.printf("%s \n", dq);
		}
		result.add(numbers[dq.peek()]);
		//System.out.printf("%s \n", result);

		for(int i = windowSize; i < numbers.length; i++) {
			Iterator<Integer> iterator = dq.iterator();
			while(iterator.hasNext()) {
				int idx = iterator.next();
				if(numbers[idx] < numbers[i] || idx == (i - windowSize))
					iterator.remove();
			}
			dq.addLast(i);
			result.add(numbers[dq.peek()]);
			//System.out.printf("%s \n", dq);
			//System.out.printf("%s \n", result);

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
        List<Integer> result = new MaxSlidingWindow1().max(numbers, windowSize);
        for(int i = 0; i < result.size(); i++) {
        	System.out.printf("%d ",result.get(i));
        }
	}

	public static void main1(String[] args) {
		int n = 100000;
		int[] numbers = new int[n];
		int windowSize = 10;
		List<Integer> result = new MaxSlidingWindow1().max(numbers, windowSize);
        for(int i = 0; i < result.size(); i++) {
        	System.out.printf("%d ",result.get(i));
        }
	}
}