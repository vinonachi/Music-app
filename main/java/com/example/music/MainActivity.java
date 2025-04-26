package com.example.music;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton, start, pause, stop;
    private MediaPlayer music;
    private String correctUsername = "vino";
    private String correctPassword = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views for login layout
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {
                    switchToMusicLayout();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Create MediaPlayer early
        music = MediaPlayer.create(this, R.raw.song);
    }

    // Play the music
    public void musicplay(View v) {
        if (music != null) {
            music.start();
        }
    }

    // Pause the music
    public void musicpause(View v) {
        if (music != null && music.isPlaying()) {
            music.pause();
        }
    }

    // Stop the music
    public void musicstop(View v) {
        if (music != null) {
            music.stop();
            music.release();
            music = null;
            // Re-create MediaPlayer
            music = MediaPlayer.create(this, R.raw.song);
        }
    }
    private void switchToMusicLayout() {
        // Switch to the music layout (activity_music.xml)
        setContentView(R.layout.music);

        // Initialize music layout views after switching
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);

        // Set click listeners for music control buttons
        start.setOnClickListener(this::musicplay);
        pause.setOnClickListener(this::musicpause);
        stop.setOnClickListener(this::musicstop);
    }

}