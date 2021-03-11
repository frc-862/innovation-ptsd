package com.frc.frcinnovationptsd.listeners;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.OnSuccessListener;

public interface FirebaseSuccessListener<T> extends OnSuccessListener<T>
{
    public T getResult();

    @Override
    public void onSuccess(T o);
}
