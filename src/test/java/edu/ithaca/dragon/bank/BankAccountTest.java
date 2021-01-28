package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());
        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount2.getBalance());
        BankAccount bankAccount3 = new BankAccount("a@b.com", -200);
        assertEquals(-200, bankAccount3.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
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
        assertFalse(BankAccount.isEmailValid("a@b.c"));
        assertTrue(BankAccount.isEmailValid("ab.cc"));
        //middle must exist
        assertFalse(BankAccount.isEmailValid("a@b.c"));
        assertTrue(BankAccount.isEmailValid("a@.cc"));
        //. must exist
        assertFalse(BankAccount.isEmailValid("a@bc"));
        assertTrue(BankAccount.isEmailValid("a@b.cc"));

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}