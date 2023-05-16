# Android PDoS via Race Condition in ActivityManager's CoreSettingsObserver

This repository contains an exploit demonstrating a Permanent Denial of Service (PDoS) condition on Android, specifically within the CoreSettingsObserver of the ActivityManager. The vulnerability was initially reported to Google in 2023.

## Vulnerability Description

This vulnerability arises from a race condition that occurs when two specific configurations (multi_press_timeout and long_press_timeout) in the system's secure settings are changed in sequence. A malicious application with the WRITE_SECURE_SETTINGS permission, or an ADB session with the same permission, can trigger this condition. 

The PDoS manifests as a device bootloop from which recovery is not possible, even with ADB. The only solution is to format the device, leading to permanent data loss.

## Reproduction Steps

The exploit involves making two configuration changes in the system secure table (multi_press_timeout and long_press_timeout). The first two changes are based on valid values, which cause the system to schedule a write operation 200ms later. The exploit then waits 200ms and updates the two entries again with malicious non-integer values just before the write operation is executed.

As soon as the malicious values are injected, ActivityManager crashes and the phone enters a permanent bootlop cycle.

## Bug Lifecycle

- The bug was reported to Google in 2023.
- Initially, the bug was marked as moderate but later marked as wontfix.
