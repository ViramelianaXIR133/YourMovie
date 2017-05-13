package id.sch.smktelkom_mlg.privateassignment.xirpl133.yourmovie;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main2Fragment extends Fragment {
    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/upcoming?api_key=18e7d23a486ff80cb18ece940e1e7feb";
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewMovie;
    private RecyclerView.Adapter adapter;
    //private RecyclerView.Adapter adaptera;

    private List<Main2ListItem> listItems;


    public Main2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LinearLayoutManager layoutManagera = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewMovie = (RecyclerView) view.findViewById(R.id.recyclerViewMovie);
        recyclerViewMovie.setHasFixedSize(true);
        recyclerViewMovie.setLayoutManager(layoutManagera);

        listItems = new ArrayList<>();

        loadRecyclerViewData();
        return view;
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);

                            //JSONArray array = jsonObject.getJSONObject("data").getJSONArray("results");
                            JSONArray array = jsonObject.getJSONArray("results");
                            //JSONArray array2 = jsonObject.getJSONArray("multimedia");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);

                                Main2ListItem item = new Main2ListItem(
                                        o.getString("poster_path"),
                                        o.getString("title"),
                                        o.getString("release_date")
                                );
                                listItems.add(item);

                            }
                            adapter = new Main2Adapter(listItems, getActivity().getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();


                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
