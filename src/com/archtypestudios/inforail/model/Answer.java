package com.archtypestudios.inforail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Answer {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private Question question;
	
	@DatabaseField
	private String englishText;
	
	@DatabaseField
	private String frenchText;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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
	
}
