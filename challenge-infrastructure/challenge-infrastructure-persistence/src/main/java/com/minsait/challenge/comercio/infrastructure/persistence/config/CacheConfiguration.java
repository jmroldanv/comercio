package com.minsait.challenge.comercio.infrastructure.persistence.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer {

    public static final String CACHE_NAME = "prices";

    @Value("${application.cache.instance-name:challenge}")
    private String instanceName;

    @Value("${application.cache.ttl-seconds:3600}")
    private int ttlSeconds;

    @Bean
    @ConditionalOnMissingBean
    @Override
    public CacheManager cacheManager() {
        CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
        compositeCacheManager.setFallbackToNoOpCache(true);
        List<CacheManager> cacheManagers = new ArrayList<>();
        cacheManagers.add(hazelCastLocalInstance());
        cacheManagers.add(noOpCacheManager());
        compositeCacheManager.setCacheManagers(cacheManagers);
        return compositeCacheManager;
    }

    private CacheManager hazelCastLocalInstance() {
        HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName(instanceName);

        if (hazelcastInstance == null) {
            Config config = new Config();
            config.setInstanceName(instanceName);
            config.getJetConfig().setEnabled(false);

            NetworkConfig networkConfig = config.getNetworkConfig();
            networkConfig.setPort(5701).setPortCount(20);
            networkConfig.setPortAutoIncrement(true);

            JoinConfig joinConfig = networkConfig.getJoin();
            joinConfig.getMulticastConfig().setEnabled(false);
            joinConfig.getTcpIpConfig().addMember("localhost").setEnabled(true);

            MapConfig mapConfig = new MapConfig(CACHE_NAME);
            mapConfig.setTimeToLiveSeconds(ttlSeconds);
            config.addMapConfig(mapConfig);

            hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        }

        return new HazelcastCacheManager(hazelcastInstance);
    }

    CacheManager noOpCacheManager() {
        return new NoOpCacheManager();
    }
}
