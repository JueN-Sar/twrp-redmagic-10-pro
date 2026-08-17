<img width="1216" height="2688" alt="TWRP-home" src="https://github.com/user-attachments/assets/6304d097-72f1-4399-bbbc-7ed4f35bf910" />
<img width="1216" height="2688" alt="TWRP-files" src="https://github.com/user-attachments/assets/c00a74f9-1664-43f6-9761-3209a581394c" />
# TWRP 16.0 for Nubia RedMagic 10 Pro (NX789J)



---

## 📋 Device Specs

| | |
|---|---|
| **Device** | Nubia RedMagic 10 Pro |
| **Codename** | NX789J |
| **SoC** | Snapdragon 8 Elite (sun) |
| **Android** | 16 |
| **TWRP** | 16.0 (3.7.1) |
| **Encryption** | FBE fscrypt policy v2 with ICE wrappedkey |
| **Partition** | A/B with dedicated recovery |

## ⚡ Features

- ✅ Working decryption (FBE fscrypt policy 2)
- ✅ ADB, Fastbootd, USB OTG, MTP
- ✅ Backup / Restore / ZIP flashing
- ❌ Vibration not working

## 📥 Download

Grab the latest build from [**Releases**](../../releases).

## 🔧 Flashing

Already rooted? Flash directly from your phone:

```bash
# Open a terminal app (e.g. Termux) and run:
su
dd if=/sdcard/Download/recovery.img of=/dev/block/bootdevice/by-name/recovery_a
dd if=/sdcard/Download/recovery.img of=/dev/block/bootdevice/by-name/recovery_b
reboot recovery
```

Or via PC with ADB:

```bash
adb push recovery.img /sdcard/recovery.img
adb shell dd if=/sdcard/recovery.img of=/dev/block/bootdevice/by-name/recovery_a
adb shell dd if=/sdcard/recovery.img of=/dev/block/bootdevice/by-name/recovery_b
adb reboot recovery
```

## 🏗️ How It Was Built

I've found [reminon's TWRP 16.0 device tree](https://github.com/reminon/twrp_device_nubia_nx789j)
— a clean, complete device tree for the RedMagic 10 Pro with working
decryption and all the essentials. It had no releases, so i set up a
GitHub Actions pipeline to build it ourselves. Zero patches, zero hacks
— just a straight build from a solid device tree.

## 🙏 Credits

- **[reminon](https://github.com/reminon)** — TWRP 16.0 device tree for RedMagic 10 Pro
- **[plompomg](https://github.com/plompomg)** — OrangeFox device tree (original reference)
- **[TeamWin](https://github.com/TeamWin)** — TWRP Recovery Project
- **[JueN-Sar](https://github.com/JueN-Sar)** — Repo owner, builder also known as MsysteM
- **[Claude](https://claude.ai)** — AI partner




---

*"The best builds are the ones that just work."* 🚀
