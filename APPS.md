# App Registry

Master tracker for all apps built. Check this before starting a new app to avoid duplicates and to reuse components.

---

## Built Apps

| # | App Name | Repo | Category | Key Components | Play Store | Date |
|---|----------|------|----------|---------------|------------|------|
| 1 | Unit Converter | [app-unit-converter](https://github.com/ab069/app-unit-converter) | Utility | ConverterCard, Converter.kt, BottomNav tabs | Pending | 2026-05-09 |
| 2 | Tip Calculator | [app-tip-calculator](https://github.com/ab069/app-tip-calculator) | Utility | TipViewModel, TipUiState, ResultCard | Pending | 2026-05-09 |

---

## Reusable Components

Components extracted from previous apps that can be copied into new apps.

| Component | File | Found In | What It Does |
|-----------|------|----------|--------------|
| ConverterCard | `ui/components/ConverterCard.kt` | app-unit-converter | Input + two dropdowns + result card. Reuse for any unit/conversion UI |
| Converter logic | `converter/Converter.kt` | app-unit-converter | Pure Kotlin conversion math with formatting |
| TipViewModel pattern | `TipViewModel.kt` | app-tip-calculator | UiState data class with computed props + StateFlow ViewModel pattern |
| ResultCard | `ui/screens/TipScreen.kt` | app-tip-calculator | Highlighted result row card. Copy for any summary/result display |

---

## App Ideas Queue

Priority-ordered list of apps to build next. Cross off as done.

### Utility
- [x] Tip Calculator with bill split
- [ ] QR Code Generator
- [ ] Countdown Timer to any date
- [ ] Password Generator
- [ ] Flashlight + SOS mode
- [ ] Random Decision Maker

### Health
- [ ] Water Intake Tracker
- [ ] Sleep Logger
- [ ] Mood Journal
- [ ] Meditation Timer
- [ ] Period Tracker

### Productivity
- [ ] Habit Tracker with streaks
- [ ] Pomodoro Timer
- [ ] Note app with categories
- [ ] Bill Reminder
- [ ] Reading List Tracker

### Finance
- [ ] Expense Tracker
- [ ] Savings Goal Tracker
- [ ] Subscription Tracker
- [ ] Budget Planner
- [ ] Debt Payoff Calculator

---

## Rules

### Before building
1. Read this file — check Built Apps for duplicates, check Reusable Components for anything to copy
2. Research Play Store competition (top complaints = our differentiators)
3. Write PRD: user stories, screen list, features scope

### While building
4. Use ViewModel + StateFlow for all state — no business logic in composables
5. No hardcoded strings — use `strings.xml`
6. All touch targets minimum 48dp
7. Use Material 3 components (no custom widgets unless necessary)
8. Scrollable layout — wrap content column in `verticalScroll`
9. Input validation at the ViewModel layer, not the UI layer
10. No ads, no tracking, no unnecessary permissions

### Before pushing
11. Review every file: package names match, no template leftovers (`com.yourapp.template`)
12. Check all imports resolve (no missing dependencies)
13. README updated with actual features
14. Add entry to Built Apps table in this file
15. Add any new reusable components to the Reusable Components table
16. Cross off idea from queue
