# Cavendish Mental Health Club - Android Registration App

A simple and attractive Android application for registering new members to wellness support programs.

## Features

- **Registration Screen**: Clean, modern interface for entering member information
- **Input Validation**: Ensures all required fields are filled correctly
- **Confirmation Screen**: Displays registered member information in an organized layout
- **Material Design**: Beautiful UI following Google's Material Design guidelines

## App Structure

### Screens

1. **Registration Screen (MainActivity)**
   - Name input field
   - Age input field
   - Wellness program selection (Stress Management, Counseling, Meditation)
   - Register button

2. **Confirmation Screen (ConfirmationActivity)**
   - Displays registered member information
   - Back button to return to registration

### Key Components

- **Member.kt**: Data class to hold member information (implements Parcelable)
- **MainActivity.kt**: Registration screen logic
- **ConfirmationActivity.kt**: Confirmation screen logic

## Design Decisions

### Layout Choice: ConstraintLayout
- Better performance with flat view hierarchy
- Flexible and responsive design
- Modern Android development standard

### Data Passing: Intent with Parcelable
- Member class implements Parcelable for efficient data transfer
- Clean separation between activities
- Type-safe data passing

## Building and Running

1. Open the project in Android Studio
2. Sync Gradle files
3. Build the project (Build > Make Project)
4. Run on an emulator or physical device

## Requirements

- Minimum SDK: 24 (Android 7.0)
- Target SDK: 36
- Kotlin version: 2.0.21

## Dependencies

- AndroidX Core KTX
- Material Design Components
- ConstraintLayout
- CardView

## Usage

1. Launch the app
2. Enter your name in the name field
3. Enter your age in the age field
4. Select a wellness program (Stress Management, Counseling, or Meditation)
5. Tap the "Register" button
6. View your registration confirmation on the next screen
7. Tap "Back to Registration" to register another member

## Validation

The app validates:
- Name must not be empty
- Age must be a valid positive number
- A wellness program must be selected

## Design Documentation

See `DESIGN_DOCUMENTATION.md` for detailed answers to the design questions.






