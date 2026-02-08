# 🚗 Car Repair Tracker

A full‑stack Android application built in **Java** that helps users track vehicles, maintenance records, and repair history in one place. This project was designed as a portfolio‑ready app to demonstrate real‑world Android development skills, clean architecture, and data persistence.

---

## 📌 Overview

Car Repair Tracker allows users to:

* Add and manage multiple vehicles
* Log repairs and maintenance activities
* View repair history per vehicle
* Maintain organized records instead of scattered notes or receipts

The app focuses on practical usability while showcasing modern Android development practices.

---

## 🛠️ Tech Stack

* **Language:** Java
* **Platform:** Android
* **UI:** XML layouts, RecyclerView, ConstraintLayout
* **Architecture:** Activity‑based with adapters and data repositories
* **Data Storage:** Room (SQLite), Firebase (Cloud)
* **Build Tool:** Gradle
* **Minimum SDK:** Android 8.0+

---

## ✨ Key Features

* **Vehicle Management**

  * Add, edit, and delete vehicles
  * Store details such as make, model, and year

* **Repair Tracking**

  * Log maintenance and repair entries
  * Track cost, date, and description of service

* **Cloud Sync (Firebase)**

  * Store and sync data in the cloud
  * Enables persistence across devices
  * Lays groundwork for authentication and multi-user support

* **Clean UI & UX**

  * RecyclerView with empty-state handling
  * Material-inspired layouts

* **Data Persistence**

  * Local database using Room
  * Cloud-backed storage using Firebase

---

## 📂 Project Structure (High Level)

```
com.example.vehiclerepairtracker
│
├── UI            # Activities & adapters
├── database      # Room database, DAOs, entities
├── model         # Data models
└── utils         # Helpers and constants
```

---

## 🚀 Getting Started

1. Clone the repository

   ```bash
   git clone https://github.com/BruceChapmanJr10/car_repair_tracker.git
   ```

2. Open the project in **Android Studio**

3. Sync Gradle and run the app on an emulator or physical device

---

## 🎯 Purpose

This project was built to:

* Demonstrate entry‑to‑mid level Android development skills
* Showcase Java, Room, RecyclerView, and clean UI practices
* Serve as a portfolio project for software engineering roles

---

## 🔮 Future Enhancements

* Firebase Authentication (user accounts)
* Real-time multi-device sync
* Notifications for scheduled maintenance
* Export repair history to PDF
* Improved analytics (total cost per vehicle)

---

## 👤 Author

**Bruce Chapman**
Software Engineer

---

## 📄 License

This project is for educational and portfolio purposes.

