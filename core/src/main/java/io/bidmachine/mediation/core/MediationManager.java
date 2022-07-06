package io.bidmachine.mediation.core;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

import io.bidmachine.mediation.core.network.NetworkAdUnit;
import io.bidmachine.mediation.core.network.NetworkAdapter;

public class MediationManager {

    private static final AtomicBoolean isInitializing = new AtomicBoolean(false);
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final Set<InitializeListener> listenerSet = new CopyOnWriteArraySet<>();

    /**
     * Initializes MediationManager.
     *
     * @param activity Your activity.
     */
    public static synchronized void initialize(@NonNull Activity activity) {
        initialize(activity, null);
    }

    /**
     * Initializes MediationManager.
     *
     * @param activity Your activity.
     * @param listener Instance of {@link InitializeListener} to notify about the end of initialization.
     */
    public static synchronized void initialize(@NonNull Activity activity, @Nullable InitializeListener listener) {
        if (isInitialized()) {
            sendOnInitialized(listener);
            return;
        }
        if (listener != null) {
            listenerSet.add(listener);
        }
        if (isInitializing.getAndSet(true)) {
            return;
        }
        MediationLogger.log("initialize");

        MediationSetting.register(activity);
        Utils.onBackgroundThread(() -> NetworkManager.initializeAdNetworks(activity, MediationManager::onInitialized));
    }

    /**
     * Returns True if MediationManager was already initialized.
     */
    public static boolean isInitializing() {
        return isInitializing.get();
    }

    /**
     * Returns True if MediationManager is in initialization state.
     */
    public static boolean isInitialized() {
        return isInitialized.get();
    }

    /**
     * Registers ad network adapter for mediation.
     *
     * @param context        Your context.
     * @param networkAdapter Ad network adapter.
     */
    public static <T extends NetworkAdUnit> void registerAdNetwork(@NonNull Context context,
                                                                   @NonNull NetworkAdapter<T> networkAdapter) {
        NetworkManager.registerAdNetwork(context, networkAdapter);
    }

    /**
     * Sets MediationManager logs enabled.
     *
     * @param isEnabled If {@code true} MediationManager will print all information.
     */
    public static void setLoggingEnabled(boolean isEnabled) {
        if (isEnabled) {
            MediationLogger.setEnabled(true);
            MediationLogger.log("setLoggingEnabled - true");
        } else {
            MediationLogger.log("setLoggingEnabled - false");
            MediationLogger.setEnabled(false);
        }
    }

    private static void onInitialized() {
        MediationLogger.log("onInitialized");

        isInitialized.set(true);
        isInitializing.set(false);
        for (InitializeListener initializeListener : listenerSet) {
            sendOnInitialized(initializeListener);
        }
        listenerSet.clear();
    }

    private static void sendOnInitialized(@Nullable InitializeListener listener) {
        if (listener != null) {
            listener.onInitialized();
        }
    }

}