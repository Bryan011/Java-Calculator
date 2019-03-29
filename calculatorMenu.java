import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class calculatorMenu extends JFrame
{
	private JPanel panel;
	private JMenuBar menuBar;
   private JMenu mainMenu;
	private JMenu openItem;
   private JMenuItem calculator;
   private JMenuItem tax;
   private JMenuItem mortgage;
   private JMenuItem closeItem;
   Calculator calc;
   MortgageCalculator mortCalc;
   TaxCalculator taxCalc;
   
	public calculatorMenu(){
		setTitle("CSC210");
		setSize(500, 500);
		setLocationRelativeTo(null);
		panel = new JPanel();
		
		add(panel);
		createComponents();
			
		setVisible( true );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void createComponents(){
   
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
      mainMenu = new JMenu("Menu");
		menuBar.add(mainMenu);
		
		Listener listener = new Listener();
	
		openItem = new JMenu("Open");
		mainMenu.add(openItem);
      
      closeItem = new JMenuItem("Close");
		closeItem.addActionListener(listener);
		
		calculator = new JMenuItem("Calculator");
		calculator.setMnemonic(KeyEvent.VK_C);
		calculator.addActionListener(listener);
		
		tax = new JMenuItem("Tax");
		tax.setMnemonic(KeyEvent.VK_T);
		tax.addActionListener(listener);
		
		mortgage = new JMenuItem("Mortgage");
		mortgage.setMnemonic(KeyEvent.VK_M);
		mortgage.addActionListener(listener);
      
		mainMenu.add(openItem);		
		openItem.add(calculator);		
		openItem.addSeparator();
		openItem.add(tax);
		openItem.addSeparator();
		openItem.add(mortgage);
		
		mainMenu.addSeparator();
		mainMenu.add(closeItem);
      
		
	}
	
	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==closeItem)
				System.exit(0);
         else if(e.getSource()==calculator){
               calc = new Calculator();
         }
         else if(e.getSource()==mortgage){
               mortCalc = new MortgageCalculator();
         } 
         else if(e.getSource()==tax){
               taxCalc = new TaxCalculator();
         }       				
		}
	}

	public static void main(String[] args) 
	{
		new calculatorMenu();		
	}
}