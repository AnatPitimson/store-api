package webapi.restapplication.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import webapi.restapplication.model.Product;
import webapi.restapplication.repository.ProductRepo;


//נותן אופציה לעבוד עם הדאטה לפי שימוש הלקוח עם הAPI

@Configuration//המחלקה הזאת נטענת לפני שהקוד עולה
//מקרה פרטי של component


public class SeedDB {

    // הProducts ייטענו עוד לפני שהקוד יפעל כך שנוכל לראות את הProducts לדוג באמצעות get

    //פה יהיה Products כדי שנבדוק שהקוד עובד במקום שנכניס אחד אחד ידני

    // logger זה הדפסה לקונסול
    private static final Logger logger= LoggerFactory.getLogger(SeedDB.class);

    @Bean// אובייקטים אלו מאוותחלים ומנוהלים על ידי הסביבה
    CommandLineRunner seedDataBase(ProductRepo myProducts)
    //CommandLineRunner זה
    // הBean האובייקטים ירוצו שורה אחר שורה באמצעות הסביבה ומיד שנפעיל את הקוד השורות הללו ירוצו
    //ProductRepo זה
    //מקשר בין הקוד לSQL
    {
        return args -> {
            logger.info("logging"+myProducts.save(new Product("AirPods v3 2021","Headphones",250.0,1)));
            logger.info("logging"+myProducts.save(new Product("iphone 13","Cellphones",1000.0,1)));
            logger.info("logging"+myProducts.save(new Product("Macbook Pro 13 2021","Laptops",1500,1)));
            logger.info("logging"+myProducts.save(new Product("Samsung Galaxy 22","Cellphones",1500,1)));
        };
    }

}
