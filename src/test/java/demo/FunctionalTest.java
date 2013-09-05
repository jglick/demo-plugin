package demo;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import hudson.model.FreeStyleProject;
import hudson.model.User;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class FunctionalTest {

    @Rule
    public JenkinsRule rule = new JenkinsRule();

    @Test
    public void usingFavoriteColorAndFormatter() throws Exception {
        rule.jenkins.setMarkupFormatter(new BoldfacingMarkupFormatter());
        rule.jenkins.setSecurityRealm(rule.createDummySecurityRealm());
        User.get("alice").addProperty(new FavoriteColorProperty("red"));
        FreeStyleProject project = rule.createFreeStyleProject();
        project.setDescription("This _is_ fun.");
        JenkinsRule.WebClient wc = rule.createWebClient();
        wc.login("alice");
        boolean found = false;
        for (HtmlElement e : wc.getPage(project).getElementsByTagName("span")) {
            if (e.getAttribute("style").equals("font-weight: bold; color: red") && e.getTextContent().equals("is")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

}
