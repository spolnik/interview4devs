package com.hiringdefined;

import com.hiringdefined.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@EnableZuulProxy
@RestController
@EnableRedisHttpSession
public class UiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(UiApplication.class);

    @Autowired
    private Environment env;

    @Autowired(required = false)
    private DatabaseLoader databaseLoader;

    /**
     * Initializes hiringdefined.
     * <p/>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p/>
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="http://jhipster.github.io/profiles.html">http://jhipster.github.io/profiles.html</a>.
     * </p>
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            LOG.warn("No Spring profile configured, running with default configuration");
        } else {
            LOG.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
            Collection activeProfiles = Arrays.asList(env.getActiveProfiles());
            if (activeProfiles.contains("dev") && activeProfiles.contains("prod")) {
                LOG.error("You have misconfigured your application! " +
                        "It should not run with both the 'dev' and 'prod' profiles at the same time.");
            }
            if (activeProfiles.contains("prod") && activeProfiles.contains("fast")) {
                LOG.error("You have misconfigured your application! " +
                        "It should not run with both the 'prod' and 'fast' profiles at the same time.");
            }
            if (activeProfiles.contains("dev") && activeProfiles.contains("cloud")) {
                LOG.error("You have misconfigured your application! " +
                        "It should not run with both the 'dev' and 'cloud' profiles at the same time.");
            }
        }
    }

    @RequestMapping("/user")
    public Map<String, Object> user(Principal user) {
        if (user == null)
            return null;

        return Collections.<String, Object> singletonMap("name", user.getName());
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(UiApplication.class);
        app.setShowBanner(false);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(app, source);
        Environment env = app.run(args).getEnvironment();

        LOG.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    /**
     * If no profile has been configured, set by default the "dev" profile.
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active") &&
                !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

            app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
        }
    }
}
