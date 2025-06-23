# Manual de Instalación de la Aplicación Web

Este documento explica cómo instalar y ejecutar la aplicación web a través de Eclipse y Apache Tomcat.

## Requisitos Previos

- [Eclipse IDE](https://www.eclipse.org/downloads/)
- [Apache Tomcat](https://tomcat.apache.org/)
- Base de datos compatible con SQL (MySQL, PostgreSQL, etc.)
- JDK instalado y configurado en Eclipse

## Pasos de Instalación

### 1. Descomprimir el archivo `.war`

Descomprime el archivo `.war` en una carpeta local de tu elección.

### 2. Importar el proyecto en Eclipse

1. Abre Eclipse.
2. Ve a `File > Import > Existing Projects into Workspace`.
3. Selecciona la carpeta donde descomprimiste el `.war`.
4. Asegúrate de marcar el proyecto y haz clic en `Finish`.

### 3. Ejecutar el código SQL

1. Navega a la carpeta: src/main/resources
2. Abre el archivo `.sql` que contiene la estructura y datos necesarios.
3. Copia y ejecuta su contenido en tu gestor de base de datos.

### 4. Configurar y ejecutar Apache Tomcat

1. En Eclipse, ve a la pestaña `Servers` (abajo). Si no la ves, activa desde `Window > Show View > Servers`.
2. Añade un nuevo servidor Apache Tomcat (`New Server`) y selecciona la versión instalada.
3. Asocia tu proyecto al servidor.
4. Haz clic derecho sobre el servidor y selecciona `Start`.

### 5. Ejecutar la aplicación web

1. Navega a la carpeta: src/main/webapp
2. Haz doble clic en el archivo `index.html` o ábrelo con el navegador desde Eclipse (`Open With > Web Browser`).
3. Realiza el Login con el usuario: admin password: Aa1234567@

Ahora la aplicación debería estar corriendo y lista para su uso.



