package com.linhnv.apps.msport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.linhnv.apps.msport.MainActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

public class Utils {
	
	
	 private Utils() {};

	    @TargetApi(11)
	    public static void enableStrictMode() {
	        if (Utils.hasGingerbread()) {
	            StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
	                    new StrictMode.ThreadPolicy.Builder()
	                            .detectAll()
	                            .penaltyLog();
	            StrictMode.VmPolicy.Builder vmPolicyBuilder =
	                    new StrictMode.VmPolicy.Builder()
	                            .detectAll()
	                            .penaltyLog();

	            if (Utils.hasHoneycomb()) {
	                threadPolicyBuilder.penaltyFlashScreen();
	                vmPolicyBuilder
	                        .setClassInstanceLimit(MainActivity.class, 1)
	                        .setClassInstanceLimit(MainActivity.class, 1);
	            }
	            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
	            StrictMode.setVmPolicy(vmPolicyBuilder.build());
	        }
	    }

	    public static boolean hasFroyo() {
	        // Can use static final constants like FROYO, declared in later versions
	        // of the OS since they are inlined at compile time. This is guaranteed behavior.
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	    }

	    public static boolean hasGingerbread() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
	    }

	    public static boolean hasHoneycomb() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	    }

	    public static boolean hasHoneycombMR1() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
	    }

	    public static boolean hasJellyBean() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
	    }
	
	public static JSONObject getJSONFromURL(String url ,String data){
		try{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);	
		StringEntity se = new StringEntity(data);
		httpost.setEntity(se);
		httpost.setHeader("Accept", "application/json");
		httpost.setHeader("Content-type", "application/x-www-form-urlencoded");

		HttpResponse response = (HttpResponse) httpclient.execute(httpost);

		HttpEntity entity = response.getEntity();

		if (entity != null) {
			// Read the content stream
			InputStream instream = entity.getContent();

			// convert content stream to a String
			String resultString = convertStreamToString(instream);
			Log.i("Linhnv", "result :" + resultString);

			instream.close();
//			resultString = resultString.substring(1, resultString.length() - 1); 																				// "]"
			// Transform the String into a JSONObject
			JSONObject jsonObjRecv = null;
			try {
				jsonObjRecv = new JSONObject(resultString);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return jsonObjRecv;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	
	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
