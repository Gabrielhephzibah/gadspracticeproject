package com.cherish.gadspracticeproject.data.remote;


import com.cherish.gadspracticeproject.data.model.api.response.LearningLeadersData;
import com.cherish.gadspracticeproject.data.model.api.response.SkillIQData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIService {

    @GET("api/hours")
    Call<List<LearningLeadersData>> getLearningLeaders();

    @GET("api/skilliq")
    Call<List<SkillIQData>> getSkillIQLeaders();

    @POST()
    @FormUrlEncoded()
    Call<Void>submitProject(@Field("entry.1824927963")String emailAddress,
                            @Field("entry.1877115667")String name,
                            @Field("entry.2006916086")String lastName,
                            @Field("entry.284483984")String linkToProject, @Url String url);



}




