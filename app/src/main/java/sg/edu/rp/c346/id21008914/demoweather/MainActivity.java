package sg.edu.rp.c346.id21008914.demoweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Carpark> alCarpark = new ArrayList<Carpark>();
        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            String carpark_number;
            int lots_available;

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrcarpark_data = firstObj.getJSONArray("carpark_data");
                    for(int i = 0; i < jsonArrcarpark_data.length(); i++) {
                        JSONObject secondObj = jsonArrcarpark_data.getJSONObject(i);
                        carpark_number = secondObj.getString("carpark_number");
                        JSONArray jsonArrInfo = secondObj.getJSONArray("carpark_info");

                        for(int m = 0; m < jsonArrInfo.length(); m++) {
                            JSONObject thirdObj = jsonArrInfo.getJSONObject(m);
                            lots_available = thirdObj.getInt("lots_available");
                            Carpark carpark = new Carpark(carpark_number, lots_available);
                            alCarpark.add(carpark);
                        }
                    }
                } catch (JSONException e) {

                }

                //POINT X – Code to display List View
                ArrayAdapter<Carpark> aaCarpark = new ArrayAdapter<Carpark>(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark);
                lv.setAdapter(aaCarpark);

            }//end onSuccess
        });
    }//end onResume

}