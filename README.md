# 📱 StudyFlow — Kotlin Mobile App

StudyFlow is an educational Android application developed as part of the **Mobile Programming** course at university. The goal of the app is to help students organize and track their study habits efficiently using modern design and functionality.

## 🚀 Technologies
- Kotlin
- Jetpack Compose
- Android Studio
- MVVM pattern (basic level)

## 🎯 Features

### :white_check_mark: Screens:
- **Register/Login screen** – users can create an account and log in.
- **Dashboard screen** – displays key statistics:
  - Tasks Completed
  - Current Session
  - Average Study Time
  - Streak (consecutive study days)
  - Upcoming Tasks – list of upcoming tasks with priority indicators.
  - Upcoming Exams – exams with remaining days shown on the card.
  - Study Activity – monthly grid showing study intensity with varying shades of green.
- **Tasks** – list of upcoming tasks with priority indicators.
- **Subjects** – list of subjects and how many tasks were completed for each one.
- **Timers** – three different ways to keep track of your studying:
    - Pomodoro Timer – timer with 25 minutes of studying and 5 minutes break in between each cycle.
    - Timer – standard timer to set how long you want to study.
    - Stopwatch – stopwatch to see how long you studied.
- **Schedule** – list of all upcoming exams/tasks to give you a better timeline.

### 📊 Visual Elements:
- Modern flat UI design
- Dark theme with vibrant accent colors
- Dynamic progress display using `Box` components (progress bars)

## 🔧 Key Components
- `LazyColumn`, `LazyVerticalGrid` for displaying data
- `TextField`, `OutlinedTextField`, `Buttons`, `Icons` for UI
- `AnnotatedString` and `SpanStyle` for rich text styling
- Navigation between screens using `NavHost` and `NavController`

## 📁 Project Structure
```
├── MainActivity.kt
├── navigation/
│   └── AppNavigation.kt
├── screens/
│   ├── LoginScreen.kt
│   ├── RegisterScreen.kt
│   └── Dashboard.kt
├── ui/theme/
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
└── res/
    └── drawable/, font/, layout/
```

## 🏁 How to Run
1. Clone the repository: `git clone <repo-url>`
2. Open in Android Studio
3. Build & Run on an emulator or physical device

## 👥 Authors
- Haris Susic
- Sanin Zajmovic
- Timur Puzo
- Malek Nafaa

## 🎓 University
- Course: Introduction To Mobile Programming
- University: International Burch University

---

If you have suggestions for improvement, feel free to fork the project and create a Pull Request.

> "Organise Your Future with StudyFlow 💡"
