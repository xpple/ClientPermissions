# ClientPermissions
A permissions mod for clients connecting to your server.

## Goal
The goal of this mod is to prevent inappropriate use of certain mods. Mod developers are often powerless to prevent 
their mods from being used on servers which do not allow them. For instance, many utility mod developers wouldn't want
their mods to be used outside of anarchy servers, but they have no way of preventing it. By including this mod, the
server can do more to prevent unfair gameplay.

### Approach
When the client connects to a server, the client will send a list of the mods they are using to the server. If the 
server also has this mod installed, the server will

1. check if the client is using any illegal mods.
2. send back a list of features that are disallowed.

If the client is using any illegal mods, the server can either kick or warn the player. If the server decides to kick,
the player will be presented with a disconnection screen containing a list of mods the player should disable in order
to join the server. If the server decides to warn the player, the player will merely be warned. This is with the
intention that the client will use the list of disallowed features to locally disable them. If the client decides to
ignore the warning, the player may risk other punishment. This way, no players will never be punished without
intentionally breaking any rules.

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
