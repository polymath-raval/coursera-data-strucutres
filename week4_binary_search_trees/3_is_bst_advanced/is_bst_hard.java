import java.util.*;
import java.io.*;

public class is_bst_hard {
    private static class InvalidTreeException extends Exception {
        InvalidTreeException(String msg) {
            super(msg);
        }
    }

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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            if(tree == null || tree.length == 0)
                return true;
            List<Integer> result = new ArrayList<>();
            try {
                inOrderTraversal(0, result, new HashSet<Integer>());
            } catch(InvalidTreeException e) {
                return false;
            }
            for(int i = 1; i < result.size(); i++) {
                if(result.get(i - 1) > result.get(i))
                    return false;
            }
            return true;
        }

        void inOrderTraversal(int root, List<Integer> result, Set<Integer> leftTurns) throws InvalidTreeException {
            if(root == -1)
                return;
            if(leftTurns.contains(tree[root].key))
                throw new InvalidTreeException("");
            
            leftTurns.add(tree[root].key);
            inOrderTraversal(tree[root].left, result, leftTurns);
            leftTurns.remove(tree[root].key);
            result.add(tree[root].key);
            inOrderTraversal(tree[root].right, result, leftTurns);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
