package unit;

import be.biginted.utilities.DriverDownloader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class GeckoDriverDownloaderTest {

    @Test
    public void downloadBinary() throws Exception {
        File binary = new File("src/test/resources/geckodriver-v0.17.0-win32.zip");
        new DriverDownloader().downloadBinary("Firefox");
        Assert.assertNotNull(binary.exists());
    }

}