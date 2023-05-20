package webapi.restapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapi.restapplication.model.Product;

import java.util.List;

//נרצה לשמור את הפרודקטים בדאטה בייס שהוא SQL אז צריך שכבה מקשרת כי פה כתוב בJava
@Repository
public interface ProductRepo extends JpaRepository<Product,Long>//נותן לנו פונקציונליות בסיסית של שאילתות SQL
// נכתוב בגאווה והוא יידע לקרוא
//כמו מציאה לפי תז נראה את זה בProductController
// ה< > מייצג את איזה סוג מידע נרצה לשמור והLong הוא של התז שאיתו מייצגים Product
{
    List<Product> getProductsByCategory(String category);
//     JpaRepositoryה
//    //ייקח את החתימות האלה ויהפוך אותם לשאילתות SQL
    //List<Product> getProductsByProductName(String name);
//    List<Product> getProductByOrderId();
}

