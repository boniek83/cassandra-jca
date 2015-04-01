# How can I use it for JBoss 5/6? #

1. Add to pom.xml of ear-project following dependency

```xml

<dependency>

<groupId>com.googlecode.cassandra-jca

Unknown end tag for &lt;/groupId&gt;



<artifactId>cassandra-jca-rar

Unknown end tag for &lt;/artifactId&gt;



<version>1.1.12

Unknown end tag for &lt;/version&gt;



<type>rar

Unknown end tag for &lt;/type&gt;





Unknown end tag for &lt;/dependency&gt;


```

2. Add to pom.xml of ejb or web project following dependency

```xml

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


3. Create the file META-INF/cassandra-ds.xml into ear-project.

```xml

<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE connection-factories PUBLIC
"-//JBoss//DTD JBOSS JCA Config 1.5//EN"
"http://www.jboss.org/j2ee/dtd/jboss-ds_1_5.dtd">

<connection-factories>

<no-tx-connection-factory>

<jndi-name>eis/CassandraConnectionFactory

Unknown end tag for &lt;/jndi-name&gt;



<rar-name>your-project-name-version.ear#cassandra-jca-rar-1.1.12.rar

Unknown end tag for &lt;/rar-name&gt;



<connection-definition>com.ssarabun.jca.cassandra.api.CassandraConnectionFactory

Unknown end tag for &lt;/connection-definition&gt;



<config-property name="Server" type="java.lang.String">localhost

Unknown end tag for &lt;/config-property&gt;



<config-property name="Port" type="java.lang.Integer">9160

Unknown end tag for &lt;/config-property&gt;



<config-property name="Timeout" type="java.lang.Integer">30000

Unknown end tag for &lt;/config-property&gt;



<min-pool-size>5

Unknown end tag for &lt;/min-pool-size&gt;



<max-pool-size>10

Unknown end tag for &lt;/max-pool-size&gt;





Unknown end tag for &lt;/no-tx-connection-factory&gt;





Unknown end tag for &lt;/connection-factories&gt;


```

4. To lookup the CassandraConnectionFactory add following code to servlet or session bean.

```
    @Resource(mappedName = "java:/eis/CassandraConnectionFactory")
    private CassandraConnectionFactory cf;
```