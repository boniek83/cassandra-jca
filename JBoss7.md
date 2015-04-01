# How can I use it for JBoss 7? #


  * [Create POM-project](JBoss7#Create_POM-project.md)
  * [Create RAR-project](JBoss7#Create_RAR-project.md)
  * [Create EJB-project](JBoss7#Create_EJB-project.md)
  * [Create EAR-project](JBoss7#Create_EAR-project.md)


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

<groupId>com.googlecode.cassandra-jca

Unknown end tag for &lt;/groupId&gt;



<artifactId>cassandra-jca-impl

Unknown end tag for &lt;/artifactId&gt;



<version>1.1.12

Unknown end tag for &lt;/version&gt;





Unknown end tag for &lt;/dependency&gt;





Unknown end tag for &lt;/dependencies&gt;


```

Create following files into "jboss-cassandra-example-rar/src/main/rar/META-INF" folder

ironjacamar.xml
```

<?xml version="1.0" encoding="UTF-8"?>

<ironjacamar>

<connection-definitions>

<connection-definition
class-name="com.googlecode.cassadra.jca.connection.factory.CassandraManagedConnectionFactory"
jndi-name="java:/eis/CassandraConnectionFactory">


<config-property name="Server" type="java.lang.String">localhost

Unknown end tag for &lt;/config-property&gt;



<config-property name="Port" type="java.lang.Integer">9160

Unknown end tag for &lt;/config-property&gt;



<config-property name="Timeout" type="java.lang.Integer">30000

Unknown end tag for &lt;/config-property&gt;



<pool>

<min-pool-size>5

Unknown end tag for &lt;/min-pool-size&gt;



<max-pool-size>10

Unknown end tag for &lt;/max-pool-size&gt;





Unknown end tag for &lt;/pool&gt;





Unknown end tag for &lt;/connection-definition&gt;





Unknown end tag for &lt;/connection-definitions&gt;





Unknown end tag for &lt;/ironjacamar&gt;


```

ra.xml
```

<?xml version="1.0" encoding="UTF-8"?>

<connector xmlns="http://java.sun.com/xml/ns/j2ee"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
http://java.sun.com/xml/ns/j2ee/connector_1_5.xsd"
version="1.5">

<description>Cassandra Resource Adapter

Unknown end tag for &lt;/description&gt;



<display-name>Cassandra Resource Adapter

Unknown end tag for &lt;/display-name&gt;



<vendor-name>ssarabun

Unknown end tag for &lt;/vendor-name&gt;



<eis-type>

Unknown end tag for &lt;/eis-type&gt;



<resourceadapter-version>4.2

Unknown end tag for &lt;/resourceadapter-version&gt;



<resourceadapter>

<resourceadapter-class>com.googlecode.cassadra.jca.ra.CassandraResourceAdapter

Unknown end tag for &lt;/resourceadapter-class&gt;



<outbound-resourceadapter>

<connection-definition>

<managedconnectionfactory-class>com.googlecode.cassadra.jca.connection.factory.CassandraManagedConnectionFactory

Unknown end tag for &lt;/managedconnectionfactory-class&gt;



<connectionfactory-interface>com.googlecode.cassadra.jca.api.CassandraConnectionFactory

Unknown end tag for &lt;/connectionfactory-interface&gt;



<connectionfactory-impl-class>com.googlecode.cassadra.jca.connection.factory.CassandraConnectionFactory

Unknown end tag for &lt;/connectionfactory-impl-class&gt;



<connection-interface>com.googlecode.cassadra.jca.api.CassandraConnection

Unknown end tag for &lt;/connection-interface&gt;



<connection-impl-class>com.googlecode.cassadra.jca.connection.CassandraConnection

Unknown end tag for &lt;/connection-impl-class&gt;





Unknown end tag for &lt;/connection-definition&gt;



<transaction-support>NoTransaction

Unknown end tag for &lt;/transaction-support&gt;



<reauthentication-support>false

Unknown end tag for &lt;/reauthentication-support&gt;





Unknown end tag for &lt;/outbound-resourceadapter&gt;





Unknown end tag for &lt;/resourceadapter&gt;





Unknown end tag for &lt;/connector&gt;


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
//import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.cassandra.thrift.KsDef;

import com.googlecode.cassadra.jca.api.CassandraConnection;
import com.googlecode.cassadra.jca.api.CassandraConnectionFactory;

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

<groupId>com.googlecode.cassandra-jca

Unknown end tag for &lt;/groupId&gt;



<artifactId>cassandra-jca-api

Unknown end tag for &lt;/artifactId&gt;



<version>1.1.12

Unknown end tag for &lt;/version&gt;



<scope>provided

Unknown end tag for &lt;/scope&gt;





Unknown end tag for &lt;/dependency&gt;


```

Add following plugin to "jboss-cassandra-example-ejb/pom.xml"
```

<plugin>

<groupId>org.apache.maven.plugins

Unknown end tag for &lt;/groupId&gt;



<artifactId>maven-ejb-plugin

Unknown end tag for &lt;/artifactId&gt;



<version>2.3

Unknown end tag for &lt;/version&gt;



<configuration>

<ejbVersion>3.1

Unknown end tag for &lt;/ejbVersion&gt;



<archive>

<manifestEntries>

<Dependencies>deployment.jboss-cassandra-example-ear-1.0-SNAPSHOT.ear.jboss-cassandra-example-rar-1.0-SNAPSHOT.rar

Unknown end tag for &lt;/Dependencies&gt;





Unknown end tag for &lt;/manifestEntries&gt;





Unknown end tag for &lt;/archive&gt;





Unknown end tag for &lt;/configuration&gt;





Unknown end tag for &lt;/plugin&gt;


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

<groupId>com.googlecode.cassandra.jca.example

Unknown end tag for &lt;/groupId&gt;



<artifactId>jboss-cassandra-example-rar

Unknown end tag for &lt;/artifactId&gt;



<version>1.0-SNAPSHOT

Unknown end tag for &lt;/version&gt;



<type>rar

Unknown end tag for &lt;/type&gt;





Unknown end tag for &lt;/dependency&gt;



<dependency>

<groupId>com.googlecode.cassandra.jca.example

Unknown end tag for &lt;/groupId&gt;



<artifactId>jboss-cassandra-example-ejb

Unknown end tag for &lt;/artifactId&gt;



<version>1.0-SNAPSHOT

Unknown end tag for &lt;/version&gt;



<type>ejb

Unknown end tag for &lt;/type&gt;





Unknown end tag for &lt;/dependency&gt;





Unknown end tag for &lt;/dependencies&gt;


```

