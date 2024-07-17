<p align="center">
  <b>This microservice belongs to the <a href="https://github.com/Venicode/user-registration-validation">User Registration Validation</a> project.</b>
</p>

# Initial Configurations

To preserve the data, you need create your database and a RabbitMQ queue. After that, update in <a href="https://github.com/Venicode/ms-user/blob/master/src/main/resources/application.properties"> application.properties </a>:

```
spring.application.name=user
server.port=8081
spring.datasource.url=jdbc:mysql://server/database_name
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.rabbitmq.addresses=amqps://rabbitmq_addresses
broker.queue.email-register.name = default.email-register
```
