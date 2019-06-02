package View;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.Controller;
import Integration.Printer;
import Integration.RegistryCreator;
import Integration.SystemCreator;

class ViewTest 
{
	private View instance;
	private RegistryCreator regCreator;
	private SystemCreator sysCreator;
    ByteArrayOutputStream printout;
    PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() throws IOException
    {
		Printer printer = new Printer();
	    regCreator = new RegistryCreator();
	    sysCreator = new SystemCreator();
        Controller contr = new Controller(regCreator, sysCreator, printer);
        instance = new View(contr);
        
        printout = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printout);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() 
    {
        System.setOut(originalSysOut);
        printout = null;
    }

    @Test
    public void testRunFakeSale() 
    {
        instance.runFakeSale();
        String actualResult = printout.toString();
        String expectedResult = "started";
        assertTrue(actualResult.contains(expectedResult), "Wrong printout after calling startNewSale().");
    }
}