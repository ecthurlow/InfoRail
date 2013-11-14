package com.archtypestudios.inforail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class TrainInfo {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private Train train;
	
	@DatabaseField
	private String textStringId;
	
	@DatabaseField
	private String imageUrl;
	
	@DatabaseField
	private float xPosition;
	
	@DatabaseField
	private float yPosition;

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

	public String getTextStringId() {
		return textStringId;
	}

	public void setTextStringId(String textStringId) {
		this.textStringId = textStringId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
}
