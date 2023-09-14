import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import sn.ept.git.dic2.entities.Employe;


import static io.restassured.RestAssured.given;

public class EmployeServiceTest extends ServerTest{
    @Test
    public void listeEmployesSucces() {
        given().when().get("/employes").then().statusCode(200);
    }

    @Test
    public void creerEmploye200() {
        Employe employe = new Employe(690, "Doe", "John",  "123456789", "johndoe@example.com", null, null, null, null, (short) 1, null, null);


        given()
                .contentType(ContentType.JSON)
                .body(employe)
                .when()
                .put("/employes")
                .then()
                .statusCode(200);


    }

    @Test
    public void modifEmploye404() {
        Employe employe = new Employe();
        employe.setId(56090);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", employe.getId())
                .body(employe)
                .when()
                .put("/employes/{id}")
                .then()
                .statusCode(404);

    }

    @Test
    public void modifEmploye200() {
        Employe employe = new Employe();
        employe.setId(6);
        employe.setNom("Niska");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", employe.getId())
                .body(employe)
                .when()
                .put("/employes/{id}")
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.equalTo(employe.getId()));


    }

    @Test
    public void rechercherEmploye200() {
        given().when().get("/employes/5")
                .then().statusCode(200);
    }

    @Test
    public void rechercherEmploye404() {
        given().when().get("/employes/3003")
                .then().statusCode(404);
    }

    @Test
    public void supprimerEmploye200() {
        given().when().delete("/employes/4")
                .then().statusCode(200);
    }

    @Test
    public void supprimerEmploye404() {
        given().when().delete("/employes/3785")
                .then().statusCode(404);
    }

}
