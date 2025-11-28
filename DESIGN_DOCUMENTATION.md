# Cavendish Mental Health Club - App Design Documentation

## Question 1: Registration Screen Design (10 Marks)

### a) Layout Choice: ConstraintLayout

**Choice:** I chose **ConstraintLayout** for the Registration Screen.

**Why ConstraintLayout:**
- **Performance:** ConstraintLayout creates a flat view hierarchy, which means better performance and faster rendering compared to nested LinearLayouts
- **Flexibility:** It allows for complex layouts with relative positioning, making it easier to create responsive designs that work across different screen sizes
- **Modern Standard:** ConstraintLayout is the recommended layout by Google for Android development
- **Easier Responsive Design:** With constraints, views automatically adjust to different screen sizes without needing multiple layout files
- **Better for Complex UIs:** The registration form has multiple input fields, a radio group, and a button that need to be properly aligned - ConstraintLayout handles this elegantly

**Alternative Considered:** LinearLayout could work but would require nesting multiple LinearLayouts, creating a deeper view hierarchy and potentially impacting performance.

### b) Input Fields and Kotlin Variables

**Input Fields Included:**
1. **Name Input Field** (`nameEditText: TextInputEditText`)
   - Type: Text input for person's name
   - Stored in: `name: String` variable

2. **Age Input Field** (`ageEditText: TextInputEditText`)
   - Type: Numeric input for age
   - Stored in: `age: Int` variable (converted from String)

3. **Wellness Program Selection** (`programRadioGroup: MaterialRadioGroup`)
   - Type: Radio button group with three options:
     - Stress Management
     - Counseling
     - Meditation
   - Stored in: `selectedProgram: String` variable

**Kotlin Variables:**
```kotlin
// Variables to capture user input
private lateinit var nameEditText: TextInputEditText
private lateinit var ageEditText: TextInputEditText
private lateinit var programRadioGroup: MaterialRadioGroup

// Variables to store the captured data
val name: String
val age: Int
val selectedProgram: String
```

### c) Capturing Input When Register Button is Tapped

**Process:**
1. **Button Click Listener:** The Register button has an `OnClickListener` attached using `setOnClickListener`
2. **Input Capture:** When tapped, the listener executes code that:
   - Reads text from `nameEditText` using `text?.toString()?.trim()`
   - Reads text from `ageEditText` and converts it to an integer
   - Gets the selected radio button ID from `programRadioGroup.checkedRadioButtonId`
   - Maps the radio button ID to the corresponding program name
3. **Validation:** Before proceeding, input is validated:
   - Name must not be empty
   - Age must be a valid positive number
   - A program must be selected
4. **Data Object Creation:** If validation passes, a `Member` object is created with the captured data
5. **Navigation:** An Intent is created to navigate to the Confirmation Screen with the Member data

**Code Flow:**
```kotlin
registerButton.setOnClickListener {
    // 1. Capture input
    val name = nameEditText.text?.toString()?.trim() ?: ""
    val ageText = ageEditText.text?.toString()?.trim() ?: ""
    val selectedProgramId = programRadioGroup.checkedRadioButtonId
    
    // 2. Validate
    if (validateInput(name, ageText, selectedProgramId)) {
        // 3. Create Member object
        val member = Member(name, age.toInt(), selectedProgram)
        
        // 4. Navigate with Intent
        navigateToConfirmation(member)
    }
}
```

---

## Question 2: Confirmation Screen Design (10 Marks)

### a) Creating a Kotlin Class to Hold User Data

**Member Data Class:**
```kotlin
@Parcelize
data class Member(
    val name: String,
    val age: Int,
    val preferredProgram: String
) : Parcelable
```

**Key Features:**
- **Data Class:** Uses Kotlin's `data class` keyword for automatic generation of `equals()`, `hashCode()`, `toString()`, and `copy()` methods
- **Parcelable:** Implements `Parcelable` interface with `@Parcelize` annotation, allowing the object to be passed through Intents
- **Properties:** Three immutable properties (`val`) to store:
  - `name`: String for the member's name
  - `age`: Int for the member's age
  - `preferredProgram`: String for the selected wellness program

**Why Parcelable:**
- Required for passing complex objects through Android Intents
- More efficient than Serializable
- Kotlin's `@Parcelize` annotation automatically generates the Parcelable implementation code

### b) Sending Data Using Intent

**Process:**
1. **Create Intent:** A new Intent is created specifying the source activity (`MainActivity`) and destination activity (`ConfirmationActivity`)
2. **Add Data:** The `Member` object is added to the Intent as an extra using `putExtra()` with a key "MEMBER_DATA"
3. **Start Activity:** `startActivity(intent)` is called to navigate to the Confirmation Screen

**Code:**
```kotlin
private fun navigateToConfirmation(member: Member) {
    // Create Intent
    val intent = Intent(this, ConfirmationActivity::class.java)
    
    // Add Member object as Parcelable extra
    intent.putExtra("MEMBER_DATA", member)
    
    // Start Confirmation Activity
    startActivity(intent)
}
```

**Key Points:**
- The Intent acts as a message carrier between activities
- `putExtra()` stores the Member object in the Intent's bundle
- Since Member implements Parcelable, Android can serialize and deserialize it automatically

### c) Receiving and Displaying Data on Confirmation Screen

**Receiving Data:**
1. **Retrieve from Intent:** The ConfirmationActivity retrieves the Member object from the Intent using `getParcelableExtra()`
2. **Type Safety:** Uses Kotlin's type parameter to ensure correct type retrieval
3. **Null Check:** Validates that the data was received successfully

**Code:**
```kotlin
private fun receiveAndDisplayData() {
    // Retrieve Member object from Intent
    val member = intent.getParcelableExtra<Member>("MEMBER_DATA")
    
    if (member != null) {
        // Display data in TextViews
        nameTextView.text = member.name
        ageTextView.text = member.age.toString()
        programTextView.text = member.preferredProgram
    }
}
```

**Display Layout:**
- **Clean Card Design:** Information is displayed in a Material CardView with rounded corners and elevation
- **Organized Layout:** Uses a LinearLayout with horizontal rows for each piece of information
- **Visual Hierarchy:**
  - Labels (Name, Age, Program) in secondary text color
  - Values in primary text color with bold styling
  - Dividers between each information row for clarity
- **Typography:** Different text sizes and styles to create visual distinction
- **Color Coding:** Program name displayed in primary blue color to highlight the selection

**Layout Structure:**
```
CardView
  └── LinearLayout (Vertical)
      ├── Name Row (Label + Value)
      ├── Divider
      ├── Age Row (Label + Value)
      ├── Divider
      └── Program Row (Label + Value)
```

This design ensures the confirmation screen is clean, readable, and provides a professional appearance that builds trust with users.

---

## Summary

The app successfully implements:
- ✅ Registration Screen with ConstraintLayout for optimal performance
- ✅ Input validation before navigation
- ✅ Member data class with Parcelable support
- ✅ Intent-based navigation with data passing
- ✅ Clean, attractive confirmation screen
- ✅ Modern Material Design components
- ✅ Professional UI/UX with proper color scheme and typography






