package bot;

import java.util.Iterator;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.EventQueue;

import javax.swing.JFrame;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.prefs.*;

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
	static String keyWord;
	static int cSIZE;
	static String color;
	static String catagory;
	
	
	public static WebDriver driver;

	private JFrame frmPremeBot;
	private JTextField txtName;
	private JTextField txtStreet;
	private JTextField txtZipCode;
	private JTextField txtPhoneNumber;
	private JTextField txtKeyWord;
	private JTextField txtCard;
	private JTextField TXTcvv;
	private JTextField txtCity;
	private JTextField txtEmail;
	private JTextField txtColor;
	
	JComboBox<String> monthDrop;
	private JTextField txtCatagory;
	

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
		frmPremeBot.getContentPane().setForeground(new Color(0, 0, 0));
		frmPremeBot.setResizable(false);
		frmPremeBot.setTitle("Checkout Bot");
		frmPremeBot.setForeground(new Color(0, 0, 0));
		frmPremeBot.setBackground(new Color(0, 0, 0));
		frmPremeBot.getContentPane().setBackground(new Color(102, 102, 102));
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
		final String PREF_KEYWORD = "itemKeyWord";
		final String PREF_MONTH = "month";
		final String PREF_YEAR = "year";
		final String PREF_SIZE = "size";
		final String PREF_COLOR = "color";
		final String PREF_CATAGORY = "catagory";
		
		// Get the value of the preference;
		String namePropertyValue = prefs.get(PREF_NAME, "no val"); // "a string"
		String emailPropertyValue = prefs.get(PREF_EMAIL, "no val");
		String streetPropertyValue = prefs.get(PREF_STREET, "no val");
		String zipPropertyValue = prefs.get(PREF_ZIP, "no val");
		String phonePropertyValue = prefs.get(PREF_PHONE, "no val");
		String cityPropertyValue = prefs.get(PREF_CITY, "no val");
		String cardPropertyValue = prefs.get(PREF_CARD, "no val");
		String cvvPropertyValue = prefs.get(PREF_CVV, "no val");
		String itemKeyWordPropertyValue = prefs.get(PREF_KEYWORD, "no val");
		int monthPropertyValue = prefs.getInt(PREF_MONTH, 0);
		int yearPropertyValue = prefs.getInt(PREF_YEAR, 0);
		int sizePropertyValue = prefs.getInt(PREF_SIZE, 0);
		String colorPropertyValue = prefs.get(PREF_COLOR, "no val");
		String catagoryPropertyValue = prefs.get(PREF_CATAGORY, "no val");
		
		//month drop down menu
		String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		JComboBox monthDrop = new JComboBox(months);
		monthDrop.setSelectedIndex(monthPropertyValue - 1);
		monthDrop.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		monthDrop.setForeground(new Color(255, 255, 255));
		monthDrop.setBackground(new Color(153, 153, 153));
		monthDrop.setBounds(440, 74, 74, 32);
		frmPremeBot.getContentPane().add(monthDrop);
		
		//years drop down menu
		String[] years = {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029"};
		JComboBox yearDrop = new JComboBox(years);
		yearDrop.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		yearDrop.setForeground(new Color(255, 255, 255));
		yearDrop.setBackground(new Color(153, 153, 153));
		yearDrop.setSelectedIndex(yearPropertyValue - 1);
		yearDrop.setBounds(545, 74, 74, 32);
		frmPremeBot.getContentPane().add(yearDrop);
		
		//sizes drop down menu
		String[] sizes = {"one size", "small", "medium", "large", "X large"};
		JComboBox sizeDrop = new JComboBox(sizes);
		sizeDrop.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		sizeDrop.setForeground(new Color(255, 255, 255));
		sizeDrop.setBackground(new Color(153, 153, 153));
		sizeDrop.setSelectedIndex(sizePropertyValue);
		sizeDrop.setBounds(440, 340, 113, 32);
		frmPremeBot.getContentPane().add(sizeDrop);
		
		//run bot button
		JButton btnRun = new JButton("RUN BOT");
		btnRun.setFont(new Font("Consolas", Font.PLAIN, 24));
		btnRun.setBackground(new Color(153, 255, 102));
		btnRun.setForeground(new Color(0, 0, 0));
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
				keyWord = txtKeyWord.getText();
				prefs.put(PREF_KEYWORD, keyWord);
				cardMonth = monthDrop.getSelectedIndex() + 1;
				prefs.putInt(PREF_MONTH,cardMonth);
				cardYear = yearDrop.getSelectedIndex() + 1;
				prefs.putInt(PREF_YEAR,cardYear);
				cSIZE = sizeDrop.getSelectedIndex();
				prefs.putInt(PREF_SIZE,cSIZE);
				color = txtColor.getText();
				prefs.put(PREF_COLOR, color);
				catagory = txtCatagory.getText();
				prefs.put(PREF_CATAGORY, catagory);
				
				try {
					runBot();
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnRun.setBounds(43, 349, 248, 48);
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
		txtKeyWord = new JTextField(itemKeyWordPropertyValue);
		txtKeyWord.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtKeyWord.setBounds(440, 180, 186, 32);
		frmPremeBot.getContentPane().add(txtKeyWord);
		txtKeyWord.setColumns(10);
		//card text box
		txtCard = new JTextField(cardPropertyValue);
		txtCard.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtCard.setBounds(440, 21, 186, 32);
		frmPremeBot.getContentPane().add(txtCard);
		txtCard.setColumns(10);
		//cvv text box
		TXTcvv = new JTextField(cvvPropertyValue);
		TXTcvv.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		TXTcvv.setBounds(440, 127, 186, 32);
		frmPremeBot.getContentPane().add(TXTcvv);
		TXTcvv.setColumns(10);
		
		txtColor = new JTextField(colorPropertyValue);
		txtColor.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtColor.setBounds(440, 233, 186, 32);
		frmPremeBot.getContentPane().add(txtColor);
		txtColor.setColumns(10);
		
		txtCatagory = new JTextField(catagoryPropertyValue);
		txtCatagory.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtCatagory.setBounds(440, 286, 186, 32);
		frmPremeBot.getContentPane().add(txtCatagory);
		txtCatagory.setColumns(10);
		
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
		lblCvv.setBounds(357, 129, 39, 26);
		frmPremeBot.getContentPane().add(lblCvv);
		
		JLabel lblKeyWord = new JLabel("Key Word");
		lblKeyWord.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblKeyWord.setForeground(new Color(255, 255, 255));
		lblKeyWord.setBounds(357, 182, 92, 26);
		frmPremeBot.getContentPane().add(lblKeyWord);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblCity.setForeground(new Color(255, 255, 255));
		lblCity.setBounds(21, 292, 92, 26);
		frmPremeBot.getContentPane().add(lblCity);
		
		JLabel lblExpMonth = new JLabel("exp");
		lblExpMonth.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblExpMonth.setForeground(new Color(255, 255, 255));
		lblExpMonth.setBounds(357, 80, 100, 26);
		frmPremeBot.getContentPane().add(lblExpMonth);
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(21, 77, 92, 26);
		frmPremeBot.getContentPane().add(lblEmail);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblSize.setForeground(new Color(255, 255, 255));
		lblSize.setBounds(357, 343, 39, 26);
		frmPremeBot.getContentPane().add(lblSize);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setForeground(new Color(255, 255, 255));
		lblColor.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblColor.setBounds(357, 235, 92, 26);
		frmPremeBot.getContentPane().add(lblColor);
		
		JLabel lblCatagory = new JLabel("Catagory");
		lblCatagory.setForeground(new Color(255, 255, 255));
		lblCatagory.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblCatagory.setBounds(357, 289, 92, 26);
		frmPremeBot.getContentPane().add(lblCatagory);
		
	}
	
	public void runBot() throws InterruptedException, MalformedURLException, IOException {
		//open store url
		initWebDriver("https://www.supremenewyork.com/shop/all");
		
		Instant start = Instant.now();
		int id = 0;
		//keep looping until a product id is found
		while(id == 0) {
			//find the product and return its id, if no product is found, return 0
			id = findProduct(keyWord);
			System.out.println("Looking for product");
		}
		
		System.out.println("Product found");
		
		//get the varient by passing the link to the json file to that product
		String prodID = "VAR NOT FOUND";
		while(prodID.equals("VAR NOT FOUND")) {
			prodID = getVarient("https://www.supremenewyork.com" + "/shop/" + id + ".json");
		}
		System.out.println("Product VAR found");
		//find the product to click on
		driver.findElement(By.xpath("//img[contains(@src,'" + prodID + "')]")).click();
		//assets.supremenewyork.com/181074/sw/zAmPJjyeNH8.jpg
		WebDriverWait wait = new WebDriverWait(driver, 20);
		//wait until the add to cart button is click able
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ard-rrmove-brttons\"]/input")));
	    //boolean for if there is a size drop down
		Boolean dropdownPresent = driver.findElement(By.name("s")).isDisplayed();
		//check if size is available, if so set the size 
	    if(dropdownPresent) {
	    	Select sizeDropDown = new Select(driver.findElement(By.xpath("//*[@id=\"s\"]")));
	    	if(cSIZE == 0) {
	    		//do nothing
	    	} else if(cSIZE == 1) {
	    		sizeDropDown.selectByVisibleText("Small");
			} else if(cSIZE == 2) {
				sizeDropDown.selectByVisibleText("Medium");
			} else if(cSIZE == 3) {
				sizeDropDown.selectByVisibleText("Large");
			} else if(cSIZE == 4) {
				sizeDropDown.selectByVisibleText("XLarge");
			}
	     }
	    System.out.println("size selected");
	  
		driver.findElement(By.xpath("//*[@id=\"ard-rrmove-brttons\"]/input")).click();
		System.out.println("Item added to cart");
		
		//wait until the checkout button is click able
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cart\"]/a[2]")));
		//find each text field with its xpath and send the keys to them
		driver.findElement(By.xpath("//*[@id=\"cart\"]/a[2]")).click();
		System.out.println("Item at checkout screen");
		
		driver.findElement(By.xpath("//*[@id=\"order_billing_name\"]")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id=\"order_email\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"order_tel\"]")).sendKeys(phone);
		driver.findElement(By.xpath("//*[@id=\"bo\"]")).sendKeys(street);
		driver.findElement(By.xpath("//*[@id=\"order_billing_zip\"]")).sendKeys(zip);
		driver.findElement(By.xpath("//*[@id=\"order_billing_city\"]")).sendKeys(city);
		driver.findElement(By.xpath("//*[@id=\"order_billing_state\"]/option[" + state + "]")).click();
		//needed wait time to avoid captcha
		//TimeUnit.SECONDS.sleep(2);
		driver.findElement(By.xpath("//*[@id=\"orcer\"]")).sendKeys(cvv);
		driver.findElement(By.xpath("//*[@id=\"rnsnckrn\"]")).sendKeys(card);
		driver.findElement(By.xpath("//*[@id=\"credit_card_month\"]/option[" + cardMonth + "]")).click();
		driver.findElement(By.xpath("//*[@id=\"credit_card_year\"]/option[" + cardYear + "]")).click();
		driver.findElement(By.xpath("//*[@id=\"cart-cc\"]/fieldset/p[2]/label/div/ins")).click();
		driver.findElement(By.xpath("//*[@id=\"pay\"]/input")).click();
		
		System.out.println("Checkout Pressed");
		Instant end = Instant.now();
		Duration time = Duration.between(start, end);
		System.out.println("Checkout time: " + time.getSeconds());
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
	
	public int findProduct(String keyWord) throws MalformedURLException, IOException {
		//convert url into a string
		@SuppressWarnings("resource")
		String s = new Scanner(new URL("https://www.supremenewyork.com/mobile_stock.json").openStream(), "UTF-8").useDelimiter("\\A").next();
		//create a json object with that string 
		JSONObject obj = new JSONObject(s);
	    JSONObject categories = obj.getJSONObject("products_and_categories");
		Iterator<String> keys = categories.keys();
		while(keys.hasNext()) {
			String key = keys.next();
			if (categories.get(key) instanceof JSONArray){
				JSONArray arr = (JSONArray) categories.get(key);
				for(int i = 0; i < arr.length(); i++) {
					JSONObject object = arr.getJSONObject(i);
					if(object.getString("name").contains(keyWord) && object.getString("category_name").equals(catagory)) {
						return object.getInt("id");
					}
				}
			}
		  } 
		  return 0;
	}
	
	public String getVarient(String prodURL) throws MalformedURLException, IOException {
		String size = "one size";
    	if(cSIZE == 1) {
    		size ="Small";
		} else if(cSIZE == 2) {
			size = "Medium";
		} else if(cSIZE == 3) {
			size = "Large";
		} else if(cSIZE == 4) {
			size = "XLarge";
		}
		//convert url into a string
		@SuppressWarnings("resource")
		String productURL = new Scanner(new URL(prodURL).openStream(), "UTF-8").useDelimiter("\\A").next();
		//create a json object with that string 
		JSONObject prod = new JSONObject(productURL);
		//Create a jso array of styles
		JSONArray arr = prod.getJSONArray("styles");
		//loop through each style
		for(int i = 0; i < arr.length(); i++) {
			JSONObject jsonObject = arr.getJSONObject(i);
		    JSONArray sizeArr = jsonObject.getJSONArray("sizes");
		    //loop through each style
			for (int j = 0; j < sizeArr.length(); j++) {
				JSONObject jsonObjectSize = sizeArr.getJSONObject(j);
				if(jsonObject.getString("name").contains(color) && (jsonObjectSize.getString("name").contentEquals(size) || size.contentEquals("one size"))){
					if(jsonObjectSize.getInt("stock_level") > 0) {
						String URL = jsonObject.getString("image_url");
						return URL.substring(28, 34);
					}
				}
			 }
	     }
		 return "VAR NOT FOUND";
	}
	
	public static void endSession() {
		driver.close();
		driver.quit();
	}
}
