# Movies App

A simple Android application built with Jetpack Compose that displays a list of movies and provides features like searching, filtering, and sorting.

## Features

- **Movie List**: Displays a list of movies using `LazyColumn`.
- **Search**: Search for movies by title or actor name.
- **Industry Filter**: Filter movies by industry (e.g., Telugu, Tamil).
- **Sorting**: Sort the movie list by:
    - Rating
    - Collection
    - Release Year
- **Navigation**: Click on a movie card to navigate to an actor-specific screen.

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design System**: Material 3
- **Navigation**: Jetpack Compose Navigation
- **State Management**: `remember` and `mutableStateOf`

## Project Structure

- `com.example.movies.model`: Contains the `Movie` data class.
- `com.example.movies.data`: Contains `DummyData` used to populate the app.
- `com.example.movies.ui.theme`: Contains UI components like `MovieCard` and theme configurations.
- `HomeScreen.kt`: The main screen containing the list, search bar, and filters.
- `AppNavigation.kt`: Handles navigation between screens.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle and run the app on an emulator or physical device.
