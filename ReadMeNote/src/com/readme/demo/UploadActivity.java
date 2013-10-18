package com.readme.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.oauth.OAuthConsumer;
import net.oauth.OAuthServiceProvider;

import com.example.readmenote.R;
import com.example.readmenote.R.layout;
import com.example.readmenote.R.menu;
import com.readme.client.ReadMeClient;
import com.readme.client.ReadMeConstants;
import com.readme.client.ReadMeException;
import com.readme.data.Resource;
import com.readme.data.User;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UploadActivity extends Activity {
	private static final String TAG = "UploadActivity";	
	 private static final OAuthServiceProvider SERVICE_PROVIDER =
		        new OAuthServiceProvider(ReadMeConstants.REQUEST_TOKEN_URL,
		        		ReadMeConstants.USER_AUTHORIZATION_URL,
		        		ReadMeConstants.ACCESS_TOKEN_URL);

		   /* // YNote sandbox environment
		    private  final OAuthServiceProvider SANDBOX_SERVICE_PROVIDER =
		        new OAuthServiceProvider(ReadMeConstants.SANDBOX_REQUEST_TOKEN_URL,
		                ReadMeConstants.SANDBOX_USER_AUTHORIZATION_URL,
		                ReadMeConstants.SANDBOX_ACCESS_TOKEN_URL);*/

	    private static final String CONSUMER_KEY = ReadMeConstants.CONSUMER_KEY;
	    private static final String CONSUMER_SECRET = ReadMeConstants.CONSUMER_SECRET;
	    private static final String accessToken = ReadMeConstants.accessToken;
	    private static final String tokenSecret = ReadMeConstants.tokenSecret;
//		    private static final String CONSUMER_KEY = "7bac56b6e961a834313726a060c69b24";
//		    private static final String CONSUMER_SECRET = "b17ce2bf8e52bd4468300cda365b2bb3";
		    // sandbox consumer
		    private static final OAuthConsumer CONSUMER = new OAuthConsumer(null,
		            CONSUMER_KEY, CONSUMER_SECRET, SERVICE_PROVIDER);

		    private static ReadMeClient client = new ReadMeClient(CONSUMER);

		    //public static User user;
		    TextView upload_textView;
			Button upload_button ;
			private static Resource resource1,resource2;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		upload_textView = (TextView)findViewById(R.id.upload_textView);
		upload_button = (Button)findViewById(R.id.upload_button);
		 client.setAccessToken(accessToken, tokenSecret);	
		 upload_button.setOnClickListener(new OnClickListener() {
		   
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new AsyncTask() {
						
						@Override
						protected Object doInBackground(Object... params) {
							try {
								//图片路径
								String path = Environment.getExternalStorageDirectory().toString()+ "/appkefu_f000.png";
								
								File file = new File("storage/sdcard0/ReadMe20130929_050019.amr");
								
								 resource1 = uploadResource(file);
							     System.out.println("Upload attachment resource");
							      byte[] file1 = downloadResource(resource1.getUrl());
							      Log.i(TAG, file1.toString());
							    /* resource2 = uploadResource(downloadImage());
							    
							        // modify the content of the note to include these two resource
							     String content = resource1.toResourceTag() + "<br>" + resource2.toResourceTag();
							     System.out.println(content);    */  
						          
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						@Override
						protected void onPostExecute(Object result) {
							
							upload_textView.setText(resource1.getUrl());
							super.onPostExecute(result);
							
						}
					}.execute("");
				}
			});
	}

	private static File downloadImage() throws IOException {
        URL url = new URL(resource1.getUrl());
        byte[] bytes = toBytes(url.openStream());
        File image = new File("storage/sdcard0/case2.png");
        FileOutputStream output = new FileOutputStream(image);
        output.write(bytes);
        output.close();
        return image;
    }
	
	private static Resource uploadResource(final File resource) throws Exception {
        try {
            return client.uploadResource(resource);
        } catch (ReadMeException e) {
            if (e.getErrorCode() == 307 || e.getErrorCode() == 1017) {
            	//Toast.makeText(MyNoteActivity.this, "-----我错了",Toast.LENGTH_SHORT).show();
            	return null;
            } else {
                throw e;
            }
        }
    }

    private static byte[] downloadResource(final String url) throws Exception {
        InputStream body = null;
        try {
            body = client.downloadResource(url);
        } catch (ReadMeException e) {
            if (e.getErrorCode() == 307 || e.getErrorCode() == 1017) {
            	//Toast.makeText(MyNoteActivity.this, "-----我错了",Toast.LENGTH_SHORT).show();
            } else {
                throw e;
            }
        }
        return toBytes(body);
    }

    private static byte[] toBytes(InputStream input) throws IOException {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream(10240);
            byte[] buffer = new byte[10240];
            int n = -1;
            while ((n = input.read(buffer)) != -1) {
                bytes.write(buffer, 0, n);
            }
            bytes.close();
            return bytes.toByteArray();
        } finally {
            input.close();
        }
    }

}
