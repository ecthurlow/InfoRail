package com.archtypestudios.inforail.widgets;

import com.archtypestudios.inforail.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class TrainInfoDialogueFragment extends DialogFragment {

	protected String trainInfo;
	
	public TrainInfoDialogueFragment(String trainInfo) {
		// TODO Auto-generated constructor stub
		this.trainInfo = trainInfo;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(trainInfo)
               .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                      dialog.dismiss();
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
	}
}
