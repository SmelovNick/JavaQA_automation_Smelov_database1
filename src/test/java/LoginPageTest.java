import Db.InsertUsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import service.Config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LoginPageTest {
    private WebDriver driver;
    static List<User> users;

    @BeforeAll
    public static void setupAll() throws SQLException {
        WebDriverManager.chromedriver().setup();
        users = InsertUsers.execute();
    }
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    @ParameterizedTest
    @MethodSource("userCredentialsStream")
    public void LoginPageTest(String login, String password){
        driver.navigate().to(Config.SAUCE_URL);
        driver.findElement(By.xpath("//input[contains(@class, 'form_input') and @data-test='username']")).clear();
        driver.findElement(By.xpath("//input[contains(@class, 'form_input') and @data-test='username']")).sendKeys(login);

        driver.findElement(By.xpath("//input[contains(@class, 'form_input') and @data-test='password']")).clear();
        driver.findElement(By.xpath("//input[contains(@class, 'form_input') and @data-test='password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[contains(@class, 'submit-button')]")).click();

        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='header_secondary_container']")).isDisplayed());
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

    static Stream<Arguments> userCredentialsStream(){
        Stream<Arguments> cases = Stream.empty();

        List<Arguments> arguments = new ArrayList<>();
        users.forEach(user ->{
            arguments.add(arguments(user.getUsername(), user.getPassword()));
                });
        return arguments.stream();
    }
}
