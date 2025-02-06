package edu.escuelaing.arep;

import static edu.escuelaing.arep.HttpServer.staticFiles;

import java.io.IOException;
import java.net.URISyntaxException;

import static edu.escuelaing.arep.HttpServer.get;
import static edu.escuelaing.arep.HttpServer.post;

public class WebApplication {

    public static void main(String[] args) throws IOException, URISyntaxException {
        staticFiles("resources");
        get("/app/hello",(req, resp) -> "Hello perros");
        get("/app/pi", (req, resp) -> String.valueOf(Math.PI));
        post("/app/hellopost", (req, resp) -> "Post received: " + req.getQueryParam("name"));
        HttpServer.start(args);
    }
}
