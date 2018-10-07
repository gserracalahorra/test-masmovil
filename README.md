# test-masmovil

SISTEMA OPERATIVO UTILIZADO: Ubuntu 18


# Manual de instalación


## CONSTRUIR LOS ARTEFACTOS .jar


/test-masmovil/phones -> mvn clean install

/test-masmovil/customers -> mvn clean install

/test-masmovil/orders -> mvn clean install


## CONSTRUIR LAS IMAGENES DOCKER

/test-masmovil/phones
sudo docker build -t phones-api .

/test-masmovil/customers
sudo docker build -t customers-api .

/test-masmovil/orders
sudo docker build -t orders-api .

## LEVANTAR LOS CONTENEDORES


sudo docker run -d -p 8081:8081 phones-api

sudo docker run -d -p 8082:8082 customers-api

sudo docker run -d --network host orders-api


## PROBAR LOS MICROSERVICIOS:

Comando correcto (HTTP 401):

curl -X POST -H 'Content-Type: application/json;' -i http://localhost:8083/orders-api/v1/order --data '{"customerId": 1,"phoneIds": [1, 3, 4, 6 ]}'

Comando con usuario inexistente (HTTP 422):

curl -X POST -H 'Content-Type: application/json;' -i http://localhost:8083/orders-api/v1/order --data '{"customerId": 9,"phoneIds": [1, 3, 4, 6 ]}'

Comando con usuario correcto pero móviles inexistentes (HTTP 422):

curl -X POST -H 'Content-Type: application/json;' -i http://localhost:8083/orders-api/v1/order --data '{"customerId": 1,"phoneIds": [6, 7, 8, 9 ]}'



# Mejoras para el sistema

### Idealmente, cada microservicio estará en un repositorio git independiente

### Sistema de logs para la aplicación

### Pueden realizarse tests de integración con docker y docker-compose

### Las órdenes, en lugar de mostrarse por consola, persistirlas en una base de datos. Podemos usar una base de datos NoSql como MongoDB porque si la aplicación va a insertar la orden significa que ha pasado las validaciones del usuario y de los productos, por lo que la integridad referencial está garantizada.

### Implementar arquitectura escalable para lo que utilizaremos:

#### Componentes del framework Spring Cloud

#### Sistema de orquestación

#### Tolerancia a fallos (Hystrix)
