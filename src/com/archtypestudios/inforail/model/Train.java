package com.archtypestudios.inforail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Train {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String nameStringId;
	
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private TrainPart trainPart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameStringId() {
		return nameStringId;
	}

	public void setNameStringId(String nameStringId) {
		this.nameStringId = nameStringId;
	}

	public TrainPart getTrainPart() {
		return trainPart;
	}

	public void setTrainPart(TrainPart trainPart) {
		this.trainPart = trainPart;
	}
	
}
