# WalksyCrystalOptimizer

A client-side Fabric mod that speeds up End Crystal placement and removes
client-side delay when breaking crystals, slimes, and magma cubes.

Originally created by **[Walksy](https://github.com/Walksy)**
([original repository](https://github.com/Walksy/WalksyCrystalOptimizer)),
licensed under MIT. This fork adds support for the current Minecraft version
range — **1.21.4 through 1.21.11, and 26.1 / 26.2** — maintained by **ISekai**.

---

## Features
- Removes the client-side delay when placing End Crystals, allowing much
  faster crystal PvP combos.
- Skips the death/hit animation delay when breaking End Crystals, Slimes,
  and Magma Cubes.
- Ping-aware throttling — the speed-up scales with your connection latency
  instead of firing blindly.

## Supported versions
This is a [Stonecutter](https://stonecutter.kikugie.dev/) multi-version project.
A single codebase is built separately against each supported Minecraft version,
using Mojang's official mappings (Yarn does not yet cover the 26.x line):

| Minecraft | Status |
|---|---|
| 1.21.4 – 1.21.11 | Supported |
| 26.1 | Supported |
| 26.2 | Supported |

Grab the jar matching your Minecraft version from the
[Releases](../../releases) page.

## Installation
1. Install [Fabric Loader](https://fabricmc.net/use/) for your Minecraft version.
2. Download the matching jar from [Releases](../../releases).
3. Drop it into your `.minecraft/mods` folder and launch.

## Building from source
This project uses Stonecutter to manage the multi-version build. From the
project root:
```bash
# Build one specific version
./gradlew :1.21.11:build

# Build every supported version and collect the jars
./gradlew :1.21.4:buildAndCollect :1.21.5:buildAndCollect :1.21.6:buildAndCollect \
  :1.21.7:buildAndCollect :1.21.8:buildAndCollect :1.21.9:buildAndCollect \
  :1.21.10:buildAndCollect :1.21.11:buildAndCollect :26.1:buildAndCollect :26.2:buildAndCollect
```
Collected jars land in `build/libs/<mod version>/`. To switch which version's
code is active in `src/` for editing, run the `Set active project to <version>`
Gradle task in the `stonecutter` task group.

## License
[MIT](LICENSE) — original copyright Walksy. Modifications and the multi-version
port are contributed by ISekai under the same license.
