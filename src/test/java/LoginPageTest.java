import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPageTest {
    private WebDriver driver;

    @BeforeAll
    public void setupAll(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    @Test
    public void LoginPageTest(){
        driver.navigate().to(Config.SAUCE_URL);

    }
}
