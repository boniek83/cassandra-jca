# How can I use it for JBoss 7? #


  * [Create POM-project](JBoss7_v1_0_12#Create_POM-project.md)
  * [Create RAR-project](JBoss7_v1_0_12#Create_RAR-project.md)
  * [Create EJB-project](JBoss7_v1_0_12#Create_EJB-project.md)
  * [Create EAR-project](JBoss7_v1_0_12#Create_EAR-project.md)


## Create POM-project ##
To create POM-project's have to execute following command
```
mvn archetype:generate \
-DarchetypeGroupId=org.codehaus.mojo.archetypes \
-DarchetypeArtifactId=pom-root \
-DgroupId=com.googlecode.cassandra.jca.example \
-DartifactId=jboss-cassandra-example-parent \
-Dversion=1.0-SNAPSHOT 
```

After that go to jboss-cassandra-example-parent folder
```
cd jboss-cassandra-example-parent
```

## Create RAR-project ##
To create RAR-project's have to execute following command into jboss-cassandra-example-parent folder
```
mvn archetype:generate \
-DarchetypeGroupId=org.codehaus.mojo.archetypes \
-DarchetypeArtifactId=pom-root \
-DgroupId=com.googlecode.cassandra.jca.example \
-DartifactId=jboss-cassandra-example-rar \
-Dversion=1.0-SNAPSHOT 
```

Change packaging type to 'rar' in  jboss-cassandra-example-rar/pom.xml file
```

<packaging>rar

Unknown end tag for &lt;/packaging&gt;


```

Add following dependence in jboss-cassandra-example-rar/pom.xml file
```
<dependencies>
    <dependency>
        <groupId>com.googlecode.cassandra-jca</groupId>
        <artifactId>cassandra-jca-impl</artifactId>
        <version>1.0.12.1</version>
            <exclusions>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.apache.httpcomponents</groupId>
                    <artifactId>httpclient</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.google.guava</groupId>
                    <artifactId>guava</artifactId>
                </exclusion>
            </exclusions>
    </dependency>
</dependencies>
```

Create following files into "jboss-cassandra-example-rar/src/main/rar/META-INF" folder

ironjacamar.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<ironjacamar>
    <connection-definitions>
        <connection-definition 
            class-name="com.googlecode.cassandra.jca.connectionfactory.CassandraManagedConnectionFactory" 
            jndi-name="java:/eis/CassandraConnectionFactory">

            <config-property name="Server" type="java.lang.String">localhost</config-property>
            <config-property name="Port" type="java.lang.Integer">9160</config-property>
            <config-property name="Timeout" type="java.lang.Integer">30000</config-property>
            <pool>
                <min-pool-size>5</min-pool-size>
                <max-pool-size>10</max-pool-size>
            </pool>
        </connection-definition>
    </connection-definitions>
</ironjacamar>
```

ra.xml
```
<?xml version="1.0" encoding="UTF-8"?>

<connector xmlns="http://java.sun.com/xml/ns/j2ee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
           http://java.sun.com/xml/ns/j2ee/connector_1_5.xsd"
           version="1.5">

    <description>Cassandra Resource Adapter</description>
    <display-name>Cassandra Resource Adapter</display-name>
    <vendor-name>sergey.sarabun@gmail.com</vendor-name>
    <eis-type></eis-type>

    <resourceadapter-version>4.2</resourceadapter-version>
    <resourceadapter>
        <resourceadapter-class>com.googlecode.cassandra.jca.ra.CassandraResourceAdapter</resourceadapter-class>
        <outbound-resourceadapter>
            <connection-definition>
                <managedconnectionfactory-class>com.googlecode.cassandra.jca.connectionfactory.CassandraManagedConnectionFactory</managedconnectionfactory-class>
                <connectionfactory-interface>com.googlecode.cassandra.jca.api.CassandraConnectionFactory</connectionfactory-interface>
                <connectionfactory-impl-class>com.googlecode.cassandra.jca.connectionfactory.CassandraConnectionFactory</connectionfactory-impl-class>
                <connection-interface>com.googlecode.cassadra.jca.api.CassandraConnection</connection-interface>
                <connection-impl-class>com.googlecode.cassandra.jca.connection.CassandraConnection</connection-impl-class>
            </connection-definition>
            <transaction-support>NoTransaction</transaction-support>
            <reauthentication-support>false</reauthentication-support>
        </outbound-resourceadapter>
    </resourceadapter>
</connector>
```

## Create EJB-project ##
```
mvn archetype:generate \
-DarchetypeGroupId=org.codehaus.mojo.archetypes \
-DarchetypeArtifactId=ejb-javaee6 \
-DgroupId=com.googlecode.cassandra.jca.example \
-DartifactId=jboss-cassandra-example-ejb \
-Dversion=1.0-SNAPSHOT 
```

Create Singleton
```

package com.googlecode.cassandra.jca.example;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.cassandra.thrift.KsDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.cassandra.jca.api.CassandraConnection;
import com.googlecode.cassandra.jca.api.CassandraConnectionFactory;


@Singleton
@Startup
public class MySingletonBean {

    @Resource(mappedName = "java:/eis/CassandraConnectionFactory")
    private CassandraConnectionFactory cf;

    @PostConstruct
    public void postConstruct() throws Exception {
        CassandraConnection c = null;
        try {
            c = cf.getConnection();

            List<KsDef> list = c.getInternalConnection().describe_keyspaces();
            for (KsDef ksDef : list) {
                System.out.println("keyspace_name = " + ksDef.name);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
}

```

Add following dependency to "jboss-cassandra-example-ejb/pom.xml"
```
<dependency>
    <groupId>com.googlecode.cassandra-jca</groupId>
    <artifactId>cassandra-jca-api</artifactId>
    <version>1.0.12.1</version>
    <scope>provided</scope>
</dependency>
```

Add following plugin to "jboss-cassandra-example-ejb/pom.xml"
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-ejb-plugin</artifactId>
    <version>2.3</version>
    <configuration>
        <ejbVersion>3.1</ejbVersion>
        <archive>
            <manifestEntries>
                <Dependencies>deployment.jboss-cassandra-example-ear-1.0-SNAPSHOT.ear.jboss-cassandra-example-rar-1.0-SNAPSHOT.rar</Dependencies>
            </manifestEntries>
        </archive>
    </configuration>
</plugin>
```


## Create EAR-project ##
```
mvn archetype:generate \
-DarchetypeGroupId=org.codehaus.mojo.archetypes \
-DarchetypeArtifactId=ear-javaee6 \
-DgroupId=com.googlecode.cassandra.jca.example \
-DartifactId=jboss-cassandra-example-ear \
-Dversion=1.0-SNAPSHOT 
```

```
<dependencies>
    <dependency>
        <groupId>com.googlecode.cassandra.jca.example</groupId>
        <artifactId>jboss-cassandra-example-rar</artifactId>
        <version>1.0-SNAPSHOT</version>
        <type>rar</type>
    </dependency>
    <dependency>
        <groupId>com.googlecode.cassandra.jca.example</groupId>
        <artifactId>jboss-cassandra-example-ejb</artifactId>
        <version>1.0-SNAPSHOT</version>
        <type>ejb</type>
    </dependency>
</dependencies>
```

