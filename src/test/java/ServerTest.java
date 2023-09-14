import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class ServerTest {
    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/projetJEE-1.0-SNAPSHOT/api/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://127.0.0.1";
        }
        RestAssured.baseURI = baseHost;

    }
}
