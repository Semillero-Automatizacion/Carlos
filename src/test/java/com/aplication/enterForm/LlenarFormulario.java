package com.aplication.enterForm;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class LlenarFormulario {
	static WebDriver driver;
	final String url = "https://web.segurosfalabella.com/co/";
	final int tEspera = 20;
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Inicializando test..");
	}

	@Before
	public void before() throws InterruptedException {
		System.out.println("Cargando Chrome.exe...");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);		
		Thread.sleep(2500);
		System.out.println("Chrome.exe cargado exitosamente.");
	}

	@Test
	public void iniciar() throws InterruptedException {
		selectByClic("//*[@id=\"col-select-SOAT\"]",10000);
		fillInput("//*[@id=\"licence-plate\"]", "DNR327");
		selectByClic("//*[@id=\"material-group-habeas_data\"]/div/div",15000);
		fillInput("//*[@id=\"step1Data-identificationNumber\"]", "1022998201");
		selectByClic("//*[@id=\"select2-typeVehicles-container\"]/span", 1000);
		selectByClic("//*[@id=\"select2-typeVehicles-container\"]/span");
		selectByClic("//*[@id=\"Step0Button\"]");
	}

	@After
	public void after() throws InterruptedException {
		System.out.println("Chrome.exe se cerrará en "+tEspera+" segundos a partir de ahora.");
		Thread.sleep(tEspera*1000);
		driver.quit();
	}
	
	public void selectByClic(String xpath,int retardo) throws InterruptedException {
		WebElement elemento = driver.findElement(By.xpath(xpath));
		elemento.click();
		if(retardo != 0) {
			Thread.sleep(retardo);
		}
	}
	
	public void selectByClic(int retardo,String xpath) throws InterruptedException {
		if(retardo != 0) {
			Thread.sleep(retardo);
		}
		WebElement elemento = driver.findElement(By.xpath(xpath));
		elemento.click();
	}
	
	public void selectByClic(String xpath){
		WebElement elemento = driver.findElement(By.xpath(xpath));
		elemento.click();
	}
	
	public void fillInput(String xpath, String texto) {
		WebElement elemento = driver.findElement(By.xpath(xpath));
		elemento.sendKeys(texto);
	}
	
	public void fillSelectByText(String xpath, String texto) {
		Select select = new Select(driver.findElement(By.xpath(xpath)));
		select.selectByVisibleText(texto);
	}
	
	public void switchTab() {
		String currentWindow = null;
		for(String window : driver.getWindowHandles()) {
			if(driver.getWindowHandle()!= window) {
				driver.switchTo().window(window);
				currentWindow = window;
			}
		}
		System.out.println("ventana con id cambiada a: "+currentWindow);
		
	}
	
	public void jump() {
		WebElement elemento = driver.findElement(By.xpath("//body"));
		elemento.sendKeys(Keys.TAB);
	}
}
