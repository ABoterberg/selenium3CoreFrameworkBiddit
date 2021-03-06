package unit;

import be.biginted.drivers.iedriver.IeDriverUnzipper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class IeDriverUnzipperTest {

    @Test @Ignore
    public void unzipBinary() throws Exception {
        File binary = new File("src/test/resources/IEDriverServer.exe");
        new IeDriverUnzipper().unzipIedriver();
        Assert.assertNotNull(binary.exists());
    }
}
