package ru.itlab.rpiserver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Конфигурация прилдожения.
 */
@Configuration
public class SpringConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SpringConfiguration.class);

    /**
     * Параметры приложения.
     *
     * @return параметры
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        final PropertySourcesPlaceholderConfigurer propsConfigurer = new PropertySourcesPlaceholderConfigurer();
        propsConfigurer.setIgnoreResourceNotFound(true);
        final List<Resource> resources = new ArrayList<>();
        resources.add(new ClassPathResource("application.properties"));
        try {
            resources.add(new ClassPathResource(InetAddress.getLocalHost().getHostName() + ".properties"));
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        propsConfigurer.setLocations(resources.toArray(new Resource[0]));
        return propsConfigurer;
    }

}
