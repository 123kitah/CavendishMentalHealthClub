package com.example.cavendishmentalhealthclub

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ConfirmationActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.confirmationLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Receive data from Intent
        receiveAndDisplayData()
        
        // Set up back button
        setupBackButton()
    }
    
    /**
     * Receive Member data from Intent and display it
     */
    private fun receiveAndDisplayData() {
        // Retrieve Member object from Intent
        val member = intent.getParcelableExtra<Member>("MEMBER_DATA")
        
        if (member != null) {
            // Display the received data in TextViews
            val nameTextView: MaterialTextView = findViewById(R.id.nameValueTextView)
            val ageTextView: MaterialTextView = findViewById(R.id.ageValueTextView)
            val programTextView: MaterialTextView = findViewById(R.id.programValueTextView)
            
            nameTextView.text = member.name
            ageTextView.text = member.age.toString()
            programTextView.text = member.preferredProgram
        }
    }
    
    /**
     * Set up back button to return to registration screen
     */
    private fun setupBackButton() {
        val backButton: MaterialButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish() // Close this activity and return to previous screen
        }
    }
}






