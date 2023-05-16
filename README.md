<div style="text-align: justify">

## PDoS via race condition in `CoreSettingsObserver`
This repository contains an exploit demonstrating a Permanent Denial of Service (PDoS) condition on Android, specifically within the `CoreSettingsObserver` of the `ActivityManager`. The vulnerability was initially reported to Google in February 2023.

### Description
This vulnerability arises from a race condition that occurs when two specific configurations (`multi_press_timeout` and `long_press_timeout`) in the system's secure settings are changed in sequence. A malicious application with the `WRITE_SECURE_SETTINGS` permission, or an ADB session with the same permission, can trigger this condition. The PDoS manifests as a device bootloop from which recovery is not possible, even with ADB. The only solution is to format the device, leading to permanent data loss.

### Reproduction
As previously said, the exploit involves making two configuration changes in the system secure table. The first two changes are based on valid values, which cause the system to schedule a write operation **200ms** later. The exploit then waits **200ms** and updates the two entries again with malicious non-integer values just before the write operation is executed. As soon as the malicious values are injected, `ActivityManager` crashes and the phone enters a permanent bootlop cycle.

### Lifecycle
- Feb 23, 2023 (06:22PM) - Initial report sent to Google.
- Mar 6,  2023 (08:39PM) - Bug marked as `Moderate`.
- Mar 13, 2023 (10:09PM) - Bug marked as `WONTFIX`.
