package com.github.n9.two.app.id5899220;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App5899220 {

    static final String TEXT_HOME = "ホーム";
    static final String NEXT = "次へ";
    static final String LOGIN = "ログイン";

    static final String TWITTER_MSG_SVG = "M19.25 3.018H4.75C3.233 3.018 2 4.252 2 5.77v12.495c0 1.518 1.233 2.753 2.75 2.753h14.5c1.517 0 2.75-1.235 2.75-2.753V5.77c0-1.518-1.233-2.752-2.75-2.752zm-14.5 1.5h14.5c.69 0 1.25.56 1.25 1.25v.714l-8.05 5.367c-.273.18-.626.182-.9-.002L3.5 6.482v-.714c0-.69.56-1.25 1.25-1.25zm14.5 14.998H4.75c-.69 0-1.25-.56-1.25-1.25V8.24l7.24 4.83c.383.256.822.384 1.26.384.44 0 .877-.128 1.26-.383l7.24-4.83v10.022c0 .69-.56 1.25-1.25 1.25z";

    static final String COCONALA_URL_INDEX = "https://coconala.com/services/";
    static final String TWITTER_URL_INDEX = "https://twitter.com/";

    static final String TW_ID = "DUMMY";
    static final String TW_PW = "DUMMY";

    static final List<String> bad_categories = List.of(
            "占い"
    );

    public static void main(String[] args) throws BiffException, IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // login twitter
        driver.get("https://twitter.com/i/flow/login");
        Thread.sleep(1000*5);
        for(WebElement we : driver.findElements(By.tagName("input"))){
            we.sendKeys(TW_ID);
            break;
        }
        Thread.sleep(3000);
        for(WebElement we : driver.findElements(By.tagName("div"))) {
            if(we.getText().equals(NEXT)) {
                we.click();
                break;
            }
        }
        Thread.sleep(3000);
        for(WebElement we : driver.findElements(By.tagName("input"))) {
            if(we.getAttribute("type").equals("password")) {
                we.sendKeys(TW_PW);
                break;
            }
        }
        Thread.sleep(3000);
        for(WebElement we : driver.findElements(By.tagName("div"))) {
            if(we.getText().equals(LOGIN)) {
                we.click();
                break;
            }
        }

        Thread.sleep(1000*30);

        File f = new File("./data/", "list.xls");
        Workbook book = Workbook.getWorkbook(f);

        Sheet mainSheet = book.getSheet(0);
        for (int i = 0; i < 200; i++) { // main 200
            String twitterId = "ecl4t";
                    //mainSheet.getCell(0, i+150).getContents();
            String coconalaUrl = mainSheet.getCell(9, i+150).getContents();
            String category = null;

            driver.get(TWITTER_URL_INDEX + twitterId);

            Thread.sleep(5000);
            for(WebElement we : driver.findElements(By.tagName("div"))) {
                //if(we.getAttribute())
                System.out.println("div" + we.getAccessibleName());
                if(we.getAttribute("data-testid") != null){
                    if(we.getAttribute("data-testid").equals("sendDMFromProfile"))
                        we.click();
                    break;
                }
            }

            Thread.sleep(5000);



            /*
            if (coconalaUrl.startsWith(COCONALA_URL_INDEX)) {
                driver.get(coconalaUrl);

                boolean isLastHome = false;
                for (WebElement we : driver.findElements(By.tagName("a"))) {
                    if (we.getText().equals(TEXT_HOME)) {
                        isLastHome = true;
                        continue;
                    }
                    if (isLastHome) {
                        category = we.getText();
                        break;
                    }
                }

                System.out.println("CATEGORY = " + category);
                if (bad_categories.contains(category)) {
                    System.out.println("Category is bad");
                    continue;
                }
            } else {
                System.out.println("URL IS NOT ACTIVE");
                System.out.println("URL = " + coconalaUrl);
            }
            */

            Thread.sleep(1000*150);
        }
    }

}
