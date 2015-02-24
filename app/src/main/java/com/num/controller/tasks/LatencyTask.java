package com.num.controller.tasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.num.controller.utils.PingUtil;
import com.num.model.Address;
import com.num.model.Ping;

import java.util.HashMap;

public class LatencyTask implements Runnable {

    private Address target;
    private Handler handler;

    public LatencyTask(Address target, Handler handler) {
        this.target = target;
        this.handler = handler;
    }

    @Override
    public void run() {
        HashMap<String, String> params = new HashMap<>();
        Bundle bundle = new Bundle();
        bundle.putString("type", "ping");
        Message msg = new Message();

        params.put("-c", "15");
        params.put("-i", "0.5");
        Ping ping = PingUtil.ping(target, params);
        bundle.putParcelable("ping", ping);

        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
