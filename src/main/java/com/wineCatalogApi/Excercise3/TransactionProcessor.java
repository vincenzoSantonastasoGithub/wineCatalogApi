package com.wineCatalogApi.Excercise3;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class TransactionProcessor {
    /** The number of transactions */
    private static final int NUM_TRANSACTIONS = 1000;
    /** The status of a transaction */
    private static enum Status { RUNNING, OK, FAILURE }
    /** The status of transactions */
    private HashMap<Integer, Status> transactionStatus = new HashMap<>();
    /**
     * Perform the complex transaction. This method must be called by the exercise and cannot be changed
     * @param input the input of the transaction
     * @return the output of the transaction
     */
    final protected double doTransaction(double input) throws InterruptedException {
// --- You cannot modify this method ---
        Thread.sleep(10000);
        return input * 100;
    }
    /**
     * Print the number of transactions. This method must be called by the exercise and cannot be changed
     * @param transactions an object describing the transaction status
     */
    final protected void printTransactions(Map<?, Status> transactions) {
// --- You cannot modify this method ---
        EnumMap<Status, Integer> counts = new EnumMap<>(Status.class);
        for (Status s : Status.values()) {
            counts.put(s, 0);
        }
        for (Status s : transactions.values()) {
            counts.put(s, counts.get(s) + 1);
        }
        System.out.printf("- %d Ok transactions, %d Running transactions, "
                        + "%d Failed transactions. Completed percentage: %s%%\n",counts.get(Status.OK), counts.get(Status.RUNNING),
                counts.get(Status.FAILURE),
                (counts.get(Status.OK) + counts.get(Status.FAILURE))
                        * 100.0 / NUM_TRANSACTIONS);
    }
    /**
     * Process all transactions
     * @return the output of all transactions
     */
    public double processTransactions() {
        double result = 0.0;
        for (int i=0; i<NUM_TRANSACTIONS; i++) {
            try {
                transactionStatus.put(i, Status.RUNNING);
                result += doTransaction(i);
                transactionStatus.put(i, Status.OK);
                printTransactions(transactionStatus);
            } catch (InterruptedException ex) {
                System.out.println("Transaction failed");
                transactionStatus.put(i, Status.FAILURE);
            }
        }
        return result;
    }
    /**
     * Main method. Display the result and execution time.
     * @param args (not used)
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        TransactionProcessor tp = new TransactionProcessor();
        double result = tp.processTransactions();
        System.out.printf("The result is: %f . "
                        + "Elapsed time: %s seconds\n",
                result,
                (System.currentTimeMillis() - startTime) / 1000.0);
    }
}