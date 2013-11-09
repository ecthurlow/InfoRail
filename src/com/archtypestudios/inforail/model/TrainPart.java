package com.archtypestudios.inforail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class TrainPart {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private TrainPartType trainPartType;
	
	@DatabaseField
	private String imageUrl;
	
	@DatabaseField
	private Boolean won;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TrainPartType getTrainPartType() {
		return trainPartType;
	}

	public void setTrainPartType(TrainPartType trainPartType) {
		trainPartType = trainPartType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getWon() {
		return won;
	}

	public void setWon(Boolean won) {
		this.won = won;
	}
}
