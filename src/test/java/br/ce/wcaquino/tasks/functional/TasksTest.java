package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
	    ChromeOptions options = new ChromeOptions();
	    WebDriver driver = new RemoteWebDriver(new URL("http://192.168.100.52:4444/wd/hub"), options);
	    driver.navigate().to("http://192.168.100.52:8001/tasks");
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
		//clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem com sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Sucess!", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
		//clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem com sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
		//clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem com sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
		//clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem com sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
}
