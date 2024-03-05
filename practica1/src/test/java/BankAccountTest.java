import org.junit.jupiter.api.*;
import bank.BankAccount;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

public class BankAccountTest 
{
    BankAccount bc;

    @BeforeEach
    public void setup()
    {
        // Arrange
        int startingBalance = 300;
        bc = new BankAccount(startingBalance);
    }
    
    /**
     * Crear un BankAccount con un cierto startingBalance supone que tras su creación 
     * debería de poseer el balance establecido y con la función getBalance() se podría ver.
     */
    @Test 
    public void InitialBalanceIsTheIndicatedInConstructor_Test()
    {
        // Arrange
        int expected = 300;

        // Act
        int output = bc.getBalance();

        // Assert
        assertEquals(expected, output);
    }

    /**
     * Realizar un deposito de una cierta cantidad en la cuenta supone que 
     * el valor actual del balance debe de ser el anterior + el añadido.
     */
    @Test
    public void BalanceAfterADepositIsCorrect_Test()
    {
        // Arrange
        int expected = 400;
        int amount = 100;

        // Act
        int output1 = bc.deposit(amount);
        int output2 = bc.getBalance();
        
        // Assert
        assertEquals(expected, output1);
        assertEquals(expected, output2);
    }

    /**
     * Realizar un depósito de 0 amount implica que el balance 
     * de la cuenta sea el mismo que antes de depósito.
     */
    @Test
    public void BalanceAfterAZeroAmountDepositIsTheSame_Test()
    {
        // Arrange
        int expected = bc.getBalance();
        int amount = 0;

        // Act
        int output1 = bc.deposit(amount);
        int output2 = bc.getBalance();
        
        // Assert
        assertEquals(expected, output1);
        assertEquals(expected, output2);
    }

    /**
     * Realizar un deposito de amount negativo implica que 
     * se produzca una excepción debido a que es un argumento erróneo.
     */
    @Test 
    public void DepositNegativeValuesAreNotAccepted_Test()
    {
        // Arrange
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;
        int amount = -10;
        String expectedMsg = "Amount cannot be negative";

        // Act
        Executable input = () -> bc.deposit(amount);

        // Assert
        assertThrows(expected, input, expectedMsg);
    }

    /**
     * Realizar una retirada de dinero de la cuenta de una cierta cantidad
     * implica que el balance actual de la cuenta será el que tenía antes menos el retirado.
     */
    @Test
    public void BalanceAfterAWithdrawIsCorrect_Test()
    {
        // Arrange
        int amount = 200;
        int expected = 100;

        // Act
        boolean output1 = bc.withdraw(amount);
        int output2 = bc.getBalance();

        // Assert
        assertTrue(output1);
        assertEquals(expected, output2);
    }

    /**
     * Realizar una retirada de cero amount de dinero implica que 
     * el balance de la cuenta no cambie.
     */
    @Test
    public void BalanceAfterAZeroAmountWithdrawIsCorrect_Test()
    {
        // Arrange
        int amount = 0;
        int expected = bc.getBalance();

        // Act
        boolean output1 = bc.withdraw(amount);
        int output2 = bc.getBalance();

        // Assert
        assertTrue(output1);
        assertEquals(expected, output2);
    }

    /**
     * Realizar una retirada de justo el balance actual de la cuenta
     * implica que el balance se quede a cero.
     */
    @Test
    public void BalanceAfterTheCompleteBalanceWithdrawIsZero_Test()
    {
        // Arrange
        int amount = bc.getBalance();
        int expected = 0;

        // Act
        boolean output1 = bc.withdraw(amount);
        int output2 = bc.getBalance();

        // Assert
        assertTrue(output1);
        assertEquals(expected, output2);
    }

    /**
     * Realizar una retirada de más que el balance actual de la cuenta
     * implica que no se retire nada y que se devuelva false.
     */
    @Test 
    public void MoreThanAccountBalanceWithdrawIsNotPossible_Test()
    {
        // Arrange
        int amount = bc.getBalance() + 10;
        int expected = bc.getBalance();

        // Act
        boolean output1 = bc.withdraw(amount);
        int output2 = bc.getBalance();

        // Assert
        assertFalse(output1);
        assertEquals(expected, output2);
    }

    /**
     * Realizar una llamada a Payment con unos valores aceptables proporciona
     * la salida que debe ser con un delta de 0.001 
     */
    @Test 
    public void NormalPaymentGivesCorrectOutput_Test()
    {
        // Arrange
        double total_amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        double expected = 838.759;

        // Act 
        double output = bc.payment(total_amount, interest, npayments);

        // Assert
        assertEquals(expected, output, 0.001);
    }

    /**
     * Realiza la llamada a Pending con unos valores aceptables proporciona
     * la salida que debe ser con un delta de 0.001
     */
    @Test 
    public void NormalPendingGivesCorrectOutput_Test()
    {
        // Arrange
        double amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        int month = 1;
        double expected = 9171.24;
 
        // Act 
        double output = bc.pending(amount, interest, npayments, month);
 
        // Assert
        assertEquals(expected, output, 0.001);
    }

    /**
     * Si a Pending se establece como parámetro month cero,
     * el output debe ser el parámetro amount introducido
     */
    @Test
    public void ZeroMonthInPendingGivesOutputEqualsToAmount_Test()
    {
        // Arrange
        double amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        int month = 0;
        double expected = amount;
 
        // Act 
        double output = bc.pending(amount, interest, npayments, month);
 
        // Assert
        assertEquals(expected, output);
    }
}
