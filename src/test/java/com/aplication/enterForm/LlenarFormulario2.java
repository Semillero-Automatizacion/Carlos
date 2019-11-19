package com.aplication.enterForm;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class LlenarFormulario2 {
	static WebDriver driver;
	final String url = "https://compratusoat.com.co/";
	final int tEspera = 10;
	
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
		fillInput("//*[@id=\"pro2\"]/div[2]/input[@name=\"placa\"]", "DNR327");
		fillInput("//input[@id=\"mail\"]", "frankye1994@gmail.com");
		fillInput("//input[@id=\"name\"]", "Frankye Steven");
		fillInput("//input[@id=\"apellidos\"]", "Mateus Alfonso");
		fillInput("//input[@id=\"phone\"]", "3115265644");
		selectByClic("//input[@id=\"accept\"]",500);
		selectByClic("//*[@id=\"modalTerminos\"]/div/div/div[3]/button[2]",500);
		selectByClic("//input[@id=\"btsubmit\"]");
		selectByClic("//input[@id=\"test5\"]",2000);
		WebElement elemento = driver.findElement(By.xpath("//*[@id=\"paso1\"]/div[5]/div/div[2]/input[1]"));
		elemento.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		selectByClic("//input[@id=\"cedula\"]",1000);
		//fillInput("//input[@id=\"cedula\"]", "1022998201");
		fillInput("//input[@id=\"inputSelect\"]", "Bogota D.C.");
		fillInput("//input[@id=\"addres\"]", "Calle 63 # 57g 42");
		elemento = driver.findElement(By.xpath("//*[@id=\"paso2\"]/div[4]/div/div[2]/input[1]"));
		elemento.sendKeys(Keys.ENTER);
		//elemento = driver.findElement(By.xpath("//*[@id=\"paso3\"]/div[4]/div/div[2]/input[1]"));
		elemento.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		selectByClic("//div[@id=\"cookies\"]/button[@type=\"button\"]",2000);
		selectByClic("//div[@class=\"col-sm-4 text-right\"]/input[@type=\"submit\"]",5000);
		elemento = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/div/h1"));
		Assert.assertEquals("Test reprobado","¡Lo sentimos no podemos continuar con tu compra!", elemento.getText());
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
