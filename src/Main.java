import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void towerOfHanoiRecursive(int n, char sourceRod, char targetRod, char tempRod){
        if (n == 1) {
            System.out.println("Move disk 1 from rod " + sourceRod + " to rod " + targetRod);
            return;
        }
        towerOfHanoiRecursive(n-1, sourceRod, tempRod, targetRod);
        System.out.println("Move disk " + n + " from rod " + sourceRod + " to rod " + targetRod);
        towerOfHanoiRecursive(n-1, tempRod, targetRod, sourceRod);
    }

    public static void towerOfHanoiIterative(int n){
        Stack<Integer> sourceRod = new Stack<>();
        Stack<Integer> targetRod = new Stack<>();
        Stack<Integer> tempRod = new Stack<>();
        int totalNumOfMoves = (int)Math.pow(2, n) - 1;

        // Put all the disk from the largest first into the source
        for(int i = n; i >= 1; i--){
            sourceRod.push(i);
        }

        // Prints out the initial states
        System.out.println("Initial:");
        printRods(sourceRod, tempRod, targetRod);

        // If the number of disks is even, swap destination and auxiliary rods
        if (n % 2 == 0) {
            for (int i = 1; i <= totalNumOfMoves; i++) {
                if (i % 3 == 1) {
                    moveDisk(sourceRod, tempRod, "source", "temp");
                } else if (i % 3 == 2) {
                    moveDisk(sourceRod, targetRod, "source", "target");
                } else if (i % 3 == 0) {
                    moveDisk(targetRod, tempRod, "target", "temp");
                }
                System.out.println("After move " + i + ":");
                printRods(sourceRod, tempRod, targetRod);
            }
            return;
        }

        for (int i = 1; i <= totalNumOfMoves; i++) {
            if (i % 3 == 1) {
                moveDisk(sourceRod, targetRod, "source", "target");
            } else if (i % 3 == 2) {
                moveDisk(sourceRod, tempRod, "source", "temp");
            } else if (i % 3 == 0) {
                moveDisk(tempRod, targetRod, "temp", "target");
            }
            System.out.println("After move " + i + ":");
            printRods(sourceRod, tempRod, targetRod);
        }
    }
    static void moveDisk(Stack<Integer> src, Stack<Integer> target, String srcName, String targetName) {
        if (!src.isEmpty() && (target.isEmpty() || src.peek() < target.peek())) {
            target.push(src.pop());
            System.out.println("Move the disk " + target.peek() + " from " + srcName + " to " + targetName);
        } else if (!target.isEmpty() && (src.isEmpty() || target.peek() < src.peek())) {
            src.push(target.pop());
            System.out.println("Move the disk " + src.peek() + " from " + targetName + " to " + srcName);
        }
    }

    static void printRods(Stack<Integer> sourceRod, Stack<Integer> tempRod, Stack<Integer> targetRod) {
        System.out.println("Source Rod: " + sourceRod);
        System.out.println("Temp Rod: " + tempRod);
        System.out.println("Target Rod: " + targetRod);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Recursive / Iterative? (r/i):");
        String userInput = scanner.nextLine().toLowerCase();
        System.out.println("Number of disks:");
        int numOfDisk = scanner.nextInt();
        if(userInput.equals("r")){
            towerOfHanoiRecursive(numOfDisk, 'A', 'C', 'B');
        } else if(userInput.equals("i")){
            towerOfHanoiIterative(numOfDisk);
        } else{
            System.out.println("Invalid Input!");
        }
    }
}