# CrowUtils
Utility mod for Minecraft, developed by the Crow.

---

### = Overview =

CrowUtils is in very early development right now.
This Repository exists to give me an easy way to access this project from multiple systems,
and provide a backup for me.

### = Installing =

CrowUtils depends on any version of the Fabric Loader equal to or greater than 0.16.12,
along with the mods [Fabric API](https://modrinth.com/mod/fabric-api/), [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin), and
[MCEF](https://modrinth.com/mod/mcef/), and is written specifically for **1.21.4**.

If you have all dependencies and the mod is still not working, update everything to its latest version.

Drag and drop the mod .jar from [Releases](https://github.com/diena1dev/CrowUtils/releases),
ensuring all dependencies are met.

Check the Minecraft "Controls" in Settings for default keybindings.

### = Development Timeline =

#### General Use:

Menu creation with different buttons for the input of URLs in, refreshing of, and tab function of MCEF.

First prototype and texture creation.

A calculator for simple computation of basic math, with history stored in a file.

A file browser to edit config files, play with an "IDE-Like" appearance.

#### Horizons End Specfic Features:

A context menu, brought up with a right-click, that has options to fetch the text of the page,
isolate the coordinate values, copy them to your clipboard, and paste them in chat. On the same menu, ask for confirmation.

IF planet position data can be grabbed with Kotlin- An algorithm that calculates
the best path around gravity wells.

A method to smooth the planet icon position updates, taking their previous and current position,
then smoothly moving them from one position to another.

A reimplementation of Void features, mainly status HUD elements and Astralchroma's Crate Placer,
rewritten for modern versions of Minecraft.