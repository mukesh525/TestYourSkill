package com.mukesh.testyourskill.parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;

public class InetConnectionPOST {

	private boolean connecting;
	//private String url = null;
	StringBuffer msg;
	private String jsonRequest = null;
	private String responsData = null;
	private String property = null;
	private boolean isTimeout = false;
	private String responseTypeString = null;
	private int rc;
	private String erroMsg;
	private String url = "",exsistingFileName="",_data="",requestString="",task="";
	ContentValues val;
	public InetConnectionPOST(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.jsonRequest = "";
	}

	public boolean isConnected() {
		return connecting;
	}

	// always verify the host - dont check for certificate
	final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
 
	public void connect() {
		// TODO Auto-generated method stub

		InputStream _in = null;
		OutputStream _out = null;
		

		URL url;
		HttpURLConnection _httpconn = null;

		try {
			url = new URL(this.url);
			_httpconn = (HttpURLConnection) url.openConnection();
			_httpconn.setRequestMethod("POST");
			
			_httpconn.setDoOutput(true);
			//_httpconn.setDoInput(true);
			_httpconn.setReadTimeout(10000 /* milliseconds */);
			_httpconn.setConnectTimeout(15000 /* milliseconds */);
			_httpconn.connect();
			_out = _httpconn.getOutputStream();
			//_out.write(requestString.getBytes());
			_out.flush();

			rc = _httpconn.getResponseCode();

			if (rc == -1) {
				connect();
				return;
			}
			responseTypeString = _httpconn.getResponseMessage();
			System.out.println("-------requestString data----------1");
			System.out.println(_httpconn.getResponseMessage()+ " -------resp code-------------2" + rc);
			
			if (rc == HttpsURLConnection.HTTP_OK) {
				_in = _httpconn.getInputStream();
				msg = new StringBuffer();
				byte[] data = new byte[1024];
				int len = 0;
				int size = 0;

				while (-1 != (len = _in.read(data))) {
					msg.append(new String(data, 0, len));
					size += len;
				}
				responsData = msg.toString();
				connecting = true;
				System.out.println("-------response data--------3" + responsData);
			}

		} catch (SocketTimeoutException ste) {
			// TODO Auto-generated catch block
			isTimeout = true;
			ste.printStackTrace();

		} catch (SocketException ste) {
			// TODO Auto-generated catch block
			isTimeout = true;
			ste.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (_httpconn != null)
					_httpconn.disconnect();
			} catch (Exception ex) {
			}

			try {
				if (_in != null)
					_in.close();
			} catch (Exception ex) {
			}

			try {
				if (_out != null)
					_out.close();
			} catch (Exception ex) {
			}

		}
	}
    public void connect(String requestString) {
        // TODO Auto-generated method stub

        InputStream _in = null;
        OutputStream _out = null;


        URL url;
        HttpURLConnection _httpconn = null;

        try {
            url = new URL(this.url);
            _httpconn = (HttpURLConnection) url.openConnection();
            _httpconn.setRequestMethod("POST");

            _httpconn.setDoOutput(true);
            //_httpconn.setDoInput(true);
            _httpconn.setReadTimeout(10000 /* milliseconds */);
            _httpconn.setConnectTimeout(15000 /* milliseconds */);
            _httpconn.connect();
            _out = _httpconn.getOutputStream();
            _out.write(requestString.getBytes());
            _out.flush();

            rc = _httpconn.getResponseCode();

            if (rc == -1) {
                connect(requestString);
                return;
            }
            responseTypeString = _httpconn.getResponseMessage();
            System.out.println("-------requestString data----------1" + requestString);
            System.out.println(_httpconn.getResponseMessage()+ " -------resp code-------------2" + rc);

            if (rc == HttpsURLConnection.HTTP_OK) {
                _in = _httpconn.getInputStream();
                msg = new StringBuffer();
                byte[] data = new byte[1024];
                int len = 0;
                int size = 0;

                while (-1 != (len = _in.read(data))) {
                    msg.append(new String(data, 0, len));
                    size += len;
                }
                responsData = msg.toString();
                connecting = true;
                System.out.println("-------response data--------3" + responsData);
            }

        } catch (SocketTimeoutException ste) {
            // TODO Auto-generated catch block
            isTimeout = true;
            ste.printStackTrace();

        } catch (SocketException ste) {
            // TODO Auto-generated catch block
            isTimeout = true;
            ste.printStackTrace();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            try {
                if (_httpconn != null)
                    _httpconn.disconnect();
            } catch (Exception ex) {
            }

            try {
                if (_in != null)
                    _in.close();
            } catch (Exception ex) {
            }

            try {
                if (_out != null)
                    _out.close();
            } catch (Exception ex) {
            }

        }
    }
	public String getResponseErrorData() {

		if (erroMsg != null) {
			return erroMsg;
		}
		return null;
	}

	public String getResponseData() {

		if (responsData != null) {
			return responsData;
		}
		return null;
	}

	public boolean isTimeout() {

		return isTimeout;
	}

	public String getResponseTypeString() {

		if (responseTypeString != null) {
			return responseTypeString;
		}
		return null;
	}

	// /////////////////////////////////
	public String getResponseCodeString() {

		if (rc != -1) {
			return String.valueOf(rc);
		}
		return null;
	}

	// /////////////////////////////////////
	public String getStringResponseData() {

		if (responsData != null) {
			return responsData;
		}
		return null;
	}
	
	public void InetConnectionPOSTImage(String url,String fileName ,String _data) 
	{
		// TODO Auto-generated constructor stub
		this.url = url;			
		this.exsistingFileName =fileName;			
		this._data=_data;
		//this.task=task;
		requestString=_data;
		
	}

}
