# ClientPermissions
A permissions mod for clients connecting to your server.

## Goal
todo

## Installation
Replace `${version}` with the artifact version.

You may choose between my own maven repository and GitHub's package repository.
### My own
```gradle
repositories {
    maven {
        url 'https://maven.xpple.dev/maven2'
    }
}
```
### GitHub packages
```gradle
repositories {
    maven {
        url 'https://maven.pkg.github.com/xpple/ClientPermissions'
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
    }
}
```
Import it:
```
dependencies {
    modImplementation 'dev.xpple:clientpermissions:${version}'
}
```
