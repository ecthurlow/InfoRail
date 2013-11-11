package com.archtypestudios.inforail.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class TrainPart {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField(dataType=DataType.ENUM_STRING)
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
		trainPartType = this.trainPartType;
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
	
	//TODO: make private?
	public enum TrainPartType {
		ENGINE,
		CAR,
		CABOOSE
	}
}
