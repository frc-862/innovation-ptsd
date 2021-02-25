package com.frc.frcinnovationptsd;

import androidx.annotation.NonNull;

import com.frc.frcinnovationptsd.listeners.FirebaseSuccessListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.HashMap;
import java.util.Map;

public class FirebaseCloud {
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    private DocumentReference userDocument;

    public FirebaseCloud(String username){
        // retrieve current user data
        database.collection(Constants.FIREBASE_USERS_COLLECTION_NAME)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for(DocumentSnapshot doc : queryDocumentSnapshots){
                        Object name = doc.get(Constants.FIREBASE_USERNAME_FIELD_NAME);
                        if(name != null && name.toString().equals(username)){
                            userDocument = doc.getReference();
                        }
                    }
                    if(userDocument == null){
                        userDocument = addUsers(new HashMap<String, String>(){
                            {put(Constants.FIREBASE_USERNAME_FIELD_NAME, username);}
                        });
                    }
                });
    }

    /**
     Adds the user to Firebase CloudStore along with their information.
     @param userInfo A map of the information for the user
     */
    private DocumentReference addUsers(Map<String, String> userInfo){
        FirebaseSuccessListener<DocumentReference> listener = new FirebaseSuccessListener<DocumentReference>(){
            private DocumentReference result;
            @Override
            public DocumentReference getResult() {
                return result;
            }

            @Override
            public void onSuccess(DocumentReference o) {
                result = o;
            }
        };

        database.collection(Constants.FIREBASE_USERS_COLLECTION_NAME)
                .add(userInfo)
                .addOnSuccessListener(listener);

        return listener.getResult();
    }


    /**
     Retrieves one field of user information from Firebase CloudStore.
     @param fieldName The name of the field to retrieve for the user
     @return the value of the field requested
     */
    public String getData(String fieldName) {
        FirebaseSuccessListener<DocumentSnapshot> listener = new FirebaseSuccessListener<DocumentSnapshot>() {
            private DocumentSnapshot documentSnapshot;
            @Override
            public DocumentSnapshot getResult() {
                return documentSnapshot;
            }

            @Override
            public void onSuccess(DocumentSnapshot o) {
                documentSnapshot = o;
            }
        };

        userDocument.get().addOnSuccessListener(listener);

        return listener.getResult().toString();
    }

    /**
     Updates one field of user information to Firebase CloudStore.
     @param fieldName The name of the field to retrieve for the user
     @param fieldValue The new field value
     @return the value of the field requested
     */
    public void updateUser(String fieldName, String fieldValue){
        userDocument.update(fieldName, fieldValue);
    }
}
