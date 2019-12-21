import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class  MaxSlidingWindow3{
	public static void main(String[] args) throws IOException {
    InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        String[] strNumbers = reader.readLine().split(" ");
        for(int i = 0; i < n; i++) {
        	numbers[i] = Integer.parseInt(strNumbers[i]);
        }
        int windowSize = Integer.parseInt(reader.readLine());
        int[] result = new Solution().compute(numbers, windowSize);
        System.out.printf("I am here %d %d %d", n, numbers.length, windowSize);
        for(int i = windowSize -1; i < result.length; i++) {
          System.out.printf("%d ",result[i]);
        }
  }


  private static class Solution {
  	int[] computeLeft(int[] numbers, int k) {
  		int[] left = new int[numbers.length];
  		for(int i = 0; i < numbers.length; i++) {
  			int position = i % k;
  			if(position == 0) {
  				left[i] = numbers[i];
  			} else {
  				left[i] = left[i-1] > numbers[i] ? left[i-1] : numbers[i];
  			}
  		}
  		return left;
  	}

  	int[] computeRight(int[] numbers, int k) {
  		int[] right = new int[numbers.length];
  		right[numbers.length - 1] = numbers[numbers.length - 1];

  		for(int i = numbers.length - 2; i >= 0; i--) {
  			int position = (i + 1) % k;
  			if(position == 0) {
  				right[i] = numbers[i];
  			} else {
  				right[i] = right[i+1] > numbers[i] ? right[i+1] : numbers[i];
  			}
  		}
  		return right;
  	}

  	int[] compute(int[] numbers, int k) {
  		int[] left = computeLeft(numbers, k);
  		int[] right = computeRight(numbers, k);
  		int[] result = new int[numbers.length];
  		for(int i = 0, j = k - 1; j < numbers.length; i++, j++) {
  			result[j] = Integer.max(right[i], left[j]);
  		}
  		/*System.out.printf("%s\n%s\n%s\n%s", Arrays.toString(numbers), 
  			Arrays.toString(left), Arrays.toString(right), Arrays.toString(result));*/
  		return result;
  	}
  } 
}