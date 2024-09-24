package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import AtmSystem.Account;
import AtmSystem.CheckingAccount;
import AtmSystem.Customer;
import AtmSystem.SavingsAccount;

public class CustomerTest {
    CheckingAccount acc1 = new CheckingAccount(100, 1, 80, 10);
    CheckingAccount acc2 = new CheckingAccount(100, 2, 50, 100);
    SavingsAccount acc3 = new SavingsAccount(10, 3, 1000, 0.08, 30);
    SavingsAccount acc4 = new SavingsAccount(0, 4, 10000, 0.08, 30);
    Account[] accAll = { acc1, acc2, acc3, acc4 };

    @Test
    public void testGetCustomerID1() {
        // Test to get the customerId in a customer object
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, accAll, 9999, 1234);
        assertEquals(1, customer.getCustomerID());
    }

    @Test
    public void testGetCustomerID2() {
        // Test to get the customerId in a customer object
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 9, accAll, 9999, 1234);
        assertEquals(9, customer.getCustomerID());
    }

    @Test
    public void testGetCustomerID3() {
        // Test to get the customerId in a customer object
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 100, accAll, 9999, 1234);
        assertEquals(100, customer.getCustomerID());
    }

    @Test
    public void testCardNumber1() {
        // Test to get the card number of a customer
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 100, accAll, 9999, 1234);
        assertEquals(9999, customer.getCardNumber());
    }

    @Test
    public void testCardNumber2() {
        // Test to get the card number of a customer
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 100, accAll, 8585, 1234);
        assertEquals(8585, customer.getCardNumber());
    }

    @Test
    public void testCardNumber3() {
        // Test to get the card number of a customer
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 100, accAll, 123456789, 1234);
        assertEquals(123456789, customer.getCardNumber());
    }

    @Test
    public void testGetPin1() {
        // Test to get the pin of a customer
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, accAll, 9999, 1234);
        assertEquals(1234, customer.getPin());
    }

    @Test
    public void testGetPin2() {
        // Test to get the pin of a customer
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, accAll, 9999, 5555);
        assertEquals(5555, customer.getPin());
    }

    @Test
    public void testGetPin3() {
        // Test to get the pin of a customer
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, accAll, 9999, 6565);
        assertEquals(6565, customer.getPin());
    }

    @Test
    public void testGetAccount1() {
        // Test to get the account of a customer
        Account[] accAll = { acc1, acc2, acc3, acc4 };
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, accAll, 9999, 1234);
        assertArrayEquals(accAll, customer.getAccounts());
    }

    @Test
    public void testGetAccount2() {
        // Test to get the account of a customer
        Account[] acc = { acc1, acc2, acc3 };
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, acc, 9999, 1234);
        assertArrayEquals(acc, customer.getAccounts());
    }

    @Test
    public void testSetPin1() {
        // test for same pin as old
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, accAll, 9999, 1234);
        assertEquals(false, customer.setPin(1234, 1234));
    }

    @Test
    public void testSetPin2() {
        // test for diff pin as old
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, accAll, 9999, 1234);
        customer.setPin(1234, 4321);
        assertEquals(4321, customer.getPin());
    }

    @Test
    public void testSetPin3() {
        // test for new pin more than 4 ints
        Customer customer = new Customer("John Doe", "123 Nowhere St.", "123-456-7890", 1, accAll, 9999, 1234);
        customer.setPin(1234, 12345);
        assertEquals(false, customer.setPin(1234, 12345));
    }
}