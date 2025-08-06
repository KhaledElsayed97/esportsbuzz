# EsportsBuzz 



A modern Android application built with Jetpack Compose for esports enthusiasts to stay updated with matches, news, and follow their favorite teams and players.

## 🎮 Features

### Core Features
- **Live Matches**: Real-time match tracking with live scores and updates
- **Match Details**: Comprehensive match information including brackets and standings
- **News Feed**: Latest esports news and updates
- **Following System**: Follow your favorite teams and players
- **Onboarding**: Interactive onboarding experience with Lottie animations

### Technical Features
- **Modern UI**: Built with Jetpack Compose and Material 3
- **Navigation**: Bottom navigation with smooth animations
- **Live Filter**: Filter matches to show only live games
- **Edge-to-Edge Design**: Immersive full-screen experience
- **Dark/Light Theme Support**: Adaptive theming system

## 🏗️ Architecture

This project follows a modular architecture with feature-based modules:

```
esportsbuzz/
├── app/                    # Main application module
├── core/                   # Shared UI components and themes
├── data/                   # Data layer (repository, API, database)
├── domain/                 # Business logic and use cases
└── features/              # Feature modules
    ├── matches/           # Match-related features
    ├── news/              # News feed feature
    ├── following/         # Following system
    └── onboarding/        # Onboarding experience
```

### Module Structure
- **app**: Main application entry point and navigation
- **core**: Shared UI components, themes, and design system
- **features**: Independent feature modules with their own UI and business logic
- **data**: Data sources and repositories
- **domain**: Business logic and use cases

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Clean Architecture
- **Navigation**: Jetpack Navigation Compose
- **Animations**: Lottie for onboarding animations
- **Build System**: Gradle with Kotlin DSL
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 33 (Android 13)

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- JDK 8 or higher
- Android SDK API 24+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/esportsbuzz.git
   cd esportsbuzz
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select it

3. **Sync and Build**
   - Wait for Gradle sync to complete
   - Build the project (Ctrl+F9 or Cmd+F9)

4. **Run the App**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press Shift+F10

## 📱 App Structure

### Main Screens
1. **Matches Screen**: View live and upcoming matches
   - Live match filtering
   - Match details and brackets
   - Standings and tournament information

2. **News Screen**: Latest esports news and updates
   - News feed with articles
   - Category filtering

3. **Following Screen**: Manage followed teams and players
   - Team following system
   - Player following system
   - Personalized content

4. **Onboarding**: Welcome experience
   - Interactive Lottie animations
   - Feature introduction
   - Smooth page transitions

### Navigation
The app uses bottom navigation with three main destinations:
- **Matches** (Home): Live matches and tournaments
- **News**: Latest esports news
- **Following**: Followed teams and players

## 🎨 Design System

### Colors
- Custom gradient backgrounds for different screens
- Material 3 color system
- Adaptive dark/light theme support

### Typography
- Custom font families (Poppins, Museo Moderno)
- Consistent text styles across the app

### Components
- Custom navigation bar
- Top app bar with live filter
- Card-based layouts
- Animated transitions

## 🔧 Development

### Project Structure
```
src/main/java/com/kholiodev/esportsbuzz/
├── MainActivity.kt              # App entry point
├── navigation/                  # Navigation setup
│   ├── NavHost.kt
│   └── TopLevelDestination.kt
└── ui/
    ├── App.kt                  # Main app composable
    └── AppState.kt             # App state management
```

### Key Dependencies
- `androidx.compose.ui`: UI components
- `androidx.compose.material3`: Material 3 design
- `androidx.navigation.compose`: Navigation
- `com.airbnb.lottie`: Lottie animations
- `androidx.lifecycle.runtimeCompose`: Lifecycle integration

## 📊 Build Configuration

### Gradle Configuration
- **Compile SDK**: 34
- **Min SDK**: 24
- **Target SDK**: 33
- **Kotlin Version**: Latest stable
- **Compose Compiler**: 1.4.3

### Build Variants
- **Debug**: Development build with debugging enabled
- **Release**: Production build with optimizations

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Jetpack Compose team for the amazing UI framework
- Material Design team for the design system
- Lottie team for the animation library
- Android community for inspiration and support

## 📞 Support

If you have any questions or need support, please:
- Open an issue on GitHub
- Contact the development team
- Check the documentation

---

**Made with ❤️ for the esports community**
