package com.whosmyqueen.boothprint;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.whosmyqueen.boothprint.base.AppInfo;
import com.whosmyqueen.boothprint.print.PrintUtil;
import com.whosmyqueen.boothprint.util.ToastUtil;


/**
 * Created by liuguirong on 8/1/17.
 */

public class BluetoothController {

    public static BluetoothAdapter init(BluetoothAdapter adapter) {
        if (null == adapter) {
            adapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (null == adapter) {
            ToastUtil.showToast(AppInfo.appContext, "该设备没有蓝牙模块");
            return adapter;
        }
        Log.d("adapter.getState()", "adapter.getState()" + adapter.getState());
        if (!adapter.isEnabled()) {
            //没有在开启中也没有打开
//            if ( adapter.getState()!=BluetoothAdapter.STATE_TURNING_ON  && adapter.getState()!=BluetoothAdapter.STATE_ON ){
            if (adapter.getState() == BluetoothAdapter.STATE_OFF) {//蓝牙被关闭时强制打开
                adapter.enable();
            } else {
                ToastUtil.showToast(AppInfo.appContext, "蓝牙未打开");
                return adapter;
            }
        }
        String address = PrintUtil.getDefaultBluethoothDeviceAddress(AppInfo.appContext);
        if (TextUtils.isEmpty(address)) {
            ToastUtil.showToast(AppInfo.appContext, "尚未绑定蓝牙设备");
            return adapter;
        }
        String name = PrintUtil.getDefaultBluetoothDeviceName(AppInfo.appContext);
//        activity.tv_bluename.setText("已绑定蓝牙：" + name);
//        activity.tv_blueadress.setText(address);
        return adapter;
    }

    public static boolean turnOnBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.enable();
        }
        return false;
    }
}
