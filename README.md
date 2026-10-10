WalksyCrystalOptimizer

WalksyCrystalOptimizer is a client-side Fabric mod designed to make End Crystal placement faster and reduce the delay when breaking End Crystals, Slimes, and Magma Cubes.

The original mod was created by Walksy and is available in the original repository. It is licensed under the MIT License.

This fork is maintained by ISekai and extends support to newer Minecraft versions, including 1.21.4 through 1.21.11, as well as 26.1 and 26.2.

⸻

Features

* Faster Crystal Placement: Removes the client-side delay when placing End Crystals, making crystal PvP combos faster and smoother.
* Reduced Breaking Delay: Skips unnecessary death and hit animation delays when breaking End Crystals, Slimes, and Magma Cubes.
* Ping-Aware Speed Control: Adjusts the speed-up based on your connection latency instead of applying it without considering your ping.

Supported Versions

This project uses Stonecutter to support multiple Minecraft versions from a single codebase.

Each version is built separately to ensure compatibility with its corresponding Minecraft release. The project uses Mojang’s official mappings because Yarn mappings do not yet cover the 26.x versions.

Minecraft Version	Status
1.21.4 to 1.21.11	Supported
26.1	Supported
26.2	Supported

You can download the JAR file for your Minecraft version from the Releases page.

Installation

Getting started is simple:

1. Install Fabric Loader for your Minecraft version.
2. Download the correct JAR file from the Releases page.
3. Move the downloaded file into your .minecraft/mods folder.
4. Launch Minecraft using your Fabric profile.

Make sure you download the version that matches your Minecraft installation.

Building from Source

The project uses Stonecutter to manage builds for different Minecraft versions.

Open a terminal in the project root directory and run the following commands.

Build for a specific Minecraft version:

./gradlew :1.21.11:build

Build all supported versions and collect the resulting JAR files:

./gradlew :1.21.4:buildAndCollect :1.21.5:buildAndCollect :1.21.6:buildAndCollect \
  :1.21.7:buildAndCollect :1.21.8:buildAndCollect :1.21.9:buildAndCollect \
  :1.21.10:buildAndCollect :1.21.11:buildAndCollect :26.1:buildAndCollect :26.2:buildAndCollect

The collected JAR files will be available in build/libs/<mod version>/.

If you want to work on a specific Minecraft version, use the Set active project to <version> Gradle task under the stonecutter task group. This switches the active version in src/ for development.

License

This project is licensed under the MIT License.

The original mod was created by Walksy. All modifications and the multi-version port are contributed by ISekai under the same license.