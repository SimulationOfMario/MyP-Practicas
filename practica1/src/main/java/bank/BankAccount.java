package bank;

public class BankAccount 
{
    private int balance = 0;

    public BankAccount(int startingBalance) 
    {
        this.balance = startingBalance;
    }

    public boolean withdraw(int amount) 
    {
        if (amount < 0)
            throw new IllegalArgumentException("Amount cannot be negative");

        if(balance >= amount) 
        {
            balance -= amount;
            return true;
        }
        return false;
    }

    public int deposit(int amount) 
    {
        if (amount < 0)
            throw new IllegalArgumentException("Amount cannot be negative");

        balance += amount;
        return balance;
    }

    public int getBalance() 
    {
        return balance;
    }

    // Calculate the payment per month for a loan
    public double payment(double total_amount, double interest, int npayments)
    {
        if(interest <= 0) 
            throw new IllegalArgumentException("Interest cannot be negative or zero");

        if(npayments <= 0) 
            throw new IllegalArgumentException("Number of payments cannot be negative or zero");
        
        if(total_amount < 0) 
            throw new IllegalArgumentException("Total amount cannot be negative");

        return total_amount * (interest * Math.pow((1 + interest), npayments) / (Math.pow((1 + interest), npayments) - 1));
    }

    // Calculate the pending amount for a loan in a month
    public double pending(double amount, double inte, int npayments, int month)
    {
        if(month < 0)
            throw new IllegalArgumentException("Month value cannot be negative");
        
        double res;

        if(month == 0) 
        {
            res = amount;
        }
        else if (month > npayments && npayments > 0)
        {
            res = 0;
        }
        else
        {
            double ant = pending(amount, inte, npayments, month - 1);
            res = ant - (payment(amount, inte, npayments) - inte * ant);
        }
        
        return res;
    }
}
