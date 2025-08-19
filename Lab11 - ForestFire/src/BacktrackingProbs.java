import java.util.*;

public class BacktrackingProbs {

    public static void printBinary(int digits) {
        printBinaryHelper(digits, "");
    }

    private static void printBinaryHelper(int digits, String soFar) {
        if(digits == soFar.length()) {
            System.out.print(soFar + " ");
        }

        else {
            printBinaryHelper(digits, soFar + 0);
            printBinaryHelper(digits, soFar + 1);
        }
    }

    public static void climbStairs(int steps) {
        climbStairsHelper(steps, "");
    }

    private static void climbStairsHelper(int steps, String soFar) {
        if(steps < 0) {
            return;
        }
        if(steps == 0) {
            System.out.println(soFar.substring(0, soFar.length() - 2) + " ");
        }

        else {
            climbStairsHelper(steps - 1, soFar + "1, ");
            climbStairsHelper(steps - 2, soFar + "2, ");
        }
    }

    public static void campsite(int x, int y) {
        campsiteHelper(x,y,"");
    }

    private static void campsiteHelper(int x, int y, String soFar) {
        if(x < 0 || y < 0) {
            return;
        }
        if(x == 0 && y == 0) {
            System.out.println(soFar + " ");
        }
        else {
            campsiteHelper(x-1,y,soFar + "E ");
            campsiteHelper(x-1,y-1,soFar + "NE ");
            campsiteHelper(x,y-1,soFar + "N ");
        }
    }

    public static int getMax(List<Integer> nums, int limit) {
        return getMaxHelper(nums, 0, limit, 0);
    }

    public static int getMaxHelper(List<Integer> nums, int i, int limit, int soFar) {
        if (i >= nums.size() || soFar > limit) {
            return 0;
        }

        int include = nums.get(i) + getMaxHelper(nums, i + 1, limit, soFar + nums.get(i));
        int skip = getMaxHelper(nums, i + 1, limit, soFar);

        if (soFar + nums.get(i) > limit) {
            include = 0;
        }

        return Math.max(include, skip);
    }

    public static int makeChange(int amount) {
        ArrayList<Integer> coins = new ArrayList<Integer>();
        coins.add(1);coins.add(5);coins.add(10);coins.add(25);
        return makeChangeHelper(coins, 0, 0, amount);
    }

    public static int makeChangeHelper(List<Integer> coins, int i, int total, int amount) {
        if (total == amount) {
            return 1;
        }
        if (total > amount || i >= coins.size()) {
            return 0;
        }

        int currentCoin = makeChangeHelper(coins, i, total + coins.get(i), amount);
        int nextCoins = makeChangeHelper(coins, i + 1, total, amount);

        return currentCoin + nextCoins;
    }

    public static void printChange(int amount) {
        ArrayList<Integer> coins = new ArrayList<Integer>();
        coins.add(1);coins.add(5);coins.add(10);coins.add(25);
        ArrayList<Integer> currentCoins = new ArrayList<Integer>();
        currentCoins.add(0);currentCoins.add(0);currentCoins.add(0);currentCoins.add(0);
        HashSet<String> hs = new HashSet<>();
        printChangeHelper(coins, 0, currentCoins, amount, 0, hs);

        System.out.println(" P  N  D  Q\n------------");
        for(String s: hs) {
            System.out.println(s);
        }
    }


    public static void printChangeHelper(List<Integer> coins, int i, List<Integer> currentCoins, int amount, int total, HashSet<String> hs) {
        if (total == amount) {
            hs.add(currentCoins.toString());
            return;
        }
        if (total > amount || i >= coins.size()) {
            return;
        }
        ArrayList<Integer> skip = new ArrayList<Integer>(currentCoins);
        ArrayList<Integer> add = new ArrayList<Integer>(currentCoins);  add.set(i, add.get(i) + 1);
        printChangeHelper(coins, i, add, amount, total + coins.get(i), hs);
        printChangeHelper(coins, i + 1, skip, amount, total, hs);
    }


    public static String longestCommonSub(String a, String b) {
        return longestCommonSubHelper(a, b, 0, 0);
    }

    private static String longestCommonSubHelper(String a, String b, int i, int j) {
        if (i == a.length() || j == b.length()) {
            return "";
        }

        if (a.charAt(i) == b.charAt(j)) {
            return a.charAt(i) + longestCommonSubHelper(a, b, i + 1, j + 1);
        }
        else {
            String skipA = longestCommonSubHelper(a, b, i + 1, j);
            String skipB = longestCommonSubHelper(a, b, i, j + 1);

            if (skipA.length() > skipB.length()) {
                return skipA;
            }
            else {
                return skipB;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("printBinary tests");
        printBinary(3);System.out.println();
        printBinary(2);System.out.println();
        printBinary(1);System.out.println("\n");

        System.out.println("climbStairs tests");
        climbStairs(4);System.out.println();
        climbStairs(2);System.out.println();
        climbStairs(1);System.out.println();

        System.out.println("campsite tests");
        campsite(2,1);System.out.println();
        campsite(2,2);System.out.println();
        campsite(1,1);System.out.println();

        System.out.println("getMax tests");
        System.out.println(getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19));
        System.out.println(getMax(Arrays.asList(4,3,2,1), 8));

        System.out.println("makeChange tests");
        System.out.println(makeChange(25));
        System.out.println(makeChange(100));

        System.out.println("\nprintChange tests");
        System.out.println("printChange(11):");printChange(11);
        System.out.println("\nprintChange(10):"); printChange(10);

        System.out.println("\nlongestCommonSub tests");
        System.out.println(longestCommonSub("hello", "hollow"));
        System.out.println(longestCommonSub("ABCDEFG", "BGCEHAF"));
    }
}