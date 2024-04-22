package com.example.usmobile.service.serviceImpl.config;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class EmbeddedMongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Bean(destroyMethod = "stop")
    public MongodExecutable mongodExecutable() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        return starter.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(host, port, Network.localhostIsIPv6()))
                .build());
    }
}

