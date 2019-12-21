import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
            // Replace this code with a faster implementation
			//System.out.println(n);
			//System.out.println(Arrays.toString(parent));
			List<Integer>[] tree = (ArrayList<Integer>[])new ArrayList[n];
			for(int i = 0; i < tree.length; i++)
				tree[i] = new ArrayList<>();
			int root = -1;
			for(int i = 0; i < parent.length; i++) {

				if(parent[i] == -1) 
					root = i;
				else
					tree[parent[i]].add(i);
			}

			return height(tree, root);
		}

		int height(List<Integer>[] tree, int node) {
			int maxHeight = 0;
			for(int child : tree[node]) {
				maxHeight = Integer.max(maxHeight, height(tree, child));
			}
			return 1 + maxHeight;
		}

		int computeHeight1() {
                        // Replace this code with a faster implementation
			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
