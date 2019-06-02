package com.wineCatalogApi.Excercise3;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class TransactionProcessorOptimizated {
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

    private double handleTransaction (int i) {
        double result = 0.0;
        try {
            transactionStatus.put(i, Status.RUNNING);
            result = doTransaction(i);
            transactionStatus.put(i, Status.OK);
        } catch (InterruptedException ex) {
            System.out.println("Transaction failed");
            transactionStatus.put(i, Status.FAILURE);
        }
        return result;
    }

    /**
     * Process all transactions
     * @return the output of all transactions
     */
    public double processTransactions() {
        List batchArray;
        double result = 0.0;
        int batchSize = 200;
        for (int i=0; i<NUM_TRANSACTIONS; i += batchSize) {
            batchArray = buildBatchArray(batchSize, i);
            ForkJoinPool customThreadPool = new ForkJoinPool(220);
            try {
                List finalBatchArray = batchArray;
                result+= (double) customThreadPool.submit( () ->
                        finalBatchArray.parallelStream().collect(Collectors.summingDouble(index-> handleTransaction((Integer) index)))).get();
            } catch (InterruptedException | ExecutionException  e) {
                System.out.printf("Transaction batch  [%d to %d]failed", batchArray.get(0), batchArray.size() - 1);
                batchArray.forEach((k)->{
                    transactionStatus.put((Integer)k, Status.FAILURE);
                });
            }
            printTransactions(transactionStatus);
        }
        return result;
    }

    /**
     * method build an array containing the transactions to process
     * @param batchSize
     * @param i
     * @return
     */
    private List buildBatchArray(int batchSize, int i) {
        List batchArray;
        batchArray = new ArrayList<Integer>();
        for (int j=i; j< i+batchSize; j++) {
            batchArray.add(j);
        }
        return batchArray;
    }

    /**
     * Main method. Display the result and execution time.
     * @param args (not used)
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        TransactionProcessorOptimizated tp = new TransactionProcessorOptimizated();
        double result = tp.processTransactions();
        System.out.printf("The result is: %f . "
                        + "Elapsed time: %s seconds\n",
                result,
                (System.currentTimeMillis() - startTime) / 1000.0);
    }
}