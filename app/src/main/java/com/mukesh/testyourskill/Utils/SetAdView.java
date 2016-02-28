package com.mukesh.testyourskill.utils;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class SetAdView {
	
	 AdView adView;
     String unitid;Context ctx;
	 InterstitialAd instad;
	 private InterstitialAd iAd;
	
	public SetAdView( String unitid,Context ctx,AdView adView)
	{
		this.unitid=unitid;
		this.ctx=ctx;
		this.adView=adView;
		
	}
  public void showAd() 
  {
	 
	AdRequest adRequest = new AdRequest.Builder().build();
	adView.loadAd(adRequest);
  }
	   
  
  

public void displayInterstitial1() 
{
iAd = new InterstitialAd(ctx);
iAd.setAdUnitId(unitid);

iAd.setAdListener(new AdListener() {
	@Override
	public void onAdLoaded() {
		//Log.d("TAG", "onAdLoaded");
		showInterstitial() ;
		//Toast.makeText(ctx, "Ad loaded successfully", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onAdFailedToLoad(int errorCode) {
		//String errorMessage = String.format("Failed to load add : "+ getErrorReason(errorCode));
		//Log.d("TAG", errorMessage);
		//Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show();
	}
});

       loadInterstitial();
}

public void loadInterstitial() {
AdRequest adRequest = new AdRequest.Builder()
//.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//.addTestDevice("8F156441E82CA13A0C3AB51368D00D0B")
   .build();


iAd.loadAd(adRequest);
}

public void showInterstitial() {
if (iAd.isLoaded()) {
	iAd.show();
} else {
	Log.d("TAG", "Interstitial ad is not loaded yet");
}
}

/** 
* Gets a string error reason from an error code
* 
* @param errorCode
* @return
*/
/*private String getErrorReason(int errorCode) {

String errorReason = "unknown error";

switch(errorCode) {
	case AdRequest.ERROR_CODE_INTERNAL_ERROR:
		errorReason = "internal error";
		break;
	case AdRequest.ERROR_CODE_INVALID_REQUEST:
		errorReason = "invalid request";
		break;
	case AdRequest.ERROR_CODE_NETWORK_ERROR:
		errorReason = "network Error";
		break;
	case AdRequest.ERROR_CODE_NO_FILL:
		errorReason = "no fill";
		break;
}
return errorReason;
}*/
}
