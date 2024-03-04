package com.example.tappingspeed

import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.example.tappingspeed.ui.theme.TappingspeedTheme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction


val sentences = listOf(
    "How are you today?",
    "What's the weather like?",
    "Can you help me with this?",
    "Where is the nearest supermarket?",
    "What time is it?",
    "I'm running late, sorry.",
    "Can I have the bill, please?",
    "Could you repeat that, please?",
    "What's your phone number?",
    "Do you have Wi-Fi here?",
    "How much does this cost?",
    "Excuse me, where's the bathroom?",
    "What would you recommend from the menu?",
    "Are you free this weekend?",
    "I'm not feeling well.",
    "Can we meet a bit earlier?",
    "What do you think about this?",
    "I'm really looking forward to it.",
    "Can you turn down the music?",
    "I'll be there in 10 minutes.",
    "This is my friend, Benjamin.",
    "It's nice to meet you.",
    "Could you please speak slower?",
    "I didn't catch that.",
    "Can I try this on?",
    "Where can I park my car?",
    "I think we're lost.",
    "Can we take a rain check?",
    "I'd like a coffee, please.",
    "It's too expensive.",
    "Could we have some more bread?",
    "I'm allergic to nuts.",
    "Can I pay by card?",
    "I'd like to make a reservation.",
    "What's the Wi-Fi password?",
    "It's my treat today.",
    "Do you know how to get to Panama?",
    "I need to go to the doctor.",
    "Can you recommend a good movie?",
    "I didn't mean to do that.",
    "Can we share this?",
    "I'm not interested, thank you.",
    "Could I have a glass of water?",
    "What's your favorite color?",
    "I'm vegetarian.",
    "Can you call me a taxi?",
    "How do I get to the train station?",
    "I need a day off.",
    "I'm looking for a job.",
    "Do you know what time it is?",
    "It's too cold outside.",
    "Can I have a menu, please?",
    "I'd like to cancel my order.",
    "What's the exchange rate?",
    "Can I have a receipt, please?",
    "Where's the exit?",
    "I'm here for business.",
    "I'll take your advice.",
    "That sounds great.",
    "Can you charge my phone?",
    "I need some advice.",
    "I'm here on vacation.",
    "I'd like to check out.",
    "Can I leave my luggage here?",
    "I need an interpreter.",
    "Can we sit outside?",
    "What's your return policy?",
    "I'm just browsing, thanks.",
    "I'm looking for the pumpkin bread.",
    "Could you lower the air conditioning?",
    "I'll have the same thing.",
    "When is the deadline?",
    "Can you hold this for me?",
    "Do you accept returns?",
    "How long does delivery take?",
    "I'm here to see Susanne.",
    "Can I have some ketchup?",
    "Do you have this in a smaller size?",
    "What are the ingredients?",
    "I'd like to book a flight.",
    "Can I have a late check-out?",
    "Where can I find a taxi?",
    "I have a reservation under Alexandra.",
    "What time do we land?",
    "Can you wake me up at 7 AM?",
    "Is breakfast included?",
    "How do I use this?",
    "Can we have two more chairs?",
    "Where is the closest ATM?",
    "Do you have non-dairy milk?",
    "I'm locked out of my room.",
    "Can I change my order?",
    "What's your best price?",
    "Can I have the Wi-Fi password again?",
    "Do you have a map?",
    "I'll have a beer, please.",
    "Can you recommend a good restaurant?",
    "Is there a gym here?",
    "I need to change my room.",
    "Can you help me with my bags?"
)

fun Sentence(): String {
    val randomIndex = Random.nextInt(sentences.size - 1)
    return sentences[randomIndex]

}

@Composable
fun Randsentence(sentence2:String, modifier: Modifier = Modifier) {
    // Assuming 'sentences' is a List<String> defined elsewhere
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            contentAlignment = Alignment.Center // Centers the text box within its allocated space
        ) {
            Text(
                text = sentence2,
                fontSize = 30.sp,
                style = TextStyle(color = Color.Black), // Set text color to black
                modifier = Modifier
                    .background(Color(0xFFFFA500)) // Set background to orange using ARGB
                    .padding(20.dp) // Add some padding around the text for better aesthetics
                    .padding(bottom = 20.dp)
            )
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TappingspeedTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val sentence2 = Sentence()
    Column(modifier = Modifier.fillMaxSize()) {
        UpperFunction(sentence2)
    }
}


@Composable
fun UpperFunction(sentence2:String) {
    var text by remember { mutableStateOf("") }
    val timer = remember { InputTimer() }
    var currentSentence by remember { mutableStateOf(sentence2) }
    var charsPerSecond by remember { mutableDoubleStateOf(0.0) }
    var click = false
    var correctness = 100.0


    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Write the text below here:",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = text,
            onValueChange = { newText ->
                if (newText.length == 1) { // Start timer on first character input
                    timer.manageTime()
                }
                text = newText
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val elapsedTime = timer.complete() // Get elapsed time in seconds
                click = true
                if (text.isNotEmpty()) {
                    charsPerSecond = text.length / elapsedTime // Calculate characters per second
                    handleInput(text, charsPerSecond)
                    correctness = calculateCorrectness(currentSentence, text)
                }
                currentSentence = Sentence()
                // Reset text for next input
                text = ""
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Next")
        }
        if (!click) {
            val elapsedTime = timer.timeNow() // Get elapsed time in seconds
            if (text.isNotEmpty()) {
                charsPerSecond = text.length / elapsedTime // Calculate characters per second
                correctness = calculateCorrectness(currentSentence, text)
            }
        }
        if (charsPerSecond > 0) {
            Text("Characters per second typed: " + String.format("%.2f", charsPerSecond))
            Text("You have written $correctness% of all Characters correctly")
        }
        Randsentence(sentence2 = currentSentence)
    }
}

fun calculateCorrectness(correctText: String, inputText: String): Double {
    fun recurse(index: Int, matches: Int): Int {
        if (index >= correctText.length || index >= inputText.length) {
            return matches
        }
        val newMatches = if (correctText[index] == inputText[index]) matches + 1 else matches
        return recurse(index + 1, newMatches)
    }

    val matchedCharacters = recurse(0, 0)
    val length = correctText.length
    return (matchedCharacters.toDouble() / length) * 100
}


// Adjusted handleInput function to accept duration
fun handleInput(input: String, charsPerSecond: Double) {
    // Handle the input and characters per second calculation
    Log.d("InputHandling", "User input: $input, Chars per second: $charsPerSecond")
}

// Function to manage timing logic
class InputTimer {
    private var startTime: Long = 0L
    private var started: Boolean = false

    fun manageTime() {
        if (!started) {
            startTime = System.currentTimeMillis()
            started = true
        }
    }

    fun complete(): Double {
        if (started) {
            started = false
            return (System.currentTimeMillis() - startTime) / 1000.0
        }
        return 0.0
    }
    fun timeNow(): Double{
        if (started) {
            return (System.currentTimeMillis() - startTime) / 1000.0
        }
        return 0.0
    }
}