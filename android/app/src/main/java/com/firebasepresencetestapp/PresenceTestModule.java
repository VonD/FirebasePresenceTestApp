package com.firebasepresencetestapp;

// import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
// import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;

public class PresenceTestModule extends ReactContextBaseJavaModule {

	private DatabaseReference root;

	public PresenceTestModule(ReactApplicationContext reactContext) {
		super(reactContext);
		root = FirebaseDatabase.getInstance().getReference();
	}

	@Override
	public String getName() {
		return "PresenceTest";
	}

	@ReactMethod
	public void monitorPresence() {
		root.child(".info/connected").addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				boolean connected = snapshot.getValue(Boolean.class);
				Log.e("PresenceTest", "Connected to Firebase: " + String.valueOf(connected));
			}
			@Override
			public void onCancelled(DatabaseError error) {}
		});
	}

	@ReactMethod
	public void monitorData() {
		root.child("test").addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				boolean data = snapshot.getValue(Boolean.class);
				Log.e("PresenceTest", "Test data value: " + String.valueOf(data));
			}
			@Override
			public void onCancelled(DatabaseError error) {}
		});
	}

}
