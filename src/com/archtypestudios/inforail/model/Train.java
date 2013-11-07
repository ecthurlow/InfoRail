package com.archtypestudios.inforail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Train {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String englishName;
	
	@DatabaseField
	private String frenchName;
	
	@DatabaseField
	private String iconUrl;
	
	@DatabaseField
	private String imageUrl;
	
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private TrainPart trainPart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getFrenchName() {
		return frenchName;
	}

	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public TrainPart getTrainPartPrizeId() {
		return trainPart;
	}

	public void setTrainPartPrizeId(TrainPart trainPart) {
		this.trainPart = trainPart;
	}
	
}
