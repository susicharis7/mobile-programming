# Read After Pull

## Purpose
This file serves as a communication hub for contributors, containing:
- Important project conventions
- Recent changes documentation
- Quick reference guides
- Team messages

---

## Design Implementation Guide

### Figma to Android Conversion
When importing from Figma use formula: 
`dp = figmaPixels/3`

```kotlin
// Example: 96px in Figma → 32dp in Compose
Text(
    text = "Example",
    modifier = Modifier.size(32.dp)
)
```
Use `dp` for dimensions, `sp` for text

### Color System

Colors are defined in:
`com/example/studyflow/ui/theme/Color.kt`

If you want to add your own color, copy the color from figma and add like this:
```kotlin
// color from figma: 48CB94
// when adding to kotlin you need to add 0xFF at the beginning 
// the FF is the alpha value (opacity)
// use this link to convert "https://gist.github.com/lopspower/03fb1cc0ac9f32ef38f4"
val colorName = Color(0xFF48CB94)
```

Usage examples:
```kotlin
// Single import
import com.example.studyflow.ui.theme.LoginGreen

// Bulk import
import com.example.studyflow.ui.theme.*

// Implementation
Surface(color = BackgroundDark) {
    Text("Hello", color = TextWhite)
}
```

### Typography

Font styles are defined in:
`com/example/studyflow/ui/theme/Type.kt`

Use:
```kotlin
Text(
    text = "Sample",
    fontFamily = interFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp
)
```

---

## Development Conventions

### Code Style
1. Use descriptive variable names
2. Follow Kotlin style guide
3. Use comments

### Commit Practices
1. Write clear commit messages
2. Reference related issues
3. Update this file when introducing significant changes

---

## Recent Changes
```markdown
- [2025-5-9]: Added login screen
- [2025-5-9]: Added register screen and navigation
- [2025-5-10]: Added theme colors and typography
- [2025-5-12]: Added more screens and navigation bar at the bottom
```

---

## Team Messages
```
@team: Need to create other screeens, looks like login and register are completed
@sanin: skontaj kako se mijenja boja na navigation bar i ostale customizatons, isto nesto na vrhu ne bude iste boje kao background pa i to probaj popraviti
@futureFixes: Napraviti bolju logiku za "StudyDayCell" boju in dashboard (currently it thinks that 45 min is 45 hours)
    - also da se popravi da one crne prazne celije popune cijeli grid 7x5
    - boje sto se nalaze na vrhu izmedju Less ..... More nisu iste kao sto su na cards u grid
@sanin/haris: I tried adding stuff to change the notification tray color to be the same as the background color in HomeNavigation.kt but it didn't work, i just left it commented
    - also added a dependency in app/build.gradle.kts for the thing, maybe it wasnt up to date
```
## Add your own things 
 - Add to "Design Implementation Guide" for others to be aware of.
 - Add your "Recent Changes"
 - Add your "Team Messages"
### Can't see the markdown?
1. Go to "help" > "Find Action"
2. search "Choose Boot" and select the first option "Choose Boot JavaRuntime for the IDE...". 
3. In the dialogue box click the "New:" test field to see the available options and select the top one which says "Runtime with JCEF" at the end
4. press "OK" and "Restart Now". 
5. After the restart open the .md file again and you will see a split screen showing the .md code and the compiled version.