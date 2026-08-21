# Game Space Unleashed by MsysteM

Magisk module that unlocks Super Resolution (Superior Pic Quality) and other Game Space features for **all games** on RedMagic devices — without LSPosed.

## Features

| Feature | Description |
|---------|-------------|
| ⚡ **Super Resolution** | Enable high frame rate & picture quality for ALL games, not just Nubia's whitelist |
| 🎮 **Global Game Mode** | Treat any app as a game so Game Space features work everywhere |
| 🛡️ **No Kill** | Prevent Game Space from killing background apps |
| 🔲 **Hide Energy Cube** | Hide the Energy Cube overlay on game launch |
| 📐 **Small Window** | Allow all apps in floating window mode |
| ✍️ **Watermark Length** | Extend watermark text limit to 1000 characters |

## How It Works

Uses **Zygisk** (via Magisk or ReZygisk) and **LSPlant** to hook Game Space (`cn.nubia.gamelauncher`) and Game Assist (`cn.nubia.gameassist`) at runtime. Bypasses capability checks, whitelist gates, and feature flags to unlock all features for every game.

**No LSPosed needed** — no detectable Xposed framework for root-detection-heavy apps.

## Requirements

- RedMagic device with Game Space
- Magisk with Zygisk **or** ReZygisk module
- [KsuWebUI](https://github.com/5ec1cff/KsuWebUIStandalone) for the settings interface
- arm64-v8a architecture

## Installation

1. Download the latest `GameSpaceUnleashed-vX.X.X.zip` from [Releases](../../releases)
2. Flash it in Magisk Manager
3. Reboot your device
4. Open **KsuWebUI** → tap **Game Space Unleashed**
5. Toggle features on/off, then tap **Apply & Restart Games**

## Building

Built automatically via GitHub Actions. To trigger a build:

1. Push to a `claude/nubia-toolkit-*` branch, or
2. Go to Actions → "Build Game Space Unleashed" → Run workflow

The workflow compiles the Zygisk native module (C++ with LSPlant) using Android NDK, compiles Java hook code into DEX, and packages everything into a flashable Magisk module zip.

## Architecture

```
GameSpaceUnleashed.zip
├── module.prop              # Module metadata
├── customize.sh             # Magisk install script
├── service.sh               # Boot service
├── post-fs-data.sh          # Pre-zygote setup
├── uninstall.sh             # Cleanup on removal
├── config.json              # Default feature toggles
├── webroot/
│   └── index.html           # KsuWebUI settings interface
├── zygisk/
│   └── arm64-v8a.so         # Zygisk native module (LSPlant hooks)
└── dex/
    └── classes.dex           # Java hook logic
```

### Hook Flow

1. Zygisk injects the native module into Game Space / Game Assist processes
2. Native module loads `classes.dex` and initializes LSPlant
3. Java hook code uses reflection to find target methods
4. LSPlant replaces method entry points with our replacements
5. Feature checks return "supported" → Super Resolution works everywhere

## Credits

- **MsysteM** — Module concept and development
- [KhanhNguyen9872/NubiaToolkit](https://github.com/KhanhNguyen9872/NubiaToolkit) — Original hook research
- [LSPlant](https://github.com/LSPosed/LSPlant) — ART method hooking engine
- [Zygisk API](https://github.com/topjohnwu/Magisk) — Process injection framework

## License

This project is for educational and personal use on devices you own.
