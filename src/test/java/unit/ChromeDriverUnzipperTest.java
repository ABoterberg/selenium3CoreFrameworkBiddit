package unit;

import be.biginted.drivers.chromedriver.ChromeDriverUnzipper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ChromeDriverUnzipperTest {

    @Test
    public void unzipBinary() throws Exception {
        File binary = new File("src/test/resources/chromedriver.exe");
        new ChromeDriverUnzipper().unzipChromedriver();
        Assert.assertNotNull(binary.exists());
    }
}
