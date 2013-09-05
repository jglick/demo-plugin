package demo;

import hudson.Extension;
import hudson.cli.CLICommand;
import hudson.model.User;
import org.kohsuke.args4j.Option;

@Extension
public class SetColorCommand extends CLICommand {

    @Option(name="--color", aliases="-c", metaVar="COLOR", usage="Color, such as ‘red’.", required=true)
    public String color;

    @Override public String getShortDescription() {
        return "Sets the current user’s favorite color.";
    }

    @Override protected int run() throws Exception {
        User u = User.current();
        if (u == null) {
            stderr.println("You must log in first.");
            return 1;
        }
        u.addProperty(new FavoriteColorProperty(color));
        return 0;
    }

}
