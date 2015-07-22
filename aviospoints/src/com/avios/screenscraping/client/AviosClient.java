package com.avios.screenscraping.client;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import com.gargoylesoftware.htmlunit.Cache;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlHeader;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;


public class AviosClient{

	private String username; 
	private String password;
	
    public AviosClient(String username, String password){
    	this.username=username; 
     	this.password=password;
    }
	
	public BigDecimal fetchBalance() {
		WebClient browser = new WebClient();
		/*ProxyConfig config = new ProxyConfig();
		config.setProxyHost("localhost");
		config.setProxyPort(8888);
		*/


        browser.getOptions().setJavaScriptEnabled(false);
        browser.getOptions().setCssEnabled(false);
		// TODO Auto-generated method stub
		HtmlPage currentPage = null;
		BigDecimal balance = new BigDecimal(0);
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRENCH);
		//nf.setCurrency(Currency.getInstance(Locale.ENGLISH));
		
		try {
			currentPage = browser.getPage("http://www.avios.com");
			currentPage = browser.getPage("http://www.avios.com/gb/en_gb/?from=countryselection");
			currentPage = browser.getPage("http://www.avios.com/gb/en_gb/?from=countryselection");
			currentPage = browser.getPage("http://www.avios.com/gb/en_gb/?from=countryselection");
			((HtmlTextInput)currentPage.getElementByName("j_username")).setValueAttribute(username);
			((HtmlPasswordInput)currentPage.getElementByName("j_password")).setValueAttribute(password);
			//currentPage = ((HtmlInput)((HtmlForm)currentPage.getFormByName("loginForm")).getInputByValue("Log in")).click();
			currentPage = ((HtmlInput)currentPage.getElementById("hme-login")).click();
			//loginpage clicked
			//System.out.print(currentPage.asText());

			currentPage = browser.getPage("https://www.avios.com/gb/en_gb/my-account/your-avios-account");
			String balanceDisplayed = ((HtmlParagraph)(currentPage.getFirstByXPath("//html/body/div[1]/div/div[1]/div/div/div/div/div/ul/li[1]/div/div[2]/ul/li/p"))).asText();
			//String accountStatus= (String)((HtmlDivision)currentPage.getElementById("acc-status")).asText();
			//System.out.println(accountStatus);
			balance = BigDecimal.valueOf(Integer.parseInt(balanceDisplayed.replaceAll(" Avios", "").replaceAll(",", "")));
		
			
			browser.closeAllWindows();
			browser.setCache(new Cache());
			browser.setCookieManager(new CookieManager());
			
			
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	
	}
}
