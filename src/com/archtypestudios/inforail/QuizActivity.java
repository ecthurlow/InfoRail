package com.archtypestudios.inforail;

import java.util.ArrayList;
import java.util.List;

import com.archtypestudios.inforail.model.Answer;
import com.archtypestudios.inforail.model.Question;
import com.archtypestudios.inforail.model.TrainPart;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	
	int id;
	String name;
	
	List<Integer> selectedAnswers;
	
	//Elements
	TextView questionTextView;
	RadioGroup answerGroup;
	
	List<Question> questions;
	int questionIndex;
	
	Repository repository;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_quiz);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
        
        TextView subtitle = (TextView)findViewById(R.id.subtitle);
		
		repository = new Repository(this);
		
		//Get variables from Selected Train Activity
		Bundle bundle = getIntent().getExtras();
		id = bundle.getInt("id");
		name = bundle.getString("name");
		
		subtitle.setText(name + " " + getString(R.string.quiz));
		
		//Get elements
		ImageView questionImage = (ImageView) findViewById(R.id.quiz_image);
		questionTextView = (TextView)findViewById(R.id.quizQuestion);
		answerGroup = (RadioGroup)findViewById(R.id.radio_answerGroup);
		
		String drawableName = "train" + id;
        int drawableId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        
		questionImage.setImageResource(drawableId);
		
		//Get questions that belong to this train
		questions = repository.questions.getByTrain(id);
		questionIndex = 0;
		
		//Initialize selected answers
		selectedAnswers = new ArrayList<Integer>();
		
		if (questions.size() > 0 ) {
			
			loadNextQuestion();
		}
		
		else {
			questionTextView.setText(R.string.noQuestionsAvailable);
		}
		
	}
	
	public void nextQuestionHandler(View view) {
		
		//Check if anything is selected
		if (answerGroup.getCheckedRadioButtonId() == -1) {
			//Tell user to check an answer
			Toast.makeText(this, R.string.noAnswerSelected, Toast.LENGTH_SHORT).show();
		}
		
		else {
			//Get id of checked radio button
			int answerId = answerGroup.getCheckedRadioButtonId();
			
			//Store value in answers
			selectedAnswers.add(answerId);
			
			//If the previous question was the last question
			if (questionIndex == questions.size()-1) {
				finishQuiz();
			}
			
			else {
				//Load next question
				questionIndex++;
				loadNextQuestion();
			}
		}
	}
	
	public void loadNextQuestion() {
		
		//Remove old radio buttons from group
		answerGroup.clearCheck();
		answerGroup.removeAllViewsInLayout();
		
		//Get next question
		Question newQuesiton = questions.get(questionIndex);
		
		//Set questionTextView
		questionTextView.setText(newQuesiton.getTextStringId());
		
		//Get answers that belong to this question
		List<Answer> answers = repository.answers.getByQuestion(newQuesiton.getId());
		
		//For every answer, create a radio button
		for (Answer answer : answers) {
			
			RadioButton rb = new RadioButton(this);
			rb.setText(answer.getTextStringId());
			rb.setId(answer.getId());
			//rb.setButtonDrawable(R.drawable.button_radio);
			answerGroup.addView(rb);
		}
		
		//Change text on next question button to "Finish Quiz" if this is the last question
		if(questionIndex == questions.size()-1) {
			Button nextQuestionButton = (Button)findViewById(R.id.nextQuestionButton);
			nextQuestionButton.setText(R.string.button_finishQuiz);
		}
		
	}
	
	
	public void finishQuiz() {
		Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show();
		
		int score = 0;
		int total = questions.size();
		
		//Remove instructions, questions, and answers
		LinearLayout contentLayout = (LinearLayout)findViewById(R.id.quiz_content);
		contentLayout.removeAllViews();
		
		//Add Title
		TextView title = new TextView(this);
		title.setText(R.string.quiz_resutlts);
		contentLayout.addView(title);
		
		//Make a ScrollView
		ScrollView scrollView = new ScrollView(this);
		scrollView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		LinearLayout allResultsContainer = new LinearLayout(this);
		allResultsContainer.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams allResultsContainerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		allResultsContainer.setLayoutParams(allResultsContainerParams);
		
		//Go through answers
		for (Integer selectedAnswer: selectedAnswers) {
			
			//create answer LinearLayout container
			LinearLayout answerLayout = new LinearLayout(this);
			//answerLayout.setBackgroundColor(Color.parseColor("#00FF00"));
			answerLayout.setOrientation(LinearLayout.VERTICAL);
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 40, 0, 0);
			answerLayout.setLayoutParams(params);
			
			//Get answer from repository
			Answer answer = repository.answers.getById(selectedAnswer);
			
			//create questionTextView
			TextView resultQuestion = new TextView(this);
			//resultQuestion.setBackgroundColor(Color.parseColor("#0000FF"));
			resultQuestion.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			resultQuestion.setText(answer.getQuestion().getTextStringId());
			answerLayout.addView(resultQuestion);
			
			//create answerTextView
			TextView resultAnswer = new TextView(this);
			resultAnswer.setText(answer.getTextStringId());
			
			LinearLayout.LayoutParams resultAnswerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			resultAnswerParams.gravity = Gravity.BOTTOM;
			resultAnswerParams.setMargins(0, 0, 10, 0);
			
			resultAnswer.setLayoutParams(resultAnswerParams);
			
			//create answerIcon ImageView
			ImageView resultIcon = new ImageView(this);
			
			LinearLayout.LayoutParams resultIconParams = new LinearLayout.LayoutParams(20, 20);
			resultIconParams.gravity = Gravity.BOTTOM;
			
			resultIcon.setLayoutParams(resultIconParams);
			
			//Add correct or incorrect Icon
			if (answer.getIsCorrect() == true) {
				score++;
				resultIcon.setImageResource(R.drawable.result_check);
			}
			
			else {
				
				resultIcon.setImageResource(R.drawable.result_x);
			}
			
			LinearLayout resultContainer = new LinearLayout(this);
			resultContainer.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
			resultContainer.addView(resultAnswer);
			resultContainer.addView(resultIcon);
			
			answerLayout.addView(resultContainer);
			
			allResultsContainer.addView(answerLayout);
			
		}
		
		scrollView.addView(allResultsContainer);
		
		Button nextQuestionButton = (Button)findViewById(R.id.nextQuestionButton);
		nextQuestionButton.setText(R.string.button_retryQuiz);
		nextQuestionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
				intent.putExtra("id", id);
				intent.putExtra("name", name);
				startActivity(intent);
			}
		});
		
		contentLayout.addView(scrollView);
		

		//Check for perfect score
		if (score == total) {
			winTrainPart();
		}
		
	}
	
	public void winTrainPart() {
		
		List<TrainPart> wonTrainParts = repository.trainParts.getByTrain(id);
		
		for(TrainPart trainPart : wonTrainParts) {
			trainPart.setWon(true);
			repository.trainParts.updateTrainPart(trainPart);
		}
	}
	
	public void goToHome(View view) {
		Intent intent = new Intent(QuizActivity.this, HomeActivity.class);
		startActivity(intent);
	}
	
	public void goToTrainBuilder(View view) {
    	Intent intent = new Intent(QuizActivity.this, TrainBuilderActivity.class);
    	startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
	        setContentView(R.layout.activity_quiz);

	    } else {
	        setContentView(R.layout.activity_quiz);
	    }
	}

}
