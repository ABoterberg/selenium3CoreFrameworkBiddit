package browser;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ProxyManager {

    private BrowserMobProxy browserMobProxy;
    private Proxy seleniumProxy;
    private DesiredCapabilities desiredCapabilities;

    private BrowserMobProxy startBrowserMobProxy() {
        browserMobProxy.blacklistRequests("^http.*png$", 200);
        browserMobProxy.blacklistRequests("^http.*jpg$", 200);
        browserMobProxy.blacklistRequests("^http.*jpeg$", 200);
        browserMobProxy.start();
        return browserMobProxy;
    }

    private Proxy createSeleniumProxy(BrowserMobProxy browserMobProxy) {
        if (null == browserMobProxy) {
            startBrowserMobProxy();
        } else {
            seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxy);
        }
        return seleniumProxy;
    }

    private DesiredCapabilities setProxyCapability(DesiredCapabilities capabilities, Proxy seleniumProxy) {
        if (null == capabilities) {
            throw new RuntimeException("Capabilities have not been set yet!");
        }
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        return capabilities;
    }

    public ProxyManager() {
        browserMobProxy = new BrowserMobProxyServer();
        desiredCapabilities = new DesiredCapabilities();
        startBrowserMobProxy();
        seleniumProxy = createSeleniumProxy(browserMobProxy);
        setProxyCapability(desiredCapabilities, seleniumProxy);
    }

    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    private void filterImages() {
        browserMobProxy.addRequestFilter((response, contents, messageInfo) -> {
            if (messageInfo.getOriginalUrl().endsWith("png")) {
                contents.setTextContents("");
            }
            return null;
        });
    }
}
