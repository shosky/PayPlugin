package com.sealink.weixin.cordova;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

import com.pingplusplus.android.PaymentActivity;
import com.sealink.pay.model.Charge;

public class PayPlugin extends CordovaPlugin {

	private static final int REQUEST_CODE_PAYMENT = 1;
    private CallbackContext callbackContext;
    
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		Activity activity = this.cordova.getActivity();
		 this.callbackContext = callbackContext;
		if (action.equals("weixinPay")) 
		{
			
			String charge = args.optString(0).toString();

            Intent intent = new Intent();
            String packageName = this.cordova.getActivity().getPackageName();
            ComponentName componentName = new ComponentName(packageName, packageName + ".wxapi.WXPayEntryActivity");
            intent.setComponent(componentName);
            intent.putExtra(PaymentActivity.EXTRA_CHARGE, charge);
            this.cordova.startActivityForResult(this, intent, REQUEST_CODE_PAYMENT);
            
			return true;
		}
		return false;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == android.app.Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                if(result.equals("success")){
                    callbackContext.success(result);
                }else {
                    callbackContext.error(result);
                }
            } else if (resultCode == android.app.Activity.RESULT_CANCELED) {
                callbackContext.error("cancel");
            }
        }
    }
}