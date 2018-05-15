package shounak.shounakbosejiit.com.calldoc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Shounak on 3/10/2018.
 */

public class Cloud_Handle extends AsyncTask <String,Void,Void>{
    Context context;
    URL url;

    String link,type,doc_name,doc_type,doc_qual,encode,temp_data,read_data,msg;
    HttpURLConnection httpURLConnection;
    ProgressDialog pd;
    public Cloud_Handle(Context context) {
        this.context=context;
    }


    @Override
    protected Void doInBackground(String... params) {
        type=params[0];
        if(type.equals("add_doc")) {
            doc_name = params[1];
            doc_type = params[2];
            doc_qual = params[3];
            link="https://phpandroid.000webhostapp.com/CallDoc/add_doc.php";
            encode = URLEncoder.encode("doc_name") + "=" + URLEncoder.encode(doc_name) + "&" + URLEncoder.encode("doc_type") + "="
                    + URLEncoder.encode(doc_type) + "&" +
                    URLEncoder.encode("doc_qual") + "=" + URLEncoder.encode(doc_qual);
        }
        if(type.equals("find_doc")){
            doc_type=params[1];
            encode=URLEncoder.encode("doc_type")+"="+URLEncoder.encode(doc_type);
            link="https://phpandroid.000webhostapp.com/CallDoc/search_doc.php";
        }
        if(type.equals("find_time_slot")){
            doc_name=params[1];
            doc_type=params[2];
            encode=URLEncoder.encode("doc_type")+"="+URLEncoder.encode(doc_type)+"&"+URLEncoder.encode("doc_name")+
                    "="+URLEncoder.encode(doc_name);
            link="https://phpandroid.000webhostapp.com/CallDoc/get_time_slot.php";
        }
        if(type.equals("book")){
            String name=db_entry.name;
            String email=db_entry.email;
            String doc_name=db_entry.sel_doc;
            String doc_type=db_entry.doc_type;
            String time=db_entry.time_slot;
            encode=URLEncoder.encode("doc_name")+"="+URLEncoder.encode(doc_name)+"&"+
                    URLEncoder.encode("doc_type")+"="+URLEncoder.encode(doc_type)+"&"+
                    URLEncoder.encode("time_slot")+"="+URLEncoder.encode(time)+"&"+
                    URLEncoder.encode("name")+"="+URLEncoder.encode(name)+"&"+
                    URLEncoder.encode("email")+"="+URLEncoder.encode(email);
            link="https://phpandroid.000webhostapp.com/CallDoc/conf_book.php";
        }
        try {
                url=new URL(link);
        } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        try {
                httpURLConnection=(HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        try {
                httpURLConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        OutputStream out= null;
        try {
              out = httpURLConnection.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                bufferedWriter.write(encode);
                bufferedWriter.flush();
                bufferedWriter.close();
                out.close();
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            InputStream inputStream= null;
            try {
                inputStream = httpURLConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader= null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            read_data="";
            try {
                while((temp_data=bufferedReader.readLine())!=null){
                    read_data+=temp_data;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait");
        pd.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pd.dismiss();
        if(type.equals("add_doc")){
           if(read_data.contains("success")){
                Toast.makeText(context,"Doctor Sucessfully Entered",Toast.LENGTH_SHORT).show();
           }
           else if(read_data.contains("exists")){
               Toast.makeText(context,"Doctor with Same name and Qualification Already Exists",Toast.LENGTH_LONG).show();
           }
        }
        else if(type.equals("find_doc")){
          db_entry.avail_doc_name=read_data.split("#");
          context.startActivity(new Intent(context,doc_list.class));
            //Toast.makeText(context, db_entry.avail_doc_type[1],Toast.LENGTH_LONG).show();
        }
        else if(type.equals("find_time_slot")){
           db_entry.slot_avail=read_data.split("#");
            context.startActivity(new Intent(context,select_time.class));

            //Toast.makeText(context,db_entry.slot_avail[1],Toast.LENGTH_LONG).show();
        }
        else if(type.equals("book")){
           //Toast.makeText(context,"Booking Confirm",Toast.LENGTH_LONG).show();
            AlertDialog.Builder b=new AlertDialog.Builder(context);
            b.setTitle("Confirmation");
            b.setMessage("Your Booking has been confirmed by the Doctor. Check your confirmation mail");
            b.setPositiveButton("OK",null);
            AlertDialog d=b.create();
            d.show();
        }
        //Toast.makeText(context,read_data,Toast.LENGTH_LONG).show();
    }
}

