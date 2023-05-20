package webapi.restapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapplicationApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestapplicationApplication.class, args);
	}

}

// Spring
//מבוססת על Inversion Of Control (IOC)
//שזה אומר שהתוכנית מריצה דברים כביכול בלי שקראנו לה לדוגמא SeedDB שלא קראנו לה אך המוצרים עדיין נמצאים כאילו קראנו לה
//כי סימנו את הפונקציה בBean@ עוד דוגמא זה ProductController שניתן להשתמש בפונקציות בלי שקראנו כלל למחלקה זה מנוהל לבד
//בנוסף Spring מבוססת גם על Dependency Injection
//שזה

//@Configuration
// Runtimeמה שנגדיר במחלקה הזאת יהיה זמין ב

// פה אנחנו יוצרים RestApi שזה WebApi אוסף של URL ופרמטרים שחברה מספקת ללקוחות שלה (שיכולים להיות מפתחים)
//שבה מפתחים באמצעות צד שרת
//באמצעות הפרמטרים והURL אפשר לגשת למידע לשנות למחוק להוסיף וכדומה


//"beans" refer to objects that are managed by the Spring framework's inversion of control (IoC) container.
//These objects are often referred to as "Spring beans" and are created and managed by the framework's IoC container.

//@Bean זה אומר שקיימים אובייקטים שהסביבה אחראית על האתחול השימוש והכל שלהם
//כלומר אוסף השורות הללו יופעלו ישר כשנפעיל את האפליקציה ומחזירות אובייקטים שהסביבה תנהל כלומר תחזיר Bean


//An "entity" refers to a class that represents a database table or document,
// and its properties represent the columns or fields in that table or document.
// In other words, an entity class maps to a database table
// and the properties of the entity map to the columns of that table.

