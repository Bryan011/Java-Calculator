import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Calculator extends JFrame 
{
   private JTextField result;
   private JPanel numberPanel; 
   private JPanel operationsPanel;    
   private JButton number[] = new JButton[10];
   private JButton operation[] = new JButton[20];
   private JButton clearButton;
   private JButton equalButton;
   private boolean valid = true;
   private String sign = "=";

   Operations ops = new Operations();

   public Calculator(){
      setTitle("Calculator");
      setSize(350,350);
      setLocationRelativeTo(null);
      
      numberPanel = new JPanel();  
      operationsPanel = new JPanel();

      createComponents();
       
      numberPanel.setLayout(new GridLayout(4,3,2,2));
      operationsPanel.setLayout(new GridLayout(5,4,2,2));
      
      getContentPane().add(BorderLayout.NORTH, result);
      getContentPane().add(BorderLayout.CENTER, numberPanel);
      getContentPane().add(BorderLayout.WEST, operationsPanel);
      
      pack();
      setVisible( true );
      setResizable( false );
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   public void createComponents(){
      createResultComponents();
      createNumberComponents();
      createOperationComponents();
   }
   
   public void createResultComponents(){
      result = new JTextField("0", JTextField.CENTER);
      result.setComponentOrientation(ComponentOrientation.
         LEFT_TO_RIGHT);  
   }
   
   public void createNumberComponents(){
      numListener nl = new numListener();
   
      for(int i = 1; i<10; i++){
         number[i] = new JButton("" +i);
         number[i].addActionListener(nl);
         numberPanel.add(number[i]);
         }
         
      number[0] = new JButton("0");
      number[0].addActionListener(nl);   
      numberPanel.add(number[0]); 
   }
   
   public void createOperationComponents(){
      operatorListener ol = new operatorListener();
      String[] symbol = {"+", "-", "/", "*","^","n!",
         "(-)","\u03C0","\u221A","|x|","\u212f\u02e3",
            "log","sin","cos","tan"};
      
      for(int i =0; i<symbol.length; i++){
         operation[i] = new JButton(symbol[i]);
         operation[i].addActionListener(ol);
         operationsPanel.add(operation[i]);
      }
      
      clearButton = new JButton("CLEAR");
      equalButton = new JButton("=");
      clearButton.addActionListener(ol);
      equalButton.addActionListener(ol);
      numberPanel.add(clearButton);  
      numberPanel.add(equalButton);  
   }
   
   class numListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
      String digit = e.getActionCommand();
      
      if(valid){
         result.setText("" + digit);   
         valid = false;
         }
      else
         result.setText(result.getText() + digit);        
      }
   }
   
   class operatorListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String numberToParse = 0+result.getText();
         
         if(e.getSource()==clearButton){
            ops = new Operations();
            result.setText("");
         }
         else if (e.getSource()==operation[5])
         {
            double total;
            double f = Double.parseDouble(result.getText());
            total = ops.factorial(f);
            result.setText("" +total);        
         }
         else if(e.getSource()==operation[6]){
            int f = Integer.parseInt(result.getText());
            int total = f*-1;
            result.setText("" +total);     
         }  
         else if(e.getSource()==operation[7]){
            result.setText("" +Math.PI);
         }
         else if(e.getSource()==operation[8]){
            result.setText("" + 
               Math.sqrt(Double.parseDouble(numberToParse)));
         }
         else if(e.getSource()==operation[9]){
            result.setText("" +
               Math.abs(Integer.parseInt(numberToParse)));
         }
         else if(e.getSource()==operation[10]){
            result.setText("" +
               Math.exp(Double.parseDouble(numberToParse)));
         } 
         else if(e.getSource()==operation[11]){
            result.setText("" +  
               Math.log(Double.parseDouble(numberToParse)));
         } 
         else if(e.getSource()==operation[12]){
            result.setText("" +
               Math.sin(Double.parseDouble(numberToParse)));
         }  
         else if(e.getSource()==operation[13]){
            result.setText("" +
               Math.cos(Double.parseDouble(numberToParse)));
         }
         else if(e.getSource()==operation[14]){
            result.setText("" +
               Math.tan(Double.parseDouble(numberToParse)));
         }
         else
         {
            if(!valid)
            {   
               valid = true;               
               if(sign.equals("=")){
                  ops.setTotal(numberToParse);   
               }
               else if(sign.equals("+")){  
                  ops.add(numberToParse);
               }
               else if (sign.equals("-"))
               {      
                  ops.subtract(numberToParse);
               }
               else if (sign.equals("*"))
               {
                  ops.multiply(numberToParse);
               }
               else if (sign.equals("/"))
               {
                  ops.divide(numberToParse);
               }
               else if (sign.equals("^"))
               {
                  ops.exponent(numberToParse);
               } 
               result.setText("" + ops.getTotal());
               sign = e.getActionCommand();   
            }  
            else
            {
            ops.setTotal(numberToParse);
            sign = e.getActionCommand();
            }          
         }
       ops.setTotal(numberToParse);         
      }
   }
 
   public static void main(String[] args)
   {
      new Calculator();
   }
}

class Operations {
   private double total;
   public Operations(){
      total = 0;
   }     
   public String getTotal(){
      return "" +total;
   }
   public void setTotal(String n){
      total = doubleParse(n);
   }
   public void add(String n){
      total += doubleParse(n);
   }
   public void subtract(String n){
      total -= doubleParse(n);
   }
   public void multiply(String n){
      total *= doubleParse(n);
   }
   public void divide(String n){
      total /= doubleParse(n);
   }
   public void exponent(String n){
      total = Math.pow(total, doubleParse(n));
   }
   public void negative(String n){
      total = doubleParse(n) * -1;
   }
   public double factorial(double f){
      double base = 1;
      double factorial = f;
      for(int i = 1; i<=factorial; i++){
         base*=i;
      }
      return base + total;
   }
  
   private int intParse(String n) {
      return Integer.parseInt(n);
   }
   private double doubleParse(String n) {
      return Double.parseDouble(n);
   }
}
   