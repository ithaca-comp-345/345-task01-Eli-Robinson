package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        //Positive Value
        BankAccount bankAccount = new BankAccount("a@b.com", 200,0);
        assertEquals(200, bankAccount.getBalance());
        //0
        BankAccount bankAccount2 = new BankAccount("a@b.com", 0,0);
        assertEquals(0, bankAccount2.getBalance());
        //negative value
        BankAccount bankAccount3 = new BankAccount("a@b.com", -200,0);
        assertEquals(-200, bankAccount3.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200,0);
        //Standard
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());
        //test when money > balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        //Test for when account -> 0
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());
        //test account at 0
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(100));
        //Test withdrawing negative
        assertThrows(InsufficientFundsException.class,()->bankAccount.withdraw(-100));

    }

    @Test
    void transferTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200,200);
        bankAccount.transfer(100.);
        //Check base case
        assertEquals(100, bankAccount.getBalance());
        assertEquals(300, bankAccount.getBalance2());
        //Check for exceptions
        assertThrows(InsufficientFundsException.class,()->bankAccount.transfer(-100.));
        assertThrows(InsufficientFundsException.class,()->bankAccount.transfer(95.111));


    }


    @Test
    void depositTest() throws IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200,0);
        //default true
        bankAccount.deposit(100);
        assertEquals(300, bankAccount.getBalance());
        //bad numbers
        assertThrows(IllegalArgumentException.class,()->bankAccount.deposit(-100));
        assertThrows(IllegalArgumentException.class,()->bankAccount.deposit(100.111));
        
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse(BankAccount.isEmailValid(""));
        //Cannot have two dashes in a row
        assertFalse(BankAccount.isEmailValid("a--b@c.com"));
        assertTrue(BankAccount.isEmailValid("a-b@c.com"));
        //The doman must exist and be two or more characters
        assertFalse(BankAccount.isEmailValid("a@b.c"));
        assertTrue(BankAccount.isEmailValid("a@b.cc"));
        assertFalse(BankAccount.isEmailValid("a@b."));
        //Front must exist
        assertFalse(BankAccount.isEmailValid("@b.c"));
        assertTrue(BankAccount.isEmailValid("a@b.cc"));
        //@ must exist
        assertFalse(BankAccount.isEmailValid("ab.cc"));
        assertTrue(BankAccount.isEmailValid("a@b.cc"));
        //middle must exist
        assertFalse(BankAccount.isEmailValid("a@b.c"));
        assertTrue(BankAccount.isEmailValid("a@.cc"));
        //. must exist
        assertFalse(BankAccount.isEmailValid("a@bc"));
        assertTrue(BankAccount.isEmailValid("a@b.cc"));

    }

    @Test
    void isAmountValidTest(){
        //Positive numbers work
        assertTrue(BankAccount.isAmountValid(100.));
        //0
        assertTrue(BankAccount.isAmountValid(0.));
        //One Decimal works
        assertTrue(BankAccount.isAmountValid(100.5));
        //Two Decimal
        assertTrue(BankAccount.isAmountValid(100.55));
        //Three Decimal
        assertFalse(BankAccount.isAmountValid(100.555));
        //Negatve
        assertFalse(BankAccount.isAmountValid(-100.));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200,0);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100,0));
        //Check for amount exception with negative and too many decimals
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.cc", -100,0));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.cc", 100.111,0));
    }

}