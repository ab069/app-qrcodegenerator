# Android App Build Rules

Rules for Claude Code when building apps from this template. Follow all of these — no exceptions.

---

## Architecture

### State management
- Every screen with state needs a `ViewModel` + `StateFlow<UiState>`
- `UiState` is a `data class` — computed values go as `val` properties with `get()`, not stored
- The composable only calls `viewModel.uiState.collectAsStateWithLifecycle()` and renders
- Never put business logic, calculations, or validation inside a `@Composable` function

```kotlin
// CORRECT
data class MyUiState(val input: String = "") {
    val isValid: Boolean get() = input.isNotBlank()
}

// WRONG — logic inside composable
@Composable fun MyScreen() {
    val isValid = input.isNotBlank() // ❌
}
```

### Input validation
- All input filtering and validation lives in the ViewModel
- Use regex in the ViewModel's `onXChanged` functions to block invalid characters before updating state
- Never show an error the user hasn't triggered — validate on input, not on mount

### Navigation
- Use Navigation Compose for multi-screen apps
- Single-screen apps: no NavHost needed, call the screen composable directly from MainActivity
- Pass `NavController` down only one level — never deeper

### Data persistence
- Local data → Room database (enable in build.gradle.kts when needed)
- Simple preferences → `DataStore<Preferences>` (not SharedPreferences)
- No data that needs to persist → no database, keep it pure in-memory

---

## Code Quality

### Kotlin
- `data class` for all UI state
- `sealed class` or `enum class` for navigation routes and fixed option sets
- Extension functions for reusable formatting (e.g., `Double.toCurrencyString()`)
- No `!!` (non-null assertion) — use `?.`, `?:`, or `requireNotNull` with a message
- Prefer `when` over `if/else if` chains for 3+ branches

### Compose
- Extract repeated UI into `private` composable functions in the same file
- Extract shared UI into `ui/components/` with its own file
- Never put a `remember` inside a loop or conditional
- Use `key()` when composables inside `LazyColumn` have identity
- Always pass `modifier: Modifier = Modifier` as the first parameter to reusable composables

### Comments
- Write zero comments unless the WHY is genuinely non-obvious
- No `// TODO` left in shipped code
- No commented-out code blocks

---

## UI / UX Rules

### Layout
- Every content column must be wrapped in `verticalScroll(rememberScrollState())` — never assume the screen is tall enough
- Use `Arrangement.spacedBy(dp)` between items, not manual `Spacer` everywhere
- Content padding: `horizontal = 16.dp, vertical = 12.dp` (consistent across all apps)
- Use `Scaffold` with `TopAppBar` on every screen

### Touch targets
- All interactive elements minimum **48dp** in both dimensions
- Use `FilledIconButton` / `IconButton` for icon-only actions — they are 48dp by default
- Prefer `FilterChip` over custom toggle rows for option selection

### Material 3
- Use only Material 3 components — no Material 2
- Colors come from `MaterialTheme.colorScheme.*` — never hardcoded hex in composables
- Use `MaterialTheme.typography.*` for all text styles
- `primaryContainer` / `onPrimaryContainer` for highlighted result surfaces
- `surfaceVariant` / `onSurfaceVariant` for secondary surfaces

### Typography hierarchy
- Screen title: `TopAppBar` title
- Section labels: `labelLarge` + `onSurfaceVariant` color
- Primary result: `headlineSmall` bold
- Secondary result: `titleLarge`
- Body / input labels: `bodyLarge`

### Feedback
- Results appear immediately as the user types — no "Calculate" button unless the operation is expensive
- Disabled buttons when action is not available (e.g., decrement at minimum)
- Empty/zero state: hide result cards until there is valid input (`isValid` on UiState)

---

## Build Configuration

### Package naming
- Pattern: `com.ab069.[appslug]`
- App slug: lowercase, no hyphens (e.g., `tipcalculator`, `habittracker`)
- Must match in: `namespace`, `applicationId`, all Kotlin package declarations, AndroidManifest

### SDK targets
- `compileSdk = 35`
- `targetSdk = 35`
- `minSdk = 26` (Android 8.0 — covers 95%+ of active devices)

### Release build
- `isMinifyEnabled = true`
- `isShrinkResources = true`
- Always include `proguard-android-optimize.txt`

### Dependencies
- Add new dependencies to `gradle/libs.versions.toml` first, then reference via `libs.*` alias
- Never hardcode a version string in `app/build.gradle.kts`
- Remove unused dependencies before pushing

---

## Play Store Requirements

Every app must be ready to ship. Before pushing, confirm:

- [ ] `app_name` in `strings.xml` is the real app name (not "App Template")
- [ ] `applicationId` is `com.ab069.[slug]` (not `com.yourapp.template`)
- [ ] Theme name in `themes.xml` and `AndroidManifest.xml` match
- [ ] No `com.yourapp.template` anywhere in the project
- [ ] `README.md` describes the actual app — features, tech stack, build instructions
- [ ] No unnecessary permissions in `AndroidManifest.xml`
- [ ] App icon placeholder noted (mipmap folders need real icons before Play Store upload)

---

## File Naming Conventions

| What | Convention | Example |
|------|-----------|---------|
| Screen composable file | `[Name]Screen.kt` | `HomeScreen.kt` |
| ViewModel | `[Name]ViewModel.kt` | `TipViewModel.kt` |
| UiState | `[Name]UiState` (inside ViewModel file) | `TipUiState` |
| Reusable component | descriptive noun | `ResultCard.kt`, `ConverterCard.kt` |
| Data/DB file | `AppDatabase.kt`, `[Entity].kt`, `[Entity]Dao.kt` | — |

---

## What NOT to do

- No `LaunchedEffect` for things that should be ViewModel logic
- No `rememberCoroutineScope` in composables — use `viewModelScope` in ViewModel
- No hardcoded strings in composables — use `stringResource(R.string.x)` or a local `val`
- No `Thread.sleep` or blocking calls on main thread
- No internet permission unless the app genuinely needs network
- No `READ_EXTERNAL_STORAGE` / `WRITE_EXTERNAL_STORAGE` unless unavoidable
- No ads libraries (AdMob etc.) — all apps are ad-free
- No analytics/tracking SDKs
