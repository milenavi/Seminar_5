package Integration;

import Model.Receipt;

/**
 * The printer is used for all printouts of receipts.
 */
public class Printer
{
    /**
     * Creates an instance represented as a printer.
     */
    public Printer() {
        
    }
    
    /**
     * Prints the specified receipt.
     *
     * @param receipt The specified receipt that shall be printed.
     */
    public void printReceipt(Receipt receipt)
    {
        System.out.println("\n\nTHIS IS THE RECEIPT BEEING PRINTED: \n" + receipt.createReceiptString());
    }
}