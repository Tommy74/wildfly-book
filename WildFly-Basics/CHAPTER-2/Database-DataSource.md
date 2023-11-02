# DATABASES - Using a DataSource

There are a couple of options to create a connection to a database, the so called "datasource":
* manual
* using a feature-pack

Let's say you want to connect to some PostgresSQL database;

First we start one

```shell
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=postgresdb
podman run -it --name=postgres --rm --network host -e POSTGRES_USER=$POSTGRES_USER -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD -e POSTGRES_DB=$POSTGRES_DB postgres
```

## Manual

In this case we are using an already existing WildFly installation, e.g. in `./wildfly` and we are modifying it to connect to PostgresSQL;

Start WildFly as usual:

```shell
./wildfly/bin/standalone.sh --server-config=standalone-ha.xml
```

Deploy the driver you need:
```shell
wget https://jdbc.postgresql.org/download/postgresql-42.6.0.jar
cp cp postgresql-42.6.0.jar ./wildfly/standalone/deployments/
```

Create a connection using that driver:

```shell
data-source add --name=postgresqlDS --jndi-name=java:jboss/datasources/postgresqlDS --driver-name=postgresql-42.6.0.jar --connection-url=jdbc:postgresql://127.0.0.1:5432/postgresdb --enabled=true --jta=true --use-java-context=true --transaction-isolation=TRANSACTION_READ_COMMITTED --min-pool-size=1 --max-pool-size=5 --pool-prefill=true --user-name=postgres --password=postgres --prepared-statements-cache-size=32 --share-prepared-statements=true
```

Build and deploy the sample application in the `[Manual](Manual)` subfolder:

```shell
cd Manual
mvn package
cp target/datasource-basic.war ./wildfly/standalone/deployments/
```

Verify it is working

```shell
curl http://127.0.0.1:8080/datasource-basic/demo
<h1>Datasource example</h1>Time from Database: 2023-11-02 18:34:31.917984+01
```

## Using a feature-pack

### WildFly

### Bootable Jar