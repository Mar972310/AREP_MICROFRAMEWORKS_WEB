package edu.escuelaing.arep;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.function.BiFunction;

/**
 *
 * @author Maria Valentina Torres Monsalve
 */

public class HttpServer {
    private static final int PORT = 35000;
    private boolean running = true;
    private ServerSocket serverSocket;
    private static String ruta = "src/main/java/edu/escuelaing/arep/";
    static HashMap<String, BiFunction<HttpRequest, HttpResponse, String>> servicesGet = new HashMap<>();
    static HashMap<String, BiFunction<HttpRequest, HttpResponse, String>> servicesPost = new HashMap<>();

    public static void main(String[] args) throws IOException, URISyntaxException {
        HttpServer server = new HttpServer();
        System.out.println(ruta);
        server.startServer();
    }

    public void startServer() throws IOException, URISyntaxException {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Failed to start server on port: " + PORT);
            throw e;
        }

        while (running) {
            try {
                System.out.println("Ready to receive ...");
                Socket clientSocket = serverSocket.accept();
                HttpRequestHandler requestHandler = new HttpRequestHandler(clientSocket,ruta);
                requestHandler.handlerRequest();
            } catch (IOException e) {
                if (!running) {
                    System.out.println("Server stopped.");
                    break;
                }
                e.printStackTrace();
            }
        }
    }

    public static void get(String path, BiFunction<HttpRequest, HttpResponse, String> restService) {
        servicesGet.put(path, restService);
    }

    public static void staticFiles(String path) {
        ruta = ruta + path;
    }

    public static void post(String path, BiFunction<HttpRequest, HttpResponse, String> restService) {
        servicesPost.put(path, restService);
    }

    public HashMap<String, BiFunction<HttpRequest, HttpResponse, String>> getServicesGet(){
        return servicesGet;
    }

    public HashMap<String, BiFunction<HttpRequest, HttpResponse, String>> getServicesPost(){
        return servicesPost;
    }
    
    public void stopServer() {
        running = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
                System.out.println("Server stopped successfully.");
            } catch (IOException e) {
                System.err.println("Error closing server: " + e.getMessage());
            }
        }
    }
}
