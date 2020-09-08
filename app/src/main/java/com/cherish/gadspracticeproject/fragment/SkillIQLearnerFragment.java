package com.cherish.gadspracticeproject.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cherish.gadspracticeproject.R;
import com.cherish.gadspracticeproject.adapter.LearningLeadersAdapter;
import com.cherish.gadspracticeproject.adapter.SkillIQLeadersAdapter;
import com.cherish.gadspracticeproject.data.model.api.response.SkillIQData;
import com.cherish.gadspracticeproject.data.remote.APIService;
import com.cherish.gadspracticeproject.data.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLearnerFragment extends Fragment {
    APIService mApiService;
    RecyclerView recyclerView;
    SkillIQLeadersAdapter recyclerViewAdapter;
    List<SkillIQData> data = new ArrayList<>();
    ProgressDialog progressDialog;

    public static SkillIQLearnerFragment newInstance() {
        return new SkillIQLearnerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = APIUtils.getAPIService();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        getSkillIQLeader();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.skill_iq_learner_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    public void getSkillIQLeader() {
        mApiService.getSkillIQLeaders().enqueue(new Callback<List<SkillIQData>>() {
            @Override
            public void onResponse(Call<List<SkillIQData>> call, Response<List<SkillIQData>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    for (int i = 0; i < response.body().size(); i++) {
                        SkillIQData objectData = response.body().get(i);
                        String name = objectData.getName();
                        String country = objectData.getCountry();
                        String score = objectData.getScore();
                        String imageUrl = objectData.getBadgeUrl();
                        SkillIQData object = new SkillIQData(name, score, country, imageUrl);
                        data.add(object);
                        recyclerViewAdapter = new SkillIQLeadersAdapter(getActivity(), data);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<SkillIQData>> call, Throwable t) {
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
