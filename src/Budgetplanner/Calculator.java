package Budgetplanner;

//imports
import javax.swing.*;
import java.awt.*;

/**
 * This is a budget tracker and calculator that can be used
 * to keep track of your monthly budget and help you stay within your
 * budget and save money as well.
 */

public class Calculator {

    //global variable
    private static double yearlyIncome;//after taxes
    private static double studentLoans;


    /**
     *  Welcome message output when program is started
     *
     */
    private static void welcomeMessage(){

        UIManager.put("OptionPane.messageFont", new Font("Sans Serif", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(null,
                "Welcome to Budget Calculator!\n" +
                "---------------------------------------------------------------------------- \n" +
                "This program was created to help those in financially constraint\n" +
                "situations to better manage their money. This program will\n" +
                "also be useful in general especially young adults who need a stable\n" +
                "financial plan early on in their career.\n" +
                "---------------------------------------------------------------------------- \n"  +"Happy Spending! \f",
                "Brian's Budget Calculator" , JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Asks user questions to determine best planned budget
     *
     */
    private static void userStatistics(){

        int studentLoansConfirm;

        //Asks user if they have student loans
        Object[] options = {"Yes", "No"};
        studentLoansConfirm = JOptionPane.showOptionDialog(null,
                "Do you have any student loans?",
                "Student Loans", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options);

        if(studentLoansConfirm == JOptionPane.YES_OPTION) {
            studentLoans = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter how much you owe:","20000"));
        }
        else {
            studentLoans = 0;
        }


        }




    /**
     * Method
     * Used to calculate salary & taxes, etc..
     */
    private static void moneyHandler() {


        //local fields/variables
        double socialSecurityTax = 0.062;
        double medicareTax = 0.076;
        double ohioIncomeTax = 0.035;
        double cityTax = 0.021;
        //initialized and used below
        double extraTax;
        double taxRate;
        double minIncomeBracket;
        double federalIncomeTax;
        double totalYearlyIncome;



        String name = JOptionPane.showInputDialog(null, "Please enter your name", "John");

            //g initiated to start use in while loop
            int g = -1;
            //while loop to make sure name is entered
            while (g < 0) {
                if (name.length() > 0) {
                    g++;
                } else
                    name = JOptionPane.showInputDialog(null, "Please enter a name...", "Bob");
            }

        //input hourly wage
        double wage = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter your hourly salary:","15"));

        //input hours/week
        double hours = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the number of hours worked per week: ", "40"));

        //double weeks = w.nextDouble();
        double weeks = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the number of weeks you work per year: ", "52"));

        double four01k = Double.parseDouble(JOptionPane.showInputDialog(null, "What percentage is taken out for 401k: ", "6"));
                four01k = four01k * 0.01;

        //calculates yearly income from wage
        yearlyIncome = wage * weeks * hours;
        yearlyIncome = yearlyIncome - (yearlyIncome * four01k);

        //storing yearly income in another variable : totalYearlyIncome
        totalYearlyIncome = yearlyIncome;

        //changes federal income tax based on yearly income
        if (yearlyIncome <= 9525) {
            federalIncomeTax = .10;
            extraTax = 0;
            minIncomeBracket = 0;
        }

        else if (yearlyIncome > 9525 && yearlyIncome <= 38700) {
            federalIncomeTax = .12;
            extraTax = 952.50;
            minIncomeBracket = 9525;
        }

        else if (yearlyIncome > 38700 && yearlyIncome <= 82000) {
            federalIncomeTax = .22;
            extraTax = 4453.50;
            minIncomeBracket = 38700;
        }

        else if (yearlyIncome > 82000 && yearlyIncome <= 157500) {
            federalIncomeTax = .24;
            extraTax = 14089.50;
            minIncomeBracket = 82000;

        }

        else if (yearlyIncome > 157500 && yearlyIncome <= 200000) {
            federalIncomeTax = .32;
            extraTax = 32089.50;
            minIncomeBracket = 157000;
        }

        else if (yearlyIncome > 200000 && yearlyIncome <= 500000) {
            federalIncomeTax = .35;
            extraTax = 45689.50;
            minIncomeBracket = 200000;
        }

        else {
            federalIncomeTax = .37;
            extraTax = 150689.50;
            minIncomeBracket = 500000;
        }


        //to find total federal income tax
        taxRate = extraTax + ((yearlyIncome - minIncomeBracket) * federalIncomeTax);

        /*---------------------------------------------------------------------------------------------*/

        yearlyIncome = Math.round((yearlyIncome - taxRate) - (yearlyIncome * socialSecurityTax) -
                (yearlyIncome * medicareTax) - (yearlyIncome * ohioIncomeTax) - (yearlyIncome * cityTax));

        /*---------------------------------------------------------------------------------------------*/

        //to calculate monthly income using yearly income after tax deductions
        double monthlyIncome = Math.round(yearlyIncome / 12);

        //to calculate bi-weekly income using monthly income calculations
        double biWeeklyPay = Math.round(monthlyIncome / 2);

        // Output based on calculated variables
        System.out.println("Hi " + name + "! Your hourly salary is $" + wage + "/hr.\nYou make $" +
                        totalYearlyIncome + " per year pre-tax. \nYou make $" +
                        yearlyIncome + " per year after taxes. You make $" + monthlyIncome +
                        " per month after taxes. \nYour biweekly pay should come out to about: $" + biWeeklyPay);


        //Output shown in message box
        UIManager.put("OptionPane.messageFont", new Font("Sans Serif", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(null, "Hi " + name + "!\n\nYour hourly salary is $" + wage
                + "/hr. \n---------------------------------------------------\nYou make $" + totalYearlyIncome +
                " per year pre-tax. \nYou make $" + yearlyIncome + " per year after taxes. \nYou make $" + monthlyIncome
                + " per month after taxes. \nYour bi-weekly pay is $" + biWeeklyPay
                + "\n---------------------------------------------------","Income Calculator" , JOptionPane.PLAIN_MESSAGE);

    }


    /**
     * Divides up and manages yearly incomes
     */
    private static void planner() {

        //local variables/ fields
        double rent;
        double carPayment;
        double food;
        double gas;
        double savings;
        double miscellaneous;
        double studentLoanPayment;

        /*------------------------------------------------------------------------*/
        //calculations
        if(studentLoans > 0 && studentLoans <10000) {
            rent = Math.round((yearlyIncome * 0.25) / 12);
            carPayment = Math.round((yearlyIncome * 0.10) / 12);
            food = Math.round((yearlyIncome * 0.15) / 12);
            gas = Math.round((yearlyIncome * 0.05) / 12);
            miscellaneous = Math.round((yearlyIncome * 0.10) / 12);
            savings = Math.round((yearlyIncome * 0.25) / 12);  //.15
            studentLoanPayment = Math.round((yearlyIncome * 0.10) / 12);

        }
        else if(studentLoans > 10000 && studentLoans < 25000) {
            rent = Math.round((yearlyIncome * 0.25) / 12);
            carPayment = Math.round((yearlyIncome * 0.10) / 12);
            food = Math.round((yearlyIncome * 0.15) / 12);
            gas = Math.round((yearlyIncome * 0.05) / 12);
            miscellaneous = Math.round((yearlyIncome * 0.10) / 12);
            savings = Math.round((yearlyIncome * 0.20) / 12);
            studentLoanPayment = Math.round((yearlyIncome * 0.15) / 12);
        }

        else if(studentLoans > 25000) {
            rent = Math.round((yearlyIncome * 0.20) / 12);
            carPayment = Math.round((yearlyIncome * 0.05) / 12);
            food = Math.round((yearlyIncome * 0.15) / 12);
            gas = Math.round((yearlyIncome * 0.05) / 12);
            miscellaneous = Math.round((yearlyIncome * 0.05) / 12);
            savings = Math.round((yearlyIncome * 0.20) / 12);
            studentLoanPayment = Math.round((yearlyIncome * 0.30) / 12);
        }

        else {
            rent = Math.round((yearlyIncome * 0.30) / 12);
            carPayment = Math.round((yearlyIncome * 0.10) / 12);
            food = Math.round((yearlyIncome * 0.15) / 12);
            gas = Math.round((yearlyIncome * 0.05) / 12);
            miscellaneous = Math.round((yearlyIncome * 0.15) / 12);
            savings = Math.round((yearlyIncome * 0.25) / 12);
            studentLoanPayment = 0;

        }
        /*------------------------------------------------------------------------*/

        //Extra calculations
        if (gas > 100) {
            rent = rent + (gas - 100);
            gas = 100;
        }
        if (food > 350) {
            rent = rent + (food - 350);
            food = 350;

        }


        //Secondary output based on calculated variables
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("The following is your recommended budget calculated specifically for you: ");
        System.out.println("Based on take home pay of: $" + yearlyIncome);
        System.out.println(" Rent & Utilities: $" + rent + "\n Car payment: $" + carPayment + "\n Food: $" + food +
                            "\n Gas: $" + gas + "\n Savings: $" + savings + "\nStudent Loans: $" + studentLoanPayment +
                            "\n Miscellaneous: $" + miscellaneous);
        if (rent < 400) {
            System.out.println("****It is recommended you get a roommate****");

        }

        //output shown in message box
        if (studentLoans <=0) {
            JOptionPane.showMessageDialog(null,
                    "Allocated budget: Monthly \n\nBased on: $" + yearlyIncome +
                            "/year post tax\n\n" + "Rent & Utilities: $" + rent + "\nCar Payment: $" + carPayment + "\nFood: $" +
                            food + "\nGas: $" + gas + "\nSavings: $" + savings + "\nStudent Loans: $" + studentLoanPayment +
                            "\nMiscellaneous: $" + miscellaneous + "\n----------------------------------------------",
                    "Planned Budget", JOptionPane.PLAIN_MESSAGE);

        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Allocated budget: Monthly \n\nBased on: $" + yearlyIncome +
                            "/year post tax\n\n" + "Rent & Utilities: $" + rent + "\nCar Payment: $" + carPayment + "\nFood: $" +
                            food + "\nGas: $" + gas + "\nSavings: $" + savings + "\nStudent Loans: $" + studentLoanPayment +
                            "\nMiscellaneous: $" + miscellaneous + "\n----------------------------------------------" +
                            "\nApproximate time to pay off loans: " + Math.round(studentLoans / (studentLoanPayment * 12)) + " years.",
                    "Planned Budget", JOptionPane.PLAIN_MESSAGE);
        }

        if (rent < 400) {
            JOptionPane.showMessageDialog(null,
                    "It is HIGHLY recommended that you get a roommate",
                    "Budget Alert!", JOptionPane.WARNING_MESSAGE);
        }


    }



    /**
     * Main method -- Runs my program
     */
    // calling methods moneyHandler and planner in main method~~
    public static void main(String[] args) {

        welcomeMessage();
        userStatistics();
        moneyHandler();
        planner();



    }

    }







