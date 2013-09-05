package demo;

import hudson.Extension;
import hudson.markup.MarkupFormatter;
import hudson.markup.MarkupFormatterDescriptor;
import hudson.model.User;
import java.io.IOException;
import java.io.Writer;
import org.kohsuke.stapler.DataBoundConstructor;

public class BoldfacingMarkupFormatter extends MarkupFormatter {

    @DataBoundConstructor
    public BoldfacingMarkupFormatter() {}

    @Override public void translate(String markup, Writer output) throws IOException {
        User u = User.current();
        String color = u != null ? u.getProperty(FavoriteColorProperty.class).getColor() : "black";
        output.write(markup.replaceAll("_([^_]+)_", "<span style='font-weight: bold; color: " + color + "'>$1</span>"));
    }

    @Extension
    public static class DescriptorImpl extends MarkupFormatterDescriptor {

        @Override public String getDisplayName() {
            return Messages.BoldfacingMarkupFormatter_boldface();
        }

    }

}
