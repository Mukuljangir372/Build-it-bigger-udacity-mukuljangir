package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class JokeAsyncTask extends AsyncTask<Context, Void,String> {

    public JokeAsyncTask() {
    }

    private static MyApi backend_api = null;
    private Context context;
    private TaskCompleteListener mTaskCompleteListener;

    @Override
    protected String doInBackground(Context... contexts) {

        if(backend_api==null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
            new AndroidJsonFactory(), null)
                    //acc to emulator
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            //build
            backend_api = builder.build();
        }
        //get context
        context = contexts[0];

        //get joke
        try {
            return backend_api.getJoke().execute().getData();
        } catch (IOException e) {
            return "";
        }
    }

    public interface TaskCompleteListener {
        void onTaskComplete(String result);
    }

    public JokeAsyncTask(TaskCompleteListener listener) {
        mTaskCompleteListener = listener;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            mTaskCompleteListener.onTaskComplete(result);
        }
    }
}
