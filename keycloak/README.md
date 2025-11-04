# Keycloak

## Start Keycloak

Start Keycloak in development mode:

```shell
./keycloak-26.4.2/bin/kc.sh start-dev --http-port 8180
```

## Configure Keycloak

Go to [Keycloak Admin Console](http://localhost:8180) and create user admin/admin;

From [Keycloak Admin Console](http://localhost:8180) create and configure the realm we are going to use:
* create Realm "example_realm"
* create User "user1" and, under "Credentials" tabs, set password "passwordUser1"
* create Role "user-role" in "Realm roles"
* add role "user-role" to user "user1" in tab "Role Mapping" for the user

From [Keycloak Admin Console](http://localhost:8180), in the "example_realm", configure a client for our WildFly application:
* Client type: SAML
* Client ID:  wildfly-keycloak-application
* Root URL:  http://localhost:8080
* Home URL:  http://localhost:8080
* Valid post logout redirect URIs: http://localhost:8080/user-servlet/*
* Master SAML Processing URL: http://localhost:8080/user-servlet/saml
* "Sign documents": OFF
* Keys -> Client signature required: OFF

Optional principal mapping:
* select "wildfly-keycloak-application-dedicated" from "Client scopes" for the "wildfly-keycloak-application" client
* "Configure a new mapper": select “User Attribute” and set “username” everywhere and “unspecified” in NameFormat
* uncomment the two line starting with "/subsystem=keycloak-saml/secure-deployment=ROOT.war/SP=wildfly-keycloak-application:write-attribute(name=PrincipalNameMapping" in keycloak-saml-subsystem.cli
