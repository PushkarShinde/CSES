import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  static char[][] board=new char[8][8];
  static boolean[] column=new boolean[8];
  static boolean[] diag1=new boolean[15];//row-col+7
  static boolean[] diag2=new boolean[15];//row+col
  static int count=0;
    public static void main(String[] args) throws Exception {
        for(int i=0;i<8;i++){
            board[i]=in.nextLine().toCharArray();
        }
        backtrack(0);
        System.out.println(count);
        out.flush();
    }

    private static void backtrack(int row){
        if(row==8){
            count++;
            return;
        }
        for(int col=0;col<8;col++){
            if(board[row][col]=='.' && !column[col] &&
                !diag1[row-col+7] && !diag2[row+col]
            ){
                column[col]=true;
                diag1[row-col+7]=true;
                diag2[row+col]=true;
                backtrack(row+1);
                column[col]=false;
                diag1[row-col+7]=false;
                diag2[row+col]=false;
            }
        }
    }

    // Fast I/O template
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // GCD
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // LCM
    static long lcm(long a, long b) {
        return a/gcd(a,b)*b;
    }

    // Sieve of Eratosthenes
    static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i*i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i*i; j <= n; j += i) isPrime[j] = false;
            }
        }
        return isPrime;
    }

    // Binary Search Template
    static int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l+(r-l)/ 2;
            if (arr[m] == target) return m;
            else if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }

}