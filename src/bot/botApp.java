package bot;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.prefs.*;
import java.awt.Toolkit;
public class botApp {
	
	static String name;
    static String email;
	static String phone;
	static String street;
	static String zip;
	static String city;
	static String card;
	static String cvv;
	static int state = 58;
	static int cardMonth;
	static int cardYear;
	static String prodSKU;
	static int cSIZE;
	
	public static WebDriver driver;

	private JFrame frmPremeBot;
	private JTextField txtName;
	private JTextField txtStreet;
	private JTextField txtZipCode;
	private JTextField txtPhoneNumber;
	private JTextField txtItemId;
	private JTextField txtCard;
	private JTextField TXTcvv;
	private JTextField txtCity;
	private JTextField txtEmail;
	
	JComboBox<String> monthDrop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					botApp window = new botApp();
					window.frmPremeBot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public botApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPremeBot = new JFrame();
		frmPremeBot.setResizable(false);
		frmPremeBot.setTitle("Checkout Bot");
		frmPremeBot.setForeground(new Color(102, 204, 0));
		frmPremeBot.setBackground(new Color(0, 0, 0));
		frmPremeBot.getContentPane().setBackground(new Color(51, 51, 51));
		frmPremeBot.setBounds(100, 100, 706, 469);
		frmPremeBot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPremeBot.getContentPane().setLayout(null);
		
		//store the users input in preferences
		// Retrieve the user preference node 
		Preferences prefs = Preferences.userNodeForPackage(botApp.class);
		// Preference key names
		final String PREF_NAME = "name";
		final String PREF_EMAIL = "email";
		final String PREF_STREET = "street";
		final String PREF_ZIP = "zip";
		final String PREF_PHONE = "phone";
		final String PREF_CITY = "city";
		final String PREF_CARD = "card";
		final String PREF_CVV = "cvv";
		final String PREF_ID = "itemID";
		final String PREF_MONTH = "month";
		final String PREF_YEAR = "year";
		final String PREF_SIZE = "size";
		
		// Get the value of the preference;
		String namePropertyValue = prefs.get(PREF_NAME, "no val"); // "a string"
		String emailPropertyValue = prefs.get(PREF_EMAIL, "no val");
		String streetPropertyValue = prefs.get(PREF_STREET, "no val");
		String zipPropertyValue = prefs.get(PREF_ZIP, "no val");
		String phonePropertyValue = prefs.get(PREF_PHONE, "no val");
		String cityPropertyValue = prefs.get(PREF_CITY, "no val");
		String cardPropertyValue = prefs.get(PREF_CARD, "no val");
		String cvvPropertyValue = prefs.get(PREF_CVV, "no val");
		String itemIDPropertyValue = prefs.get(PREF_ID, "no val");
		int monthPropertyValue = prefs.getInt(PREF_MONTH, 0);
		int yearPropertyValue = prefs.getInt(PREF_YEAR, 0);
		int sizePropertyValue = prefs.getInt(PREF_SIZE, 0);
		
