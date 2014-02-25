import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class MustacheBasedRoute extends Route {
    final Mustache template;

    protected MustacheBasedRoute(String path, String template) {
        super(path);

        DefaultMustacheFactory mustacheFactory = new DefaultMustacheFactory("tpl");

        this.template = mustacheFactory.compile(template);
    }

    @Override
    public Object handle(Request request, Response response) {
        StringWriter writer = new StringWriter();
        try {
            doHandle(request, response, writer);
        } catch (Exception e) {
            e.printStackTrace();
            response.redirect("/internal_error");
        }
        return writer;
    }

    protected abstract void doHandle(final Request request, final Response response, final Writer writer)
            throws IOException;
}
