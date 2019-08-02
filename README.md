[![Build Status](https://travis-ci.org/au5ton/FluffWorldMinecraft.svg?branch=master)](https://travis-ci.org/au5ton/FluffWorldMinecraft)

# FluffWorldMinecraft
Custom Spigot 1.14 plugin

## Building
- `cd FluffWorldMinecraft/`
- Install local jar dependencies in `lib/`: 

    `$ make dep`
- Install maven dependencies:

    `$ mvn install`
- Create jar file:

    `$ mvn package`
- see `target/` directory for generated jar file

## Dependencies:
- JDK 8 / OpenJDK 8
- Maven
- make
- SpigotMC 1.14.4-R0.1-SNAPSHOT API
- `net.austinj.xaerominimap.MinimapAPI` plugin must be loaded on the server too

