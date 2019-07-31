# FluffWorldMinecraft
Custom Spigot 1.14 plugin

## Building
- `cd FluffWorldMinecraft/`
- Install local jar dependencies in `lib/`: 

    `$ make`
- Install maven dependencies:

    `$ mvn install`
- Create jar file (dependencies are [shaded](http://maven.apache.org/plugins/maven-shade-plugin/index.html)):

    `$ mvn package`
- see `target/` directory for generated jar file

## Dependencies:
- JDK 8 / OpenJDK 8
- Maven
- make
- SpigotMC 1.14.4-R0.1-SNAPSHOT API

