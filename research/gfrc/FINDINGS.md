# GFRC Super Resolution — Complete Findings

## Device: RedMagic 10 Pro (NX789J) · V11.0.05MR1 · Snapdragon 8 Elite (SM8750)

## The Bypass — ONE PROPERTY

```
vendor.gpp.allgame.enable=1
```

This single system property makes `getGfrcCapByPkg()` return **22** (max upscale + frame interpolation) for **ALL games**, completely bypassing the whitelist file.

## Architecture

```
GameAssist PluginUtils.e(pkg)
  → reflection → MindSyncManager$Trigger.getGfrcCapByPkg(pkg)  [framework.jar]
    → IPC (Binder) → IMindSyncManager.getGfrcCapByPkg(pkg)
      → PerformanceController (class E).getGfrcCapByPkg(pkg)   [cubeserver.jar]
        → GfrcModeCtrl (class N).j(pkg)                        [cubeserver.jar]
          → CHECK: vendor.gpp.allgame.enable == 1 ? return 22
          → ELSE:  HashMap lookup from /system/etc/gpp_app_list
```

## Whitelist File

- **Path**: `/system/etc/gpp_app_list`
- **Format**: `package_name,upscale(0/1/2),interp(0/1)` — one per line
- **Comments**: Lines starting with `#` are ignored
- **Encoding**: cap = `(upscale × 10) + interp`
- **240 entries** in stock firmware (mostly Chinese market games)

## Cap Value Encoding

| upscale | interp | cap | Meaning |
|---------|--------|-----|---------|
| 0 | 0 | 0 | Not supported (not in list) |
| 0 | 1 | 1 | Frame interpolation only |
| 1 | 1 | 11 | Standard super res + frame interp |
| 2 | 1 | 21 | HIGH super res + frame interp |
| 2 | 2 | 22 | allgame.enable bypass (max everything) |

## System Properties

| Property | Purpose |
|----------|---------|
| `vendor.gpp.allgame.enable` | **THE BYPASS** — 1 = all games get cap=22 |
| `vendor.gpp.frc.enable` | Master GFRC enable (0x22) / disable (0x21) |
| `vendor.gpp.dynamic.settings.enable` | Dynamic settings, set to "1" on init |
| `vendor.gpp.gfrc.interp.rate` | Frame interpolation rate value |
| `vendor.gpp.gfrc.upscale.ratio` | Super resolution upscale ratio |

## Feature Gates (must be true)

| Feature Flag | Property |
|-------------|----------|
| `ZTE_FEATURE_GFRC` | `ro.vendor.feature.zte_feature_gfrc` |
| `ZTE_FEATURE_ZPERF_CUBE` | `ro.vendor.feature.zte_feature_zperf_cube` |
| (alternative) | `persist.sys.debug_cubeserver` (debug bypass for ZPERF_CUBE) |

## Settings.Global Keys

| Key | Purpose |
|-----|---------|
| `game_gfrc_mode` | Per-game user mode storage: `pkg+mode,pkg+mode,...` |

## Excluded Packages (always skipped)

- `com.android.permissioncontroller`
- `cn.nubia.nbgame`
- `cn.nubia.gamehighlights`
- `cn.nubia.keymapcenter`

## Source Files

| File | Location | Purpose |
|------|----------|---------|
| `MindSyncManager.java` | framework.jar | Public API, Trigger inner class |
| `E.java` (PerformanceController) | cubeserver.jar | Binder receiver |
| `N.java` (GfrcModeCtrl) | cubeserver.jar | Whitelist loader, cap lookup |
| `M.java` (GfrcModeSettings) | cubeserver.jar | Per-game settings data |
| `L.java` (ContentObserver) | cubeserver.jar | Watches game_gfrc_mode changes |
| `gpp_app_list` | /system/etc/ | Plain text whitelist |

## Module Implementation Options

### Option 1: Nuclear (all games, one property)
```bash
# In system.prop or via resetprop
vendor.gpp.allgame.enable=1
```

### Option 2: Selective (overlay file with custom games)
```bash
# Magisk module overlays /system/etc/gpp_app_list
# Add custom games to the file with desired cap values
```

### Option 3: Both (default all + custom file for WebUI)
```bash
# Default: vendor.gpp.allgame.enable=1 (everything works)
# KSU WebUI: toggle per-game, switch between nuclear/selective
```
