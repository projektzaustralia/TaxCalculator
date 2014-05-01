/*
COSC2135 - Programming 1 
Study Period 1, 2014 
Assignment 1 
David Zammit

This is a console application which implements the functionality of a basic 
tax calculation program.
*/

package assignmentOne;

import java.util.Scanner;

public class BasicTaxCalculator
{

   public static void main(String[] args)
   {

      Scanner console = new Scanner(System.in);

      String custName, taxFileNum, fiYear, taxAgent;
      double grossIncome = 0, bankInterest = 0, pretax = 0, claimDeduc = 0;
      double assIncome = 0, taxOffset = 0, taxIncome = 0, taxPayable = 0, 
               medicareFinal = 0;
      double giTotal = 0, biTotal = 0, ptsTotal = 0, cdTotal = 0;
      final double levyRates = 0.015;
      final int A_INCOME = 10000;
      final int B_INCOME = 40000;
      final int C_INCOME = 80000;
      final int D_INCOME = 120000;

      //Basic user information to be input
      System.out.print("*** Final Income Tax Calculator ***\n");
      System.out.print("\nEnter Name: ");
      custName = console.nextLine();
      System.out.print("\nEnter Tax File Number (TFN): ");
      taxFileNum = console.nextLine();
      System.out.print("\nEnter Financial Year: ");
      fiYear = console.nextLine();
      
      //Main menu for user navigation
      char menu = 0;
      Boolean valledMenu = false;
      while (valledMenu == false)
      {
         System.out.println("-------------------");
         System.out.print("\n*** Taxation Data Entry System ***\n");
         System.out.print("\nA - Add assessable income");
         System.out.print("\nB - Add interest accrued from bank account");
         System.out.print("\nC - Add pre-tax superannuation contribution");
         System.out.print("\nD - Add claimable deduction\n");
         System.out.print("\nX - Exit and compile final tax statement");

         System.out.print("\n\nEnter your selection: ");
         menu = console.next().charAt(0);

         switch (menu)
         {
            case 'A':
            case 'a':
               System.out.print("\nEnter assessable income for period " +
                                fiYear
                                + ": ");
               grossIncome = console.nextDouble();
               giTotal = giTotal + grossIncome;
               break;
            case 'B':
            case 'b':
               System.out.print("\nEnter bank interest accrued: ");
               bankInterest = console.nextDouble();
               biTotal = biTotal + bankInterest;
               break;
            case 'C':
            case 'c':
               System.out
                        .print("\nEnter pre-tax superanuation contribution " +
                                 "for period "
                               +
                               fiYear + ": ");
               pretax = console.nextDouble();
               ptsTotal = ptsTotal + pretax;
               break;
            case 'D':
            case 'd':
               System.out.print("\nEnter claimable deduction(s) for period "
                                + fiYear + ": ");
               claimDeduc = console.nextDouble();
               cdTotal = cdTotal + claimDeduc;
               break;
            case 'X':
            case 'x':
               System.out.println("\nTaxation data entry complete.");
               System.out.println("");
               System.out.println("-----------------");
               valledMenu = true;
               break;
            default:
               System.out.println("\nInvalid selection, please try again.");
               break;
         }
      }

      // Calculations used to determine final values.
      assIncome = giTotal + biTotal;
      taxOffset = ptsTotal + cdTotal;
      taxIncome = assIncome - taxOffset;

      // Determine customers tax bracket.
      Boolean i = false;
      while (i == false)
      {
         if (taxIncome <= A_INCOME)
         {
            taxPayable = 0;
            i = true;
         }
         else if (taxIncome > A_INCOME && taxIncome <= B_INCOME)
         {
            taxPayable = (taxIncome - A_INCOME) * 0.15;
            i = true;
         }
         else if (taxIncome > B_INCOME && taxIncome <= C_INCOME)
         {
            taxPayable = ((taxIncome - B_INCOME) * 0.30) + 4500;
            i = true;
         }
         else if (taxIncome > C_INCOME && taxIncome <= D_INCOME)
         {
            taxPayable = ((taxIncome - C_INCOME) * 0.40) + 16500;
            i = true;
         }
         else if (taxIncome > D_INCOME)
         {
            taxPayable = ((taxIncome - D_INCOME) * 0.45) + 32500;
            i = true;
         }
      }

      // Health Insurance Calculations
      int valledAns = 0;
      while (valledAns == 0)
      {
         System.out
                  .print("\nDoes the taxpayer currently have private health " +
                           "insurance? (Y/N): ");
         char ans = console.next().charAt(0);
         switch (ans)
         {
            case 'y':
            case 'Y':
               valledAns = 1;
               break;
            case 'n':
            case 'N':
               valledAns = 2;
               break;
            default:
               System.out
                        .print("Invalid input, please answer Y/N to continue.");
               break;
         }
      }

      if (valledAns == 2)
      {
         medicareFinal = taxIncome * levyRates;
      }

      console.nextLine();

      System.out.print("\nEnter tax agent name: ");
      taxAgent = console.nextLine();

      // Customers final tax statement
      System.out.print("\n\n\t*** Final Tax Statement ***");
      System.out.print("\n\nCustomer Name: " + custName);
      System.out.print("\nTax File Number: " + taxFileNum);
      System.out.print("\nFinancial Year: " + fiYear);
      System.out.printf("\n\nAssessable Income: %.2f", assIncome);
      System.out.printf("\nMinus Tax Offsets: -%.2f", taxOffset);
      System.out.printf("\nTaxable Income: %.2f", taxIncome);
      System.out.printf("\n\nTax Payable: %.2f", taxPayable);
      if (valledAns == 2)
      {
         System.out.printf("\nMedicare Levy: %.2f", medicareFinal);
      }
      System.out.print("\n\nTax Agent: " + taxAgent);

      console.close();

   }
}