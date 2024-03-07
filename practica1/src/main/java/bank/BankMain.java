package bank;

public class BankMain 
{

    public static void main(String args[])
    {
        BankAccount bank = new BankAccount(10);
        double total_amount = 10000; 
        double interes = 0.01;
        int npayments = 12;
        int month = 13;

        System.out.printf("Load payment of amount %f, with interes %f and in %d months is: %f\n", total_amount, 
            interes, npayments, bank.payment(total_amount, interes, npayments));

        System.out.printf("Load pending payment of amount %f, with interes %f, %d months, in month %d is: %f\n", total_amount, 
            interes, npayments, month, bank.pending(total_amount, interes, npayments, month));
    }

    
}
