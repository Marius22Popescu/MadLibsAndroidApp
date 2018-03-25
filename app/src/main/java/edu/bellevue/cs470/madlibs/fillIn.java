package edu.bellevue.cs470.madlibs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fillIn extends AppCompatActivity {

    private EditText mEditText;
    private TextView mQuestionView;
    private TextView mWordsLeftView;
    private Button mOkButton;
    private Button mSeeStoryButton;
    private String story;
    private String placeholders;
    private String [] placeholdersArray;
    private ArrayList<String> inputs = new ArrayList<String>();
    private ArrayList<String> answers  = new ArrayList<String>();
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in);

        mEditText = (EditText) findViewById(R.id.edit_text1);
        mQuestionView = (TextView) findViewById(R.id.tv_3);
        mWordsLeftView = (TextView) findViewById(R.id.tv_2);
        mOkButton = (Button) findViewById(R.id.button_ok);
        mSeeStoryButton = (Button) findViewById(R.id.button_see_story);
        i = 0;

        //Call the readFileData method in order to read the text file in a string.
        story = readFileData();

        //Get the placeholders inside a ArrayList
        placeholders = getPlaceholders(story);
        placeholdersArray = placeholders.split("/");
        for (int k = 0; k < placeholdersArray.length; k++) {
            inputs.add("Enter a " + placeholdersArray[k]);
        }
        inputs.add("The story is ready, push \"see story\" button!");

        //Set data for the first display
        mQuestionView.setText(inputs.get(i));
        mWordsLeftView.setText((inputs.size() - (i+1)) + " word(s) left");

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s;
                s = mEditText.getText().toString();
                //get the answer from the EditView
                answers.add(s);
                mEditText.setText("");
                i++;
                //set the question in TextView exp "person's-name"
                mQuestionView.setText(inputs.get(i));
                mWordsLeftView.setText((inputs.size() - (i+1)) + " word(s) left");
            }
        });

         mSeeStoryButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //replace the story
                 story = replace(story);
                 String  textEntered = story;
                 // Storing the Context in a variable 
                 Context context = fillIn.this;
                 // Creating the class that we want to start (and open) when the button is clicked
                 Class destinationActivity = NewStoryActivity.class;
                 //Create the Intent that will start the Activity we specified above
                 Intent startChildActivityIntent = new Intent(context, destinationActivity);
                 //putExtra method to put the String from the EditText in the Intent
                 startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, textEntered);
                 //start the ChildActivity
                 startActivity(startChildActivityIntent);
             }
         });

}

    //This method will read the file inside a string
    private String readFileData() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.example));
        String input = "";
        String text = "";
        while (scan.hasNextLine()) {
            input = scan.nextLine();
            text += input + "\n";
        }
        return text;
}
    //This method will read the placeholders and return them as a string
    private String getPlaceholders(String txt) {
        String word = "";
        String words = "";

        //Split the story by space
        String textCol = "";
        String textSpace[] = txt.split(" ");

        for (int i = 0; i < textSpace.length; i++) {
            textCol += textSpace[i] + "\n";
        }
            //Creating the pattern, searching for the variable an display them
            Pattern pattern = Pattern.compile(".* ?<(.*)> ?");
            Matcher matcher = pattern.matcher(textCol);
            while (matcher.find()) {
                word = matcher.group(1);
                words += word + "/";
            }
        return words;
    }

    // This method will replace the placeholders in the story
    private String replace (String old){
        String newStory = old;
        //Split the story by space
        String textCol = "";
        String textSpace[] = old.split(" ");
        for (int i = 0; i < textSpace.length; i++) {
            textCol += textSpace[i] + "\n";
        }

        //Creating the pattern, searching for the placeholders and replace them
        Pattern pattern2 = Pattern.compile(".* ?(<.*>) ?");
        Matcher matcher2 = pattern2.matcher(textCol);
        //Replace the placeholders with the user input
        int j = 0;
        while(matcher2.find()) {
            newStory = newStory.replaceFirst(matcher2.group(1), answers.get(j));
            Log.d("test1", matcher2.group(1)); // This is just for testing
            j++;
        }
        return newStory;
    }
}

