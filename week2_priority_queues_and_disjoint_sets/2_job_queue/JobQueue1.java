import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class JobQueue1 {

    private static class Processor implements Comparable<Processor>{
        int processorId;
        long startTime;
        Processor(int _p, long _s) {
            this.processorId = _p;
            this.startTime = _s;
        }

        public int compareTo(Processor other) {
            int startTimeCompare = Long.compare(this.startTime, other.startTime);
            if(startTimeCompare != 0)
                return startTimeCompare;
            else
                return Integer.compare(this.processorId, other.processorId);
        }
    }

    private Processor[] assignJobsOptimized(int numWorkers, int numberOfJobs ,int[] jobs) {
        Processor[] result = new Processor[numberOfJobs];
        PriorityQueue<Processor> processors = new PriorityQueue<>();
        for(int i = 0; i < numWorkers; i++) {
            processors.add(new Processor(i, 0));
        }
        for(int i = 0; i < numberOfJobs; i++) {
            Processor bestProcessor = processors.poll();
            result[i] = bestProcessor;
            processors.add(new Processor(bestProcessor.processorId, bestProcessor.startTime + jobs[i]));
        }
        return result;
    }

    /*private AssignedJob[] assignJobs(int numWorkers, int numberOfJobs ,int[] jobs) {
        // TODO: replace this code with a faster algorithm.
        AssignedJob[] result = new AssignedJob[numberOfJobs];
        int[] assignedWorker = new int[jobs.length];
        long[] startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            result[i] = new AssignedJob(bestWorker, nextFreeTime[bestWorker]);
            nextFreeTime[bestWorker] += duration;
        }
        return result;
    }*/

    

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void writeResponse(Processor[] assignedJobs) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i = 0; i < assignedJobs.length; ++i) {
            out.println(assignedJobs[i].processorId + " " + assignedJobs[i].startTime);
        }
        out.close();

    }

    public void solve() throws IOException {
        FastScanner in = new FastScanner();
        int numWorkers = in.nextInt();
        int numberOfJobs = in.nextInt();
        int[] jobs = new int[numberOfJobs];
        for (int i = 0; i < numberOfJobs; ++i) {
            jobs[i] = in.nextInt();
        }
        Processor[] assignedJobs = assignJobsOptimized(numWorkers, numberOfJobs, jobs);
        writeResponse(assignedJobs);
    }

}
