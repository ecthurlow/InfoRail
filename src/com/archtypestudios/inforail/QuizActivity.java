package com.archtypestudios.inforail;

import java.util.ArrayList;
import java.util.List;

import com.archtypestudios.inforail.model.Answer;
import com.archtypestudios.inforail.model.Question;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		repository = new Repository(this);
		
		//Get variables from Selected Train Activity
		Bundle bundle = getIntent().getExtras();
		id = bundle.getInt("id");
		name = bundle.getString("name");
		
		//Get elements
		questionTextView = (TextView)findViewById(R.id.quizQuestion);
		answerGroup = (RadioGroup)findViewById(R.id.radio_answerGroup);
		
		//Get questions that belong to this train
		questions = repository.questions.getByTrain(id);
		questionIndex = 0;
		
		//Initialize selected answers
		selectedAnswers = new ArrayList<Integer>();
		
		loadNextQuestion();
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
		
		//Remove instructions, questions, and answers
		LinearLayout contentLayout = (LinearLayout)findViewById(R.id.quiz_content);
		contentLayout.removeAllViews();
		
		//Add Title
		TextView title = new TextView(this);
		title.setText(R.string.quiz_resutlts);
		contentLayout.addView(title);
		
		//Go through answers
		for (Integer selectedAnswer: selectedAnswers) {
			//create linear layout
			LinearLayout answerLayout = new LinearLayout(this);
			answerLayout.setOrientation(LinearLayout.VERTICAL);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 20, 0, 0);
			answerLayout.setLayoutParams(params);
			
			//Get answer from repository
			Answer answer = repository.answers.getById(selectedAnswer);
			
			//show question
			TextView resultQuestion = new TextView(this);
			resultQuestion.setText(answer.getQuestion().getTextStringId());
			answerLayout.addView(resultQuestion);
			
			//show answer
			TextView resultAnswer = new TextView(this);
			resultAnswer.setText(answer.getTextStringId());
			answerLayout.addView(resultAnswer);
			
			//Add correct or incorrect
			TextView result = new TextView(this);
			if (answer.getIsCorrect() == true) {
				result.setText(R.string.correct);
				answerLayout.addView(result, params);
			}
			
			else {
				result.setText(R.string.incorrect);
				answerLayout.addView(result);
				
			}
			
			contentLayout.addView(answerLayout);
			
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
		}
		
	}
	
	public void goToHome(View view) {
		Intent intent = new Intent(QuizActivity.this, HomeActivity.class);
		startActivity(intent);
	}
	
	public void goToTrainBuilder(View view) {
		//TODO: Implement goToTrainBuilder method
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

}
