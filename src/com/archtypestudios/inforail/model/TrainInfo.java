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
	private String englishText;
	
	@DatabaseField
	private String frenchText;
	
	@DatabaseField
	private String imageUrl;
	
	@DatabaseField
	private int xPosition;
	
	@DatabaseField
	private int yPosition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Train getTrainId() {
		return train;
	}

	public void setTrainId(Train train) {
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
}
