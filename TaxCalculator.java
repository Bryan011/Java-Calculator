import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class TaxCalculator extends JFrame
{
   private JPanel panel;
   private JPanel textPanel;
   private JPanel buttonPanel;
   private JPanel resultPanel;
   private JTextField itemCost;
   private JTextField interestRate;   
   private JTextField totalSalesTax;
   private JTextField balanceDue;
   private JButton calculateTax;
   private JButton clearButton;
   
   DecimalFormat formatter = new DecimalFormat("###,###,###,###.00");
   taxOperations taxOps = new taxOperations();
   
   public TaxCalculator(){
      panel =new JPanel();
      textPanel = new JPanel();
      buttonPanel = new JPanel();
      resultPanel = new JPanel();
      textPanel.setLayout(new GridLayout(0,1));
      buttonPanel.setLayout(new GridLayout(0,1));
      resultPanel.setLayout(new GridLayout(0,1));
      
      setTitle("Tax");
      setSize(250,350);
      
  
      createComponents();
      add(panel);
      
      
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   public void createComponents()
   {
      
      
      itemCost = new JTextField(15);
      interestRate = new JTextField(15);
      
      totalSalesTax = new JTextField(15);
      balanceDue = new JTextField(15);
       
      
      JLabel itemCostLabel = new JLabel("Item Cost");
      textPanel.add(itemCostLabel);
      textPanel.add(itemCost);
      
      JLabel interestRateLabel = new JLabel("Interest Rate (%)");
      textPanel.add(interestRateLabel);
      textPanel.add(interestRate);
      
      Listener listener = new Listener();
      calculateTax = new JButton("Calculate");
      calculateTax.addActionListener(listener);
      clearButton = new JButton("Clear");
      clearButton.addActionListener(listener);
       
      buttonPanel.add(calculateTax);
      buttonPanel.add(clearButton);

      JLabel salesTaxLabel = new JLabel("Total Sales Tax");
      resultPanel.add(salesTaxLabel);
      resultPanel.add(totalSalesTax);
            
      JLabel balanceLabel = new JLabel("Total Balance");
      resultPanel.add(balanceLabel);
      resultPanel.add(balanceDue);
 
      panel.add(textPanel);
      panel.add(buttonPanel);
      panel.add(resultPanel);   
         
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         
         double totalTax, blnce;
         double c, r;
         c = Double.parseDouble(itemCost.getText());
         r = Double.parseDouble(interestRate.getText());
        
         if(e.getSource()==calculateTax)
         {
            totalTax = taxOps.calculateTax(c,r);
            totalSalesTax.setText(formatter.format(totalTax));
            
            blnce = (totalTax+c);
            balanceDue.setText(formatter.format(blnce));
         }
         else if(e.getSource()==clearButton)
         {
            itemCost.setText("0");
            interestRate.setText("0");       
            totalSalesTax.setText("0");
            balanceDue.setText("0");
         }
      }
   }
   public static void main ( String[] args )
   {
      new TaxCalculator();
   }

}

class taxOperations {
   
   public double calculateTax(double c, double r){
      double cost = c;
      double rate = r;
      double result;
      
      rate/=100;
      result = rate*cost;
      
      return result;
   }
}