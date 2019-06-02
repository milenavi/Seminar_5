package Startup;

import Controller.Controller;
import Integration.RegistryCreator;
import Integration.SystemCreator;
import Integration.Printer;
import View.View;

/**
 * Contains the <code>main</code> method. Performs all startup of
 * the application.
 */
public class Main
{
    public static void main(String[] args)
    {
        RegistryCreator regCreator = new RegistryCreator();
        SystemCreator sysCreator = new SystemCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(regCreator, sysCreator, printer);
        View view = new View(contr);
        //view.sampleExecution();
        view.runFakeSale();
    }
}