
# Aplicacion Web App

su finalidad es mostrar la implementacion de la tecnologia JSF y permitir a cualquier persona crear un proyecto en esta tecnología

 
## Detalles Relevantes de la Tecnologia JSF

### Que es ?
La tecnología JavaServer Faces es un framework de interfaz de componentes de usuarios del lado del servidor para las aplicaciones web basadas en la tecnología Java
Los principales componentes de la tecnología JSF son:

- Una API para:Representar componentes de Interfaz de Usuario (UI) y gestionar su estado.
- Manejar eventos, validar en el servidor y conversión de datos.
- Definir la navegación de páginas.
- Soporte de internacionalización y accesibilidad.
- Dos librerías de etiquetas JSP personalizadas para expresar componentes en una página JSP y enlazar los componentes a objetos del servidor.

### Ciclo de Vida  
Cuando se carga la aplicación web en el servidor se inicializa el framework JSF. Se lee el fichero de configuración faces-config.xml y se crean los beans gestionados definidos con el ámbito application , realizando las sentencias de incialización necesarias. Después el motor de JSF está listo para recibir peticiones y para lanzar el ciclo de vida de JSF con cada una.
![App Screenshot](https://github.com/DeveUp/technology-jsf/blob/main/img/ciclo-de-vida.png?raw=true)

### Ambito Beans
- **Petición**: Se define con el valor request en el faces-config.xml o con la anotación @RequestScoped en la clase. El bean se asocia a una petición HTTP. Cada nueva petición crea un nuevo bean y lo asocia con la página. Se usa para el paso de mensajes que no sea necesario propagar a lo largo de la aplicación.

- **Sesión**: Se define con el valor session en el faces-config.xml o con la anotación @SessionScoped en la clase. El bean se asocia a una sesión definida con el API de Servlets. Se usa para conservar elementos que queremos mantener a lo largo de la aplicación como, por ejemplo: un usuario logueado.
- **Vista**: Se define con el valor view en el faces-config.xml o la anotación @ViewScoped en la clase. Un bean en este ámbito persistirá mientras no naveguemos a otra vista. Se suele usar en páginas Ajax.
## Instrucciones de la Aplicacion

### Dependencias

La especificación Java Server Faces se compone de:

- API Javadocs 
- La especificación estándar de HTML RenderKit
- El documento de especificaciones de Java Server Faces
```html
<dependency>
	<groupId>com.sun.faces</groupId>
	<artifactId>jsf-api</artifactId>
	<version>2.2.19</version>
</dependency>
```

```html
<dependency>
	<groupId>com.sun.faces</groupId>
	<artifactId>jsf-impl</artifactId>
	<version>2.2.19</version>
</dependency>
```
Es un paquete de extensiones Java estándar. Estas incluyen extensiones como javax.servlet, que se ocupa de la ejecución de servlets, y javax.jcr, que se ocupa de la biblioteca de contenido de Java.

```html
<dependency>
	<groupId>javax</groupId>
	<artifactId>javaee-web-api</artifactId>
	<version>7.0</version>
	<scope>provided</scope>
</dependency>
```
### Como Crear un Bean?
para Crear un Bean se crea una clase en java se le indica que es un Bean 

```java
@ManagedBean(name = "user")
```
Despues se le indica el tipo del ambito del Bean En este caso sera de tipo Session
```java
@SessionScoped
```
![App Screenshot](https://github.com/DeveUp/technology-jsf/blob/main/img/BeanSession.jpeg?raw=true)

### Comunicacion entre la Vista y los Bean
Como se puede observar la forma de acceder es mediante acceso a clases, no hay necesidad de recibir por parametros los atributos que lleguen desde la vista.

![App Screenshot](https://github.com/DeveUp/technology-jsf/blob/main/img/Bean.jpeg?raw=true)
