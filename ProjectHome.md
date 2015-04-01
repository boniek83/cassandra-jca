When I was developing enterprise applications for JBoss, I faced the following problem. There are not enough drivers, which adapted enterprise platform for NoSQL database. The purpose of the project is to create adapter for Apache Cassandra database.

Using the cassandra-jca, will simplify the development of your application and you will not care about closing connections and you must not develop you connection-pool or use external one.

We support following versions of Apache Cassandra database:

| **Apache Cassandra version** | **cassandra-jca version** |
|:-----------------------------|:--------------------------|
|1.0.12|1.0.12.1|
|1.1.12|1.1.12.1|
|1.2.5|1.2.5.1|
|1.2.6|1.2.6.1|
|1.2.7|1.2.7|
|1.2.8|1.2.8|
|1.2.9|1.2.9|
|2.0.6|2.0.6|

In the near future, we plan to support following versions of Apache Cassandra database:
  * 1.2.19
  * 2.0.10
  * 2.1.0
If you need to use other version of Apache Cassandra database and we don't support it, let us know about it. You have to create open issue and we will be do it.

