# Prueba Linexperts
## Requerimientos

- Git
- Java 8 o superior
- Maven
- Docker o docker compose
  En caso de no tener Docker, se debe instalar una base de datos Posgres o ejecutar una en la nube y ajustar el archivo application.yml que está dentro de resources para poder conectarse a la aplicación

Clonar el código fuente:
```
git clone https://github.com/thaednevol/AlfaProject.git
```

Nos movemos a la carpeta descargada:
```
cd AlfaProject
```
Ejecutamos maven para descargar las dependencias:
```
mvn clean install
```
Ejecutamos docker para iniciar una instancia de la base de datos:
```
docker compose up db
```
Ejecutar las sentencias SQL:
```
CREATE TABLE public.invoice (
	id uuid NOT NULL,
	id_customer varchar NOT NULL,
	created_date date NULL,
	status varchar NULL,
	cost float8 NULL
);
```
```
CREATE TABLE public."policy" (
	id uuid NOT NULL,
	name varchar NOT NULL,
	description varchar NOT NULL,
	cost float8 NOT NULL
);
```
```
CREATE TABLE public.policy_invoice (
	invoice_id uuid NOT NULL,
	policy_id uuid NOT NULL
);
```
```
INSERT INTO public."policy"
(id, "name", description, "cost")
VALUES('ec750bf2-d247-11ed-afa1-0242ac120002', 'Transport Policy', 'Transport policy', 5000.0);
```
```
INSERT INTO public."policy"
(id, "name", description, "cost")
VALUES('e31a2114-d247-11ed-afa1-0242ac120002'::uuid, 'Warranty Policy', 'Six months warranty', 10000.0);
```
```
INSERT INTO public."policy"
(id, "name", description, "cost")
VALUES('8ed69fa9-d17b-40ee-a8b1-93b6d8888c47'::uuid, 'All Risk Poilicy', '10 years all risk policy', 20000.0);
```
Ejecutamos spring-boot:
```
mvn spring-boot:run
```
La aplicación debería mostrar el API en formato OpenAPI 3.0 con Swagger en la siguiente dirección:
```
http://localhost:8080/swagger-ui.html#/
```
