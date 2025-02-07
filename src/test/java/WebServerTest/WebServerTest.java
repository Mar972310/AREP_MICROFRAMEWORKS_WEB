package WebServerTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import static edu.escuelaing.arep.HttpServer.get;
import static edu.escuelaing.arep.HttpServer.post;
import static edu.escuelaing.arep.HttpServer.staticFiles;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import edu.escuelaing.arep.HttpServer;

/**
 *
 * @author Maria Valentina Torres Monsalve
 */

public class WebServerTest {

    
    private static final String URL = "http://localhost:35000/";
    private static HttpServer server;
    private static Thread serverThread;


    @BeforeClass
    public static void setUp() {
        try {
            server = new HttpServer();
            serverThread = new Thread(() -> {
                try {
                    HttpServer.staticFiles("resources/static");
                    HttpServer.get("/app/hello",(req, resp) -> "Hello, world");
                    HttpServer.get("/app/helloget",(req, resp) -> "Get received: " + req.getQueryParam("name"));
                    HttpServer.get("/app/pi", (req, resp) -> String.valueOf(Math.PI));
                    HttpServer.post("/app/hellopost", (req, resp) -> "Post received: " + req.getQueryParam("name"));
                    server.startServer();
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();
            Thread.sleep(1000); 
            System.out.println("Servidor iniciado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void shouldLoadStaticFileHtml() throws Exception {

        String file = "index.html";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notShouldLoadStaticFileHtml() throws Exception {
        String file = "web.html";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(404, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoadStaticFileCss() throws Exception {
   
        String file = "style.css";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notShouldLoadStaticFileCss() throws Exception {
    
        String file = "styles.css";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(404, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoadStaticFileJs() throws Exception {
        String file = "script.js";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notShouldLoadStaticFileJs() throws Exception {
        String file = "prueba.js";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(404, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoadStaticImagePNG() throws Exception {
        String file = "imagen1.png";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoadStaticImageJPG() throws Exception {
        
        String file = "imagen2.jpg";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notShouldLoadStaticImagePNG() throws Exception {
        String file = "imagen8.png";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(404, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notShouldLoadStaticImageJPG() throws Exception {
        String file = "imagen5.jpg";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(404, responseCode);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoadRestGet() throws Exception {
        String file = "app/helloget?name=maria";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String response = in.readLine();
            in.close();
            assertEquals("{\"response\":\"Get received: maria\"}", response);
            request.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoadRestHello() throws Exception {
        String file = "app/hello";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("GET");
            int responseCode = request.getResponseCode();
            assertEquals(200, responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String response = in.readLine();
            in.close();
            assertEquals("{\"response\":\"Hello, world\"}", response);
            request.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoadRestPost() throws Exception {
        String file = "app/hellopost?name=valentina";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("POST");
            int responseCode = request.getResponseCode();
            assertEquals(201, responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String response = in.readLine();
            in.close();
            assertEquals("{\"response\":\"Post received: valentina\"}", response);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notShouldLoadRestPost() throws Exception {
        String file = "app/hello/x?name=valentina";
        try {
            URL requestUrl = new URL(URL + file);
            HttpURLConnection request = (HttpURLConnection) requestUrl.openConnection();
            request.setRequestMethod("POST");
            int responseCode = request.getResponseCode();
            assertEquals(404, responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String response = in.readLine();
            in.close();
            assertEquals("{\"response\":Method not supported}", response);
            request.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void tearDown(){
        try {
            server.stopServer();
            serverThread.join(); 
            System.out.println("Servidor cerrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

}
