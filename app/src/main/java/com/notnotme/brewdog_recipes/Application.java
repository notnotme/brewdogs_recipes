package com.notnotme.brewdog_recipes;

import android.util.Log;

import com.notnotme.brewdog_recipes.controller.application.ControllerProvider;
import com.notnotme.brewdog_recipes.controller.application.api.ApiProvider.ApiController;
import com.notnotme.brewdog_recipes.controller.application.api.retrofit.RetrofitApiControllerImpl;
import com.notnotme.brewdog_recipes.controller.application.storage.StorageProvider.StorageController;
import com.notnotme.brewdog_recipes.controller.application.storage.realm.RealmStorageControllerImpl;
import com.squareup.leakcanary.LeakCanary;

public final class Application extends android.app.Application {

    private static final String TAG = Application.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) return;
        LeakCanary.install(this);

        try {
            ControllerProvider.registerController(this, StorageController.class, RealmStorageControllerImpl.class);
            ControllerProvider.registerController(this, ApiController.class, RetrofitApiControllerImpl.class);
            Log.i(TAG, "All your base are belong to us");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
