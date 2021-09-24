package com.comp6442.route42.repository;

import com.comp6442.route42.model.Model;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class FirestoreRepository<T extends Model>{
  protected FirebaseAuth auth;
  protected static Firestore firestore;
  protected CollectionReference collection;
  protected Class<T> classType;

  public FirestoreRepository(String collectionPath, Class<T> cType) {
    this.classType = cType;

    try {
      FirebaseOptions options = FirebaseOptions.builder()
              .setCredentials(GoogleCredentials.getApplicationDefault())
              .setDatabaseUrl("https://softwareconstruction42.firebaseio.com/")
              .build();

      // Initialize the default app
      FirebaseApp defaultApp = FirebaseApp.initializeApp(options);

      System.out.println(defaultApp.getName());  // "[DEFAULT]"

      // Retrieve services by passing the defaultApp variable...
      auth = FirebaseAuth.getInstance();
      firestore = FirestoreClient.getFirestore();
      this.collection = firestore.collection("posts");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

//  abstract DocumentReference getOne(String id);

//  abstract void setMany(List<T> items);
}