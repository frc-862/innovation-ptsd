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

import java.util.Map;

public class FirebaseCloud {
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    /**
     Adds the user to Firebase CloudStore along with their information.
     @param userInfo A map of the information for the user
     */
    public void addUsers(Map<String, String> userInfo){
        database.collection(Constants.FIREBASE_USERS_COLLECTION_NAME)
                .add(userInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("FirebaseCloud: save data " + documentReference.getId());
                    }
                });
    }


    /**
     Retrieves one field of user information from Firebase CloudStore.
     @param username The name of the user
     @param fieldName The name of the field to retrieve for the user
     @return the value of the field requested
     */
    public String getData(String username, String fieldName) {
        FirebaseSuccessListener<QuerySnapshot> listener = new FirebaseSuccessListener<QuerySnapshot>() {
            private String result;

            @Override
            public String getResult() {
                return result;
            }

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                    if (doc.getId().equals(username)) {
                        result = doc.get(fieldName).toString();
                    }
                }
            }
        };

        database.collection(Constants.FIREBASE_USERS_COLLECTION_NAME)
                .get()
                .addOnSuccessListener(listener);

        return listener.getResult();
    }
}
