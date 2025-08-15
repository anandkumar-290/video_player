I'm glad the code is working for you! Here's a professional and clear README file tailored for your video player app, ready for your Git repository.

🎬 Video Player App
Overview
This is a modern Android video player application built using Jetpack Compose for a beautiful and declarative UI. The app allows users to browse videos on their device, view them by folder, and play them in a dedicated video player screen. It's designed with a clean architecture using Hilt for dependency injection and Kotlin Flows for reactive data handling.

Features
Splash Screen: A sleek splash screen on app launch.

Media File Browsing: Automatically discovers and displays all video files on the device.

Folder View: Organizes videos by their containing folders for easy navigation.

Permissions Handling: Requests necessary media permissions (READ_MEDIA_VIDEO) gracefully.

Video Playback: A dedicated screen to play videos using ExoPlayer.

Dynamic UI: The UI adapts to show a placeholder message if media permissions are not granted.

Coil Integration: Efficiently loads video thumbnails using the Coil image loading library with VideoFrameDecoder.

Navigation: Seamless navigation between screens using the new Compose Navigation with type-safe arguments via Kotlin Serialization.

Technologies Used
Kotlin: The primary programming language.

Jetpack Compose: For building the native Android UI.

Hilt: A dependency injection framework to simplify managing dependencies.

Kotlinx Serialization: For serializing and deserializing data classes, particularly for type-safe navigation arguments.

Jetpack Navigation: For handling in-app navigation between screens.

Coroutines & Flow: For asynchronous operations and reactive data streams.

Coil: An image loading library for Android backed by Kotlin Coroutines.

Accompanist Permissions: For declarative runtime permissions handling in Compose.

ExoPlayer (Media3): A robust, open-source media player for Android.

Getting Started
To run this project locally, clone the repository and open it in Android Studio.

git clone <your-repo-link>
The project uses Gradle for builds. Simply sync the project and run the app on an emulator or a physical device running Android 12 (API 31) or higher.

Project Structure
The project follows a clean architecture pattern with clear separation of concerns.

Data: Contains data sources (e.g., ContentResolver to query video files) and repository implementations.

Domain: Defines the business logic and contracts (e.g., VideoFileRepo interface).

Presentation: Contains the UI layer, including all composable screens, navigation logic, and ViewModels.

HiltModule: Sets up the dependency graph for Hilt.

Contributing
Feel free to open issues or submit pull requests. All contributions are welcome.
