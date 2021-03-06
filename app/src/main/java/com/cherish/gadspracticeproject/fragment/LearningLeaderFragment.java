package com.cherish.gadspracticeproject.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cherish.gadspracticeproject.R;
import com.cherish.gadspracticeproject.adapter.LearningLeadersAdapter;
import com.cherish.gadspracticeproject.data.model.api.response.LearningLeadersData;
import com.cherish.gadspracticeproject.data.remote.APIService;
import com.cherish.gadspracticeproject.data.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeaderFragment extends Fragment {
    APIService mApiService;
    RecyclerView recyclerView;
    LearningLeadersAdapter recyclerViewAdapter;
    List<LearningLeadersData> data = new ArrayList<>();
    ProgressDialog progressDialog;

    public static LearningLeaderFragment newInstance() {
        return new LearningLeaderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = APIUtils.getAPIService();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        getLearningLeader();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.learning_leader_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    public void getLearningLeader() {
        mApiService.getLearningLeaders().enqueue(new Callback<List<LearningLeadersData>>() {
            @Override
            public void onResponse(Call<List<LearningLeadersData>> call, Response<List<LearningLeadersData>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Log.i("Response Success", String.valueOf(response));

                    for (int i = 0; i < response.body().size(); i++) {
                        LearningLeadersData objectData = response.body().get(i);
                        String name = objectData.getName();
                        String country = objectData.getCountry();
                        String hours = objectData.getHours();
                        String imageUrl = objectData.getBadgeUrl();
                        LearningLeadersData object = new LearningLeadersData(name, hours, country, imageUrl);
                        data.add(object);
                        recyclerViewAdapter = new LearningLeadersAdapter(getActivity(), data);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<LearningLeadersData>> call, Throwable t) {
                progressDialog.dismiss();
                Log.i("Failure", "Failure");
                Toast toast = new Toast(getActivity());
                toast.setDuration(Toast.LENGTH_LONG);
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.custom_toast_layout, null);
                toast.setView(view);
                toast.show();


            }
        });

    }
}

