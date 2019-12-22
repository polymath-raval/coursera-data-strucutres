import java.io.*;
import java.util.*;

public class MergingTables1 {
    int[] parents;
    int[] ranks;
    int[] numberOfRows;
    long maxNumberOfRows;

    public MergingTables1(int[] numberOfRows, int maximumNumberOfRows) {
        this.parents = setParents(numberOfRows.length);
        this.ranks = new int[numberOfRows.length];
        this.numberOfRows = numberOfRows;
        this.maxNumberOfRows = maximumNumberOfRows;
    }

    private int[] setParents(int n) {
        int[] _p = new int[n];
        for(int i = 0; i < n; i++)
            _p[i] = i;
        return _p;
    }

    int getParent(int node) {
        if(parents[node] != node) {
            parents[node] = getParent(parents[node]);
        }
        return parents[node];
    }

    void merge(int i, int j) {
        int p_i = getParent(i);
        int p_j = getParent(j);
        if(p_i != p_j) {
            if(ranks[p_i] > ranks[p_j]) {
                parents[p_j] = p_i;
                numberOfRows[p_i] += numberOfRows[p_j];
                numberOfRows[p_j] = 0;
                maxNumberOfRows = Math.max(maxNumberOfRows, numberOfRows[p_i]);
            } else {
                parents[p_i] = p_j;
                numberOfRows[p_j] += numberOfRows[p_i];
                numberOfRows[p_i] = 0;
                maxNumberOfRows = Math.max(maxNumberOfRows, numberOfRows[p_j]);
                if(ranks[p_i] == ranks[p_j]) {
                    ranks[p_j]++;
                }
            }
        }
    }

    

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        
        int n = reader.nextInt();
        int m = reader.nextInt();
        int maximumNumberOfRows = -1;
        int[] numberOfRows = new int[n];
        for (int i = 0; i < n; i++) {
            numberOfRows[i] = reader.nextInt();
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows[i]);
        }
        MergingTables1 solution = new MergingTables1(numberOfRows, maximumNumberOfRows);

        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            solution.merge(source, destination);
            writer.printf("%d\n", solution.maxNumberOfRows);
        }
        writer.writer.flush();
    }

}
