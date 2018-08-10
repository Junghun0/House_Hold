package com.example.parkjunghun.house_hold.Util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.parkjunghun.house_hold.Model.StoreInfoEvent;
import com.example.parkjunghun.house_hold.Model.StoreParseModel;
import com.example.parkjunghun.house_hold.Model.StoreUsingInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseStoreManager{

    private static FirebaseStoreManager instance = new FirebaseStoreManager();
    private HashMap<String, Object> datamap = new HashMap<>();
    private List<Map<String, Object>> datalist = new ArrayList<>();
    private StoreParseModel storeParseModel;

    private ArrayList<StoreParseModel> testlist = new ArrayList<>();

    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build();
    private FirebaseFirestore db;
    private SimpleDateFormat simpleDateFormat_day = new SimpleDateFormat("yyyy_MM_dd");
    private Date date = new Date();

    public static FirebaseStoreManager getInstance() {
        return instance;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public void setDb(FirebaseFirestore db) {
        this.db = db;
    }

    private FirebaseStoreManager(){
        db = FirebaseFirestore.getInstance();
        db.setFirestoreSettings(settings);

        db.collection("smsInfo").document(simpleDateFormat_day.format(date).toString());
    }

    public void setUsingInfo(StoreUsingInfo storeUsingInfo){
        db.collection("smsInfo").document(simpleDateFormat_day.format(date).toString()).collection("dayInfo").add(storeUsingInfo).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i("firebasestore onSuccess","onsucces");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("fierbasestore onFailure",e.toString());
            }
        });
    }

    public void getUsingInfo(final String date){
        db.collection("smsInfo").document(date).collection("dayInfo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("FirebaseStore@", document.getId() + " => " + document.getData());
                        datalist.add(document.getData());
                    }

                    for(int i=0; i < datalist.size();i++){
                        datamap.put("mydata",datalist.get(i).get("usingmap"));
                    }

                    for(int i=0; i <datalist.size();i++){

                        Log.d("mydata~~",""+((Map)datalist.get(i).get("usingmap")).get("bank"));
                        Log.d("mydata~~",""+((Map)datalist.get(i).get("usingmap")).get("using_time"));
                        Log.d("mydata~~",""+((Map)datalist.get(i).get("usingmap")).get("balance"));
                        Log.d("mydata~~",""+((Map)datalist.get(i).get("usingmap")).get("place"));
                        Log.d("mydata~~",""+((Map)datalist.get(i).get("usingmap")).get("using_money"));

                        storeParseModel = new StoreParseModel(((Map)datalist.get(i).get("usingmap")).get("bank").toString(), ((Map)datalist.get(i).get("usingmap")).get("using_time").toString(),((Map)datalist.get(i).get("usingmap")).get("balance").toString(),((Map)datalist.get(i).get("usingmap")).get("place").toString(),((Map)datalist.get(i).get("usingmap")).get("using_money").toString());
                        testlist.add(storeParseModel);
                    }
                    //result 로 구분해야될듯 ?
                    EventBus.getDefault().post(new StoreInfoEvent(testlist, true, datalist.size()));
                } else {
                    Log.d("FirebaseStore", "Error getting documents: ", task.getException());
                }
            }
        });

    }


}
