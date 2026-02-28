package com.davinchicoder.spring_otel.config;

import io.pyroscope.http.Format;
import io.pyroscope.javaagent.EventType;
import io.pyroscope.javaagent.PyroscopeAgent;
import io.pyroscope.javaagent.config.Config;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class PyroscopeConfig {

    private final List<String> supportedOS = List.of("linux", "mac", "darwin");

    @PostConstruct
    public void init() {

        String osName = System.getProperty("os.name", "").toLowerCase();

        boolean isSupported = supportedOS.contains(osName);

        if (!isSupported) {
            log.info("Pyroscope profiling unabled: Unsupported OS {}", osName);
            return;
        }

        PyroscopeAgent.start(
                new Config.Builder()
                        .setApplicationName("spring-otel")
                        .setProfilingEvent(EventType.ITIMER)
                        .setFormat(Format.JFR)
                        .setServerAddress("http://localhost:9999")
                        .build()
        );
    }
}