		//month drop down menu
		String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		JComboBox monthDrop = new JComboBox(months);
		monthDrop.setSelectedIndex(monthPropertyValue - 1);
		monthDrop.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		monthDrop.setForeground(new Color(255, 255, 255));
		monthDrop.setBackground(new Color(153, 153, 153));
		monthDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set users month to the selected index + 1
				cardMonth = monthDrop.getSelectedIndex() + 1;
			}
		});
		monthDrop.setBounds(450, 74, 74, 32);
		frmPremeBot.getContentPane().add(monthDrop);
		
		//years drop down menu
		String[] years = {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029"};
		JComboBox yearDrop = new JComboBox(years);
		yearDrop.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		yearDrop.setForeground(new Color(255, 255, 255));
		yearDrop.setBackground(new Color(153, 153, 153));
		yearDrop.setSelectedIndex(yearPropertyValue - 1);
		yearDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardYear = yearDrop.getSelectedIndex() + 1;
			}
		});
		yearDrop.setBounds(450, 127, 74, 32);
		frmPremeBot.getContentPane().add(yearDrop);
		
		//sizes drop down menu
		String[] sizes = {"small", "medium", "large", "X large"};
		JComboBox sizeDrop = new JComboBox(sizes);
		sizeDrop.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		sizeDrop.setForeground(new Color(255, 255, 255));
		sizeDrop.setBackground(new Color(153, 153, 153));
		sizeDrop.setSelectedIndex(sizePropertyValue);
		sizeDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cSIZE = sizeDrop.getSelectedIndex();
			}
		});
		sizeDrop.setBounds(440, 283, 84, 32);
		frmPremeBot.getContentPane().add(sizeDrop);
		
		//run bot button
		JButton btnRun = new JButton("RUN BOT");
		btnRun.setFont(new Font("SansSerif", Font.PLAIN, 21));
		btnRun.setBackground(new Color(153, 204, 0));
		btnRun.setForeground(new Color(255, 255, 255));
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//read in all the text boxes and set variables to them
				//store the value as a preference 
				name = txtName.getText();
				prefs.put(PREF_NAME, name);
				email = txtEmail.getText();
				prefs.put(PREF_EMAIL, email);
				street = txtStreet.getText();
				prefs.put(PREF_STREET, street);
				zip = txtZipCode.getText();
				prefs.put(PREF_ZIP, zip);
				phone = txtPhoneNumber.getText();
				prefs.put(PREF_PHONE, phone);
				city = txtCity.getText();
				prefs.put(PREF_CITY, city);
				card = txtCard.getText();
				prefs.put(PREF_CARD, card);
				cvv = TXTcvv.getText();
				prefs.put(PREF_CVV, cvv);
				prodSKU = txtItemId.getText();
				prefs.put(PREF_ID, prodSKU);
				cardMonth = monthDrop.getSelectedIndex() + 1;
				prefs.putInt(PREF_MONTH,cardMonth);
				cardYear = yearDrop.getSelectedIndex() + 1;
				prefs.putInt(PREF_YEAR,cardYear);
				cSIZE = sizeDrop.getSelectedIndex();
				prefs.putInt(PREF_SIZE,cSIZE);
				
			    try {
					runBot();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnRun.setBounds(453, 342, 185, 35);
		frmPremeBot.getContentPane().add(btnRun);
		
		//name text box
		txtName = new JTextField(namePropertyValue);
		txtName.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtName.setBounds(113, 21, 211, 32);
		frmPremeBot.getContentPane().add(txtName);
		txtName.setColumns(10);
		//email text box
		txtEmail = new JTextField(emailPropertyValue);
		txtEmail.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtEmail.setBounds(113, 74, 211, 32);
		frmPremeBot.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		//street text box
		txtStreet = new JTextField(streetPropertyValue);
		txtStreet.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtStreet.setBounds(113, 127, 211, 32);
		frmPremeBot.getContentPane().add(txtStreet);
		txtStreet.setColumns(10);
		//zip text box
		txtZipCode = new JTextField(zipPropertyValue);
		txtZipCode.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtZipCode.setBounds(113, 180, 211, 32);
		frmPremeBot.getContentPane().add(txtZipCode);
		txtZipCode.setColumns(10);
		//phone text box
		txtPhoneNumber = new JTextField(phonePropertyValue);
		txtPhoneNumber.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtPhoneNumber.setBounds(113, 233, 211, 32);
		frmPremeBot.getContentPane().add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		//city text box
		txtCity = new JTextField(cityPropertyValue);
		txtCity.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtCity.setBounds(113, 290, 211, 32);
		frmPremeBot.getContentPane().add(txtCity);
		txtCity.setColumns(10);
		//item id txt box
		txtItemId = new JTextField(itemIDPropertyValue);
		txtItemId.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtItemId.setBounds(440, 233, 186, 32);
		frmPremeBot.getContentPane().add(txtItemId);
		txtItemId.setColumns(10);
		//card text box
		txtCard = new JTextField(cardPropertyValue);
		txtCard.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtCard.setBounds(440, 21, 186, 32);
		frmPremeBot.getContentPane().add(txtCard);
		txtCard.setColumns(10);
		//cvv text box
		TXTcvv = new JTextField(cvvPropertyValue);
		TXTcvv.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		TXTcvv.setBounds(440, 180, 186, 32);
		frmPremeBot.getContentPane().add(TXTcvv);
		TXTcvv.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Corbel", Font.PLAIN, 18));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBackground(new Color(255, 255, 255));
		lblName.setBounds(21, 24, 92, 26);
		frmPremeBot.getContentPane().add(lblName);
		
		JLabel lblNewLabel = new JLabel("Street");
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(21, 130, 92, 26);
		frmPremeBot.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Zip");
		lblNewLabel_1.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(21, 183, 92, 26);
		frmPremeBot.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblPhone.setForeground(new Color(255, 255, 255));
		lblPhone.setBounds(21, 236, 92, 26);
		frmPremeBot.getContentPane().add(lblPhone);
		
		JLabel lblCard = new JLabel("Card #");
		lblCard.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblCard.setForeground(new Color(255, 255, 255));
		lblCard.setBounds(357, 27, 66, 26);
		frmPremeBot.getContentPane().add(lblCard);
		
		JLabel lblCvv = new JLabel("CVV");
		lblCvv.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblCvv.setForeground(new Color(255, 255, 255));
		lblCvv.setBounds(357, 186, 39, 26);
		frmPremeBot.getContentPane().add(lblCvv);
		
		JLabel lblItemId = new JLabel("ITEM ID");
		lblItemId.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblItemId.setForeground(new Color(255, 255, 255));
		lblItemId.setBounds(357, 239, 92, 26);
		frmPremeBot.getContentPane().add(lblItemId);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblCity.setForeground(new Color(255, 255, 255));
		lblCity.setBounds(21, 299, 92, 26);
		frmPremeBot.getContentPane().add(lblCity);
		
		JLabel lblExpMonth = new JLabel("exp month");
		lblExpMonth.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblExpMonth.setForeground(new Color(255, 255, 255));
		lblExpMonth.setBounds(357, 80, 100, 26);
		frmPremeBot.getContentPane().add(lblExpMonth);
		
		JLabel lblExpYear = new JLabel("exp year");
		lblExpYear.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblExpYear.setForeground(new Color(255, 255, 255));
		lblExpYear.setBounds(357, 133, 84, 26);
		frmPremeBot.getContentPane().add(lblExpYear);
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(21, 77, 92, 26);
		frmPremeBot.getContentPane().add(lblEmail);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblSize.setForeground(new Color(255, 255, 255));
		lblSize.setBounds(357, 289, 39, 26);
		frmPremeBot.getContentPane().add(lblSize);
	}
	
	public void runBot() throws InterruptedException {
		//open store url
		initWebDriver("https://www.supremenewyork.com/shop/all");
		//find the product to click on
		driver.findElement(By.xpath("//img[contains(@src,'" + prodSKU + "')]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
		//wait until the add to cart button is click able
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ard-rrmove-brttons\"]/input")));
	    //boolean for if there is a size drop down
		Boolean dropdownPresent = driver.findElement(By.name("s")).isDisplayed();
		//check if size is available, if so set the size 
		System.out.println(cSIZE);
	    if(dropdownPresent) {
			if(cSIZE == 0) {
				System.out.println(cSIZE);
				driver.findElement(By.xpath("//*[@id=\"s\"]/option[1]")).click();
			} else if(cSIZE == 1) {
				driver.findElement(By.xpath("//*[@id=\"s\"]/option[2]")).click();
			} else if(cSIZE == 2) {
				driver.findElement(By.xpath("//*[@id=\"s\"]/option[3]")).click();
			} else if(cSIZE == 3) {
				driver.findElement(By.xpath("//*[@id=\"s\"]/option[4]")).click();
			}
			
	     }
	  
		//wait until page is loaded
		driver.findElement(By.xpath("//*[@id=\"ard-rrmove-brttons\"]/input")).click();
		//wait until the checkout button is click able
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cart\"]/a[2]")));
		//find each text field with its xpath and send the keys to them
		driver.findElement(By.xpath("//*[@id=\"cart\"]/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"order_billing_name\"]")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id=\"order_email\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"order_tel\"]")).sendKeys(phone);
		driver.findElement(By.xpath("//*[@id=\"bo\"]")).sendKeys(street);
		driver.findElement(By.xpath("//*[@id=\"order_billing_zip\"]")).sendKeys(zip);
		driver.findElement(By.xpath("//*[@id=\"order_billing_city\"]")).sendKeys(city);
		driver.findElement(By.xpath("//*[@id=\"order_billing_state\"]/option[" + state + "]")).click();
		//needed wait time to avoid captcha
		TimeUnit.SECONDS.sleep(2);
		driver.findElement(By.xpath("//*[@id=\"orcer\"]")).sendKeys(cvv);
		driver.findElement(By.xpath("//*[@id=\"rnsnckrn\"]")).sendKeys(card);
		driver.findElement(By.xpath("//*[@id=\"credit_card_month\"]/option[" + cardMonth + "]")).click();
		driver.findElement(By.xpath("//*[@id=\"credit_card_year\"]/option[" + cardYear + "]")).click();
		driver.findElement(By.xpath("//*[@id=\"cart-cc\"]/fieldset/p[2]/label/div/ins")).click();
		driver.findElement(By.xpath("//*[@id=\"pay\"]/input")).click();
	}
	
	public static void initWebDriver(String URL) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\selniumDrivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setProxy(null);
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		//options.addArguments("user-data-dir=/Users/jackb/AppData/Local/Google/Chrome/User Data");
		
		driver = new ChromeDriver(options);
		// Initialize browser
		// Open supreme homescreen
		driver.get(URL);
	}
	
	public static void endSession() {
		driver.close();
		driver.quit();
	}
}
