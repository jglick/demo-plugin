package demo;

import hudson.Extension;
import hudson.model.User;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;
import hudson.util.ListBoxModel;
import org.kohsuke.stapler.DataBoundConstructor;

public class FavoriteColorProperty extends UserProperty {

    private final String color;

    @DataBoundConstructor
    public FavoriteColorProperty(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }

    @Extension
    public static class DescriptorImpl extends UserPropertyDescriptor {

        @Override public String getDisplayName() {
            return Messages.FavoriteColorProperty_favorite_color();
        }

        @Override public UserProperty newInstance(User user) {
            return new FavoriteColorProperty("red");
        }

        public ListBoxModel doFillColorItems() {
            return new ListBoxModel().add("red").add("green").add("blue");
        }

    }

}
