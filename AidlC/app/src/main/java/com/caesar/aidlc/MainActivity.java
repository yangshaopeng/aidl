package com.caesar.aidlc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.caesar.aidls.IMyAidlInterface;
import com.caesar.aidls.Person;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private IMyAidlInterface remoteBinder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("yang", "客户端连接成功");
            /**
             * 连接成功，拿到远程服务的代理，proxy。
             */
            remoteBinder = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("yang", "客户端断开成功");
            remoteBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = (int)(Math.random() * 10);
                int num2 = (int)(Math.random() * 10);
                try {
                    int rst = remoteBinder.add(num1, num2);
                    Person person = new Person("aaa", 22);
                    remoteBinder.addPerson(person);
                    btn.setText(num1 + "+" + num2 + " = " + rst + "\n" + person.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
        bindRemoteService();
    }

    private void bindRemoteService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.caesar.aidls", "com.caesar.aidls.IRemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
