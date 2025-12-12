package com.mtp.ehr.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.session.DefaultSessionFactory;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
@EnableCassandraRepositories(
    basePackages = "com.mtp.ehr.repo1",
    cassandraTemplateRef = "cassandraTemplate1"
)
public class CassandraConfig1 extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "ehr_db"; 
    }

    @Override
    protected String getContactPoints() {
        return "localhost"; 
    }

    @Primary
    @Bean(name = "cassandraSession1")
    public CqlSession cassandraSession1() {
        return CqlSession.builder()
                .withKeyspace(getKeyspaceName())
                .addContactPoint(java.net.InetSocketAddress.createUnresolved(getContactPoints(), 9042))
                .withLocalDatacenter("datacenter1") 
                .build();
    }

    @Bean(name = "cassandraTemplate1")
    public CassandraTemplate cassandraTemplate1(@Qualifier("cassandraSession1") CqlSession session) {
        return new CassandraAdminTemplate(new DefaultSessionFactory(session), cassandraConverter());
    }
}
