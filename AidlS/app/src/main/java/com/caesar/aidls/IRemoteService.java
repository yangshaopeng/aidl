package com.caesar.aidls;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yangshaopeng.
 * Description:.....
 * 2019/9/21 22:10
 * email : yangshaopeng_it@163.com
 */
public class IRemoteService extends Service {

    private List<Person> persons;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("yang", "开始连接 service binder");
        persons = new ArrayList<>();
        return binder;
    }

    IBinder binder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.i("yang", "计算");
            return num1 + num2;
        }

        @Override
        public List<Person> addPerson(Person person) {
            persons.add(person);
            return persons;
        }
    };

}
