package com.archtypestudios.inforail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class TrainPartType {

	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String type;
}
