package com.example.cavendishmentalhealthclub

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.RadioGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    
    // Kotlin variables to store user input
    private lateinit var nameEditText: TextInputEditText
    private lateinit var ageEditText: TextInputEditText
    private lateinit var programRadioGroup: RadioGroup
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Initialize views
        initializeViews()
        
        // Set up register button click listener
        setupRegisterButton()
    }
    
    /**
     * Initialize all input fields by finding them in the layout
     */
    private fun initializeViews() {
        nameEditText = findViewById(R.id.nameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        programRadioGroup = findViewById(R.id.programRadioGroup)
    }
    
    /**
     * Set up the Register button to capture input when tapped
     */
    private fun setupRegisterButton() {
        val registerButton: MaterialButton = findViewById(R.id.registerButton)
        
        registerButton.setOnClickListener {
            // Capture input from the form
            val name = nameEditText.text?.toString()?.trim() ?: ""
            val ageText = ageEditText.text?.toString()?.trim() ?: ""
            val selectedProgramId = programRadioGroup.checkedRadioButtonId
            
            // Validate input
            if (validateInput(name, ageText, selectedProgramId)) {
                // Parse age to integer
                val age = ageText.toInt()
                
                // Get selected program name
                val selectedProgram = when (selectedProgramId) {
                    R.id.stressManagementRadio -> getString(R.string.stress_management)
                    R.id.counselingRadio -> getString(R.string.counseling)
                    R.id.meditationRadio -> getString(R.string.meditation)
                    else -> ""
                }
                
                // Create Member object with captured data
                val member = Member(name, age, selectedProgram)
                
                // Navigate to Confirmation Screen using Intent
                navigateToConfirmation(member)
            }
        }
    }
    
    /**
     * Validate user input before registration
     */
    private fun validateInput(name: String, ageText: String, selectedProgramId: Int): Boolean {
        var isValid = true
        
        // Validate name
        if (name.isEmpty()) {
            nameEditText.error = getString(R.string.error_name_required)
            isValid = false
        } else {
            val nameParts = name.split(" ")
                .filter { it.isNotBlank() }
            if (nameParts.size < 2) {
                nameEditText.error = getString(R.string.error_full_name_required)
                isValid = false
            } else {
                nameEditText.error = null
            }
        }
        
        // Validate age
        if (ageText.isEmpty()) {
            ageEditText.error = getString(R.string.error_age_required)
            isValid = false
        } else {
            try {
                val age = ageText.toInt()
                if (age <= 0 || age > 150) {
                    ageEditText.error = getString(R.string.error_age_invalid)
                    isValid = false
                } else {
                    ageEditText.error = null
                }
            } catch (e: NumberFormatException) {
                ageEditText.error = getString(R.string.error_age_invalid)
                isValid = false
            }
        }
        
        // Validate program selection
        if (selectedProgramId == -1) {
            Toast.makeText(this, getString(R.string.error_program_required), Toast.LENGTH_SHORT).show()
            isValid = false
        }
        
        return isValid
    }
    
    /**
     * Navigate to Confirmation Screen using Intent to pass Member data
     */
    private fun navigateToConfirmation(member: Member) {
        // Create Intent to start ConfirmationActivity
        val intent = Intent(this, ConfirmationActivity::class.java)
        
        // Pass Member object as Parcelable extra
        intent.putExtra("MEMBER_DATA", member)
        
        // Start the Confirmation Activity
        startActivity(intent)
    }
}
