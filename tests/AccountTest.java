package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import AtmSystem.Account;

public class AccountTest {

    @Test
    public void testGetBalance1() {
        Account account = new Account(200, 1, 500);
        assertEquals(200, account.getBalance(), 0.001);
    }

    @Test
    public void testGetBalance2() {
        Account account = new Account(500, 1, 500);
        assertEquals(500, account.getBalance(), 0.001);
    }

    @Test
    public void testGetAccountID1() {
        Account account = new Account(200, 1, 500);
        assertEquals(1, account.getAccID());
    }

    @Test
    public void testGetAccountID2() {
        Account account = new Account(200, 5, 500);
        assertEquals(5, account.getAccID());
    }

    @Test
    public void testGetWithdrawLimit1() {
        Account account = new Account(200, 1, 500);
        assertEquals(500, account.getWithdrawLimit(), 0.001);
    }

    @Test
    public void testGetWithdrawLimit2() {
        Account account = new Account(200, 1, 1000);
        assertEquals(1000, account.getWithdrawLimit(), 0.001);
    }

    @Test
    public void testSetBalance1() {
        Account account = new Account(200, 1, 1000);
        account.setBalance(500);
        assertEquals(500, account.getBalance(), 0.001);
    }

    @Test
    public void testSetBalance2() {
        Account account = new Account(200, 1, 1000);
        account.setBalance(1000);
        assertEquals(1000, account.getBalance(), 0.001);
    }

    @Test
    public void testSetWithdrawLimit1() {
        Account account = new Account(200, 1, 1000);
        account.setWithdrawLimit(500);
        assertEquals(500, account.getWithdrawLimit(), 0.001);
    }

    @Test
    public void testSetWithdrawLimit2() {
        Account account = new Account(200, 1, 1000);
        account.setWithdrawLimit(1200);
        assertEquals(1200, account.getWithdrawLimit(), 0.001);
    }

}