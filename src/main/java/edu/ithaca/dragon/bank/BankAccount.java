package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;
    private double balance2;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance,double secondBalance){
        if  (!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("amount not valid");
        }
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
            this.balance2 = secondBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public double getBalance2(){
        return balance2;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if  (!isAmountValid(amount)){
            throw new InsufficientFundsException("Not enough money");
        }
        if (amount <= 0){
            throw new InsufficientFundsException("Not enough money");
        }
        if (amount <= balance){
            balance -= amount;
        }
        
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }
    //Increases the balance by amount if amount is nonegative and does not have fraction of cents
    public void deposit (double amount) throws IllegalArgumentException{
        if  (!isAmountValid(amount)){
            throw new IllegalArgumentException("invalid amount");
        }
        else{
            balance +=amount;
        }
    }

    public static boolean isEmailValid(String email){
        //Check if @ exits
        if (email.indexOf('@') == -1){
            return false;
        }
        //Check if . extists
        if (email.indexOf('.') == -1){
            return false;
        }
        //Cant have double dash
        if (email.indexOf("--") != -1){
            return false;
        }
        //check for two terms after .
        if (email.length()-email.indexOf('.')-1 <= 1){
             return false;
        }
        //Check location of @ to ensure start of email
        if (email.indexOf('@') == 0){
            return false;
        }
        //check to see if domain exists between @ and  .
        if (email.indexOf('@')-email.indexOf('.') == 0){
            return false;
        }
        //All cases passed good email
        else {
            return true;
        }
    }

    public static boolean isAmountValid(Double amount){
        //Check for no fraction of cents
        if((amount*100)%(1) != 0){
            return false;
        }
        //Check if positive
        if(amount<0){
            return false;
        }
        //Cases passed return true
        else{
            return true;
        }
    }

    public void transfer(Double amount) throws InsufficientFundsException{
        withdraw(amount);
        balance2 +=amount;
    }
}
