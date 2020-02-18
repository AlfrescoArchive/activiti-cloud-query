# Activiti Cloud Notifications GraphQL

[![Join Us in Gitter](https://badges.gitter.im/Activiti/Activiti7.svg)](https://gitter.im/Activiti/Activiti7?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status Travis](https://travis-ci.com/Activiti/activiti-cloud-notifications-graphql.svg?branch=master)](https://travis-ci.com/Activiti/activiti-cloud-notifications-graphql)
[![Coverage Status](http://img.shields.io/codecov/c/github/Activiti/activiti-cloud-notifications-graphql/master.svg?maxAge=86400)](https://codecov.io/gh/Activiti/activiti-cloud-notifications-graphql)
[![ASL 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/Activiti/activiti-cloud-notifications-graphql/blob/master/LICENSE.txt)
[![CLA](https://cla-assistant.io/readme/badge/Activiti/activiti-cloud-notifications-graphql)](https://cla-assistant.io/Activiti/activiti-cloud-notifications-graphql)
[![Docker Build Status](https://img.shields.io/docker/build/activiti/activiti-cloud-notifications-graphql.svg)](https://hub.docker.com/r/activiti/activiti-cloud-notifications-graphql/)
[![security status](https://www.meterian.com/badge/gh/Activiti/activiti-cloud-notifications-graphql/security)](https://www.meterian.com/report/gh/Activiti/activiti-cloud-notifications-graphql)
[![stability status](https://www.meterian.com/badge/gh/Activiti/activiti-cloud-notifications-graphql/stability)](https://www.meterian.com/report/gh/Activiti/activiti-cloud-notifications-graphql)

[Docker Image](https://hub.docker.com/r/activiti/activiti-cloud-notifications-graphql/)

## Building & Running this Service
You can build this service from source using Git & Maven or you can just run our Docker Image. 

### Spring Boot: 
> git clone https://github.com/Activiti/activiti-cloud-notifications-graphql.git
> cd activiti-cloud-notifications-graphql/
> mvn clean install spring-boot:run

### Docker: 
> docker run -p 8182:8182 -d --name activiti-cloud-notifications-graphql activiti/activiti-cloud-notifications-graphql:latest

## Environemnt Variables
```
server.port=${ACT_NOTIFICATIONS_GRAPHQL_PORT:8080}
spring.application.name=${ACT_NOTIFICATIONS_GRAPHQL_APP_NAME:notifications-graphql}
spring.jackson.serialization.fail-on-unwrapped-type-identifiers=${ACT_SPRING_JACKSON_FAIL_ON_UNWRAPPED_IDS:false}
keycloak.auth-server-url=${ACT_KEYCLOAK_URL:http://activiti-cloud-sso-idm:8180/auth}
keycloak.realm=${ACT_KEYCLOAK_REALM:springboot}
keycloak.resource=${ACT_KEYCLOAK_RESOURCE:activiti}
keycloak.public-client=${ACT_KEYCLOAK_CLIENT:true}
keycloak.ssl-required=${ACT_KEYCLOAK_SSL_REQUIRED:none}
keycloak.security-constraints[0].authRoles[0]=${ACT_KEYCLOAK_ROLES:user}
keycloak.security-constraints[0].securityCollections[0].patterns[0]=${ACT_KEYCLOAK_PATTERNS:/*}
keycloak.principal-attribute=${ACT_KEYCLOAK_PRINCIPAL_ATTRIBUTE:preferred-username}
activiti.keycloak.admin-client-app=${ACT_KEYCLOAK_CLIENT_APP:admin-cli}
activiti.keycloak.client-user=${ACT_KEYCLOAK_CLIENT_USER:client}
activiti.keycloak.client-password=${ACT_KEYCLOAK_CLIENT_PASSWORD:client}
spring.rabbitmq.host=${ACT_RABBITMQ_HOST:rabbitmq}
eureka.client.serviceUrl.defaultZone=${ACT_EUREKA_URL:http://activiti-cloud-registry:8761/eureka/}
eureka.instance.hostname=${ACT_NOTIFICATIONS_GRAPHQL_HOST:activiti-cloud-notifications-graphql}
eureka.client.enabled=${ACT_NOTIFICATIONS_GRAPHQL_EUREKA_CLIENT_ENABLED:true}
```
