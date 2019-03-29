import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class MortgageCalculator extends JFrame
{
   private JPanel panel;
   private JPanel textPanel;
   private JPanel buttonPanel;
   private JPanel resultPanel;
   private JTextField mortgageAmount;
   private JTextField interestRate;
   private JTextField mortgagePeriod;
   private JTextField monthlyPayment;
   private JTextField balanceDue;
   private JButton calculateMortgage;
   private JButton clearButton;
   
   DecimalFormat formatter = new DecimalFormat("###,###,###,###.00");
   mortgageOperations mortOps = new mortgageOperations();
   
   public MortgageCalculator(){
      panel =new JPanel();
      textPanel = new JPanel();
      buttonPanel = new JPanel();
      resultPanel = new JPanel();
      textPanel.setLayout(new GridLayout(0,1));
      buttonPanel.setLayout(new GridLayout(0,1));
      resultPanel.setLayout(new GridLayout(0,1));
      
      setTitle("Mortgage Calculator");
      setSize(250,350);
      
  
      createComponents();
      add(panel);
      
      
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   public void createComponents()
   {
      
      
      mortgageAmount = new JTextField(15);
      interestRate = new JTextField(15);
      mortgagePeriod = new JTextField(15);
      monthlyPayment = new JTextField(15);
      balanceDue = new JTextField(15);
       
      
      JLabel mortgageAmountLabel = new JLabel("Mortgage Amount ($)");
      textPanel.add(mortgageAmountLabel);
      textPanel.add(mortgageAmount);
      
      JLabel interestRateLabel = new JLabel("Interest Rate (%)");
      textPanel.add(interestRateLabel);
      textPanel.add(interestRate);
      
      JLabel mortgagePeriodLabel  = new JLabel("Mortgage Period (years)");
      textPanel.add(mortgagePeriodLabel);
      textPanel.add(mortgagePeriod);
    
     
     
      Listener listener = new Listener();
      calculateMortgage = new JButton("Calculate");
      calculateMortgage.addActionListener(listener);
      clearButton = new JButton("Clear");
      clearButton.addActionListener(listener);
       
      buttonPanel.add(calculateMortgage);
      buttonPanel.add(clearButton);
      
      JLabel balanceLabel = new JLabel("Balance");
      resultPanel.add(balanceLabel);
      resultPanel.add(balanceDue);
      
      JLabel resultLabel = new JLabel("Monthly Payment");
      resultPanel.add(resultLabel);
      resultPanel.add(monthlyPayment);  
          
      
      panel.add(textPanel);
      panel.add(buttonPanel);
      panel.add(resultPanel);   
         
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         
         double mPayment, blnce;
         double a, r, p;
         a = Double.parseDouble(mortgageAmount.getText());
         r = Double.parseDouble(interestRate.getText());
         p = Double.parseDouble(mortgagePeriod.getText());
         if(e.getSource()==calculateMortgage)
         {
            mPayment = mortOps.calculateMortgage(a,r,p);
            monthlyPayment.setText(formatter.format(mPayment));
            
            blnce = (mPayment * (p * 12));
            balanceDue.setText(formatter.format(blnce));
         }
         else if(e.getSource()==clearButton)
         {
            mortgageAmount.setText("0");
            interestRate.setText("0");
            mortgagePeriod.setText("0");
            monthlyPayment.setText("0");
            balanceDue.setText("0");
         }
      }
   }
   public static void main ( String[] args )
   {
      new MortgageCalculator();
   }

}

class mortgageOperations {
  
   public double calculateMortgage(double a, double r, double p){
      double rate = (r/100)/12;
      double base = (rate+1);
      double months = p * 12;
      double result = a * (rate *(Math.pow(base, months)) / 
      ((Math.pow(base, months)) -1));
      
      return result;
   }
}