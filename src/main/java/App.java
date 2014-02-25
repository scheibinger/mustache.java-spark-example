import domain.Project;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.io.Writer;

import static spark.Spark.get;
import static spark.Spark.setPort;

public class App {
    public static void main(String[] args) {
        setPort(8082);
        initializeRoutes();
    }

    private static void initializeRoutes() {
        get(new MustacheBasedRoute("/index", "main.mustache") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException {
                Project model = new Project("Hello World");
                template.execute(writer, model);
            }
        });
    }

}
