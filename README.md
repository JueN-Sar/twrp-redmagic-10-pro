
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
- ❌ WLan not working
- ❌ Battery % (indicator) showing always 100%


<img width="152" height="336" alt="TWRP-decrypt" src="https://github.com/user-attachments/assets/032e73a7-c73a-434b-86e0-2a9f046f7402" />

<img width="152" height="336" alt="TWRP-home" src="https://github.com/user-attachments/assets/6304d097-72f1-4399-bbbc-7ed4f35bf910" />
<img width="152" height="336" alt="TWRP-files" src="https://github.com/user-attachments/assets/c00a74f9-1664-43f6-9761-3209a581394c" />
<img width="152" height="336" alt="TWRP-wipe" src="https://github.com/user-attachments/assets/fbdd7c2a-535d-49f6-aea2-6023f7a118fc" />

<img width="152" height="336" alt="TWRP-reboot" src="https://github.com/user-attachments/assets/bbd630d5-1119-4654-8286-be2247b31217" />
<img width="152" height="336" alt="TWRP-backup" src="https://github.com/user-attachments/assets/080030c9-36ab-4e0a-a3b5-fa10794a7005" />
<img width="152" height="336" alt="5200" src="https://github.com/user-attachments/assets/e4220d5e-ddf6-4cf6-b94d-c89ad33f74aa" />
<img width="152" height="336" alt="5201" src="https://github.com/user-attachments/assets/2a3a122a-c31a-428f-9f6a-f3832c74f6c0" />



<img width="152" height="336" alt="5203" src="https://github.com/user-attachments/assets/e5de5cb0-e426-4c6c-978e-92fb8c09d3b7" />
<img width="152" height="336" alt="5202" src="https://github.com/user-attachments/assets/b3fc0867-32aa-4e4a-bb5f-f36191f9ffd9" />


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
