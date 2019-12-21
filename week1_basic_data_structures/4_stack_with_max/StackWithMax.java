import java.util.*;
import java.io.*;

public class StackWithMax {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        ImprovedStack stack = new ImprovedStack();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
            } else if ("pop".equals(operation)) {
                stack.pop();
            } else if ("max".equals(operation)) {
                //System.out.println(Collections.max(stack));
                System.out.println(stack.max());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }

    private static class ImprovedStack {
        Stack<Integer> dataStack = new Stack<Integer>();
        Stack<Integer> maxStack = new Stack<Integer>();

        void push(int val) {
            if(dataStack.isEmpty() && maxStack.isEmpty()) {
                dataStack.push(val);
                maxStack.push(val);
            } else {
                dataStack.push(val);
                maxStack.push(Integer.max(maxStack.peek(), val));
            }
        }

        void pop() {
            if(!dataStack.isEmpty() && !maxStack.isEmpty()) {
                dataStack.pop();
                maxStack.pop();
            }
        }

        String max() {
            if(!maxStack.isEmpty()) {
                return "" + maxStack.peek();
            }
            return "";
        }
    }
}
