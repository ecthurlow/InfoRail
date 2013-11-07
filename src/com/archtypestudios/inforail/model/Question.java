package com.archtypestudios.inforail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Question {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private Train train;
	
	@DatabaseField
	private String englishText;
	
	@DatabaseField
	private String frenchText;
	
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private QuestionType questionType;
	
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private Answer answer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public String getEnglishText() {
		return englishText;
	}

	public void setEnglishText(String englishText) {
		this.englishText = englishText;
	}

	public String getFrenchText() {
		return frenchText;
	}

	public void setFrenchText(String frenchText) {
		this.frenchText = frenchText;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
