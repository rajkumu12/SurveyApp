package com.survey.surveyapp.Netwrks;


import com.survey.surveyapp.Models.BaseModel;
import com.survey.surveyapp.Models.LoginModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface APIInterface {


    @Multipart
    @POST(WSContants.REGISTER_LOGIN)
    Call<LoginModel> register_login2(
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );


    @Multipart
    @POST(WSContants.REGISTER_LOGIN)
    Call<LoginModel> register_login(
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );

    @Multipart
    @POST(WSContants.UPDATE_USER)
    Call<LoginModel> updateUser(
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );

    @Multipart
    @POST(WSContants.ADDID)
    Call<BaseModel> CreateId(
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );


  /*  @GET(WSContants.CATEGORIES)
    Call<LiveClassesCategoryModel> getLiveClassesCategory();





    @Multipart
    @POST(WSContants.CONTENTS)
    Call<LiveClassesVideoModel> getLiveClassesVideo(
            //@Part("category_id") RequestBody category_id
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );




    @GET(WSContants.CHANNELS)
    Call<ResponseBody> getChannelDetail(@Query("key") String key,
                                                    @Query("id") String channelId,
                                                    @Query("part") String part,
                                                    @Query("order") String order,
                                                    @Query("maxResults") String maxResults);

    @GET(WSContants.SEARCH)
    Call<ResponseBody> getLiveChannelDetail(@Query("key") String key,
                                        @Query("channelId") String channelId,
                                        @Query("part") String part,
                                        @Query("eventType") String eventType,
                                        @Query("type") String type);

    @GET(WSContants.SEARCH_COURSES)
    Call<SearchModel> getSearchList(@Query(WSContants.SEARCH_COURSES_SEARCH) String key);


    @Multipart
    @POST(WSContants.COURSES)
    Call<HomeFreeCourseModel> getFreeCourseList(
            //@Part("category_id") RequestBody category_id
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );


    @Multipart
    @POST(WSContants.COURSES)
    Call<HomePaidCourseModel> getPaidCourseList(
            //@Part("category_id") RequestBody category_id
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );


    @Multipart
    @POST(WSContants.COURSES)
    Call<HomeFreeCourseModel> getCourseList(
            //@Part("category_id") RequestBody category_id
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );


    @Multipart
    @POST(WSContants.COURSES_CONTENTS)
    Call<CaptureModel> getCaptureList(
            //@Part("category_id") RequestBody category_id
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );


    @Multipart
    @POST(WSContants.COURSES_CONTENTS_INFO)
    Call<CaptureModel> getCaptureListInfo(
            //@Part("category_id") RequestBody category_id
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );


    @Multipart
    @POST(WSContants.SEARCH_COURSES)
    Call<ResponseBody> getSearchCourse(
            //@Part("category_id") RequestBody category_id
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );



    @Multipart
    @POST(WSContants.PROFILE)
    Call<LoginModel> updateUser(
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );




    @Multipart
    @POST(WSContants.QUIZ)
    Call<AssessmentModel> getQuizList(
            //@Part("category_id") RequestBody category_id
            @Part ArrayList<MultipartBody.Part> arrayListMash
    );






    @GET()
    @Streaming
    Call<ResponseBody> downloadImage(@Url String fileUrl);





//    @GET("login.php?")
//    Call<ResponseBody>
//    login(
//            @Query("emailid") String username,
//            @Query("password") String password
//    );

    @GET("login.php?")
    Call<LoginModel> login(@Query("email") String username,
                                         @Query("password") String password,
                           @Query("ip_address") String ip_address
    );


    //
    @FormUrlEncoded
    @POST("update_result.php")
    Call<ResultModel> postresult(@Field("course_id") String course_id,
                                 @Field("content_id") String content_id,
                                 @Field("userid") String userid, @Field("result") String result);
    @FormUrlEncoded
    @POST("get_result.php")
    Call<ResultModel> getResult(@Field("course_id")String courseID,
                                @Field("content_id") String contentID,
                                @Field("userid")  String userid);


    @FormUrlEncoded
    @POST("contactus.php")
    Call<FeedBackModel> postfeedback(@Field("user_id")String user_id,
                                     @Field("mode") String mode,
                                     @Field("message")  String message);

   // http://smartiecards.com/android/login.php?email=&password=&ip_address=4535


    @Multipart
    @POST("login_register.php?")
    Call<ResponseBody> register(
            @Part("mobile") RequestBody mobile,
            @Part("email_id") RequestBody email_id,
            @Part("full_name") RequestBody full_name
    );

    @Multipart
    @POST("viewlist.php?")
    Call<ResponseBody> viewlist(@Part("tab") RequestBody tab);

    @Multipart
    @POST("pastperformance.php?")
    Call<ResponseBody> pastperformance(@Part("tab") RequestBody tab);





    @GET("update-device-token.php?")
    Call<ResponseBody>
    generateDeviceToken(
            @Query("userid") String userid,
            @Query("devicetoken") String devicetoken,
            @Query("device_type") String device_type
    );


    @GET("signup.php?")
    Call<ResponseBody>
    register(
            @Query("user_full_name") String user_full_name,
            @Query("user_mobile") String user_mobile,
            @Query("user_email") String user_email,
            @Query("user_password") String user_password
//            @Query("card_number") String card_number,
//            @Query("card_type") String card_type,
//            @Query("card_exp_date") String card_exp_date,
//            @Query("cvv") String cvv
    );

    @GET("social-signup.php?")
    Call<ResponseBody>
    socialRegister(
            @Query("full_name") String full_name,
            @Query("email") String email,
            @Query("purl") String card_exp_date
    );


    @GET("user-detail.php?")
    Call<ResponseBody>
    userProfile(
            @Query("userid") String full_name
    );





    @GET("find-drivers.php?")
    Call<ResponseBody>
    findDrivers(
            @Query("user_id") String user_id,
            @Query("pickup_location") String pickup_location,
            @Query("trip_detail") String trip_detail,
            @Query("trip_date") String trip_date,
            @Query("trip_time") String trip_time,
            @Query("pickup_lat") String pickup_lat,
            @Query("pickup_long") String pickup_long,
            @Query("drop_lat") String drop_lat,
            @Query("drop_long") String drop_long
    );


    @GET("send-request.php?")
    Call<ResponseBody>
    sendRequest(
            @Query("user_id") String user_id,
            @Query("driver_id") String pickup_location,
            @Query("trip_id") String trip_id,
            @Query("card_id") String card_id,
            @Query("total_cost") String total_cost,
            @Query("booking_cost") String booking_cost,
            @Query("pending_cost") String pending_cost
    );



    @GET("user-send_request-list.php?")
    Call<ResponseBody>
    userSendRequesList(
            @Query("userid") String username
    );


    @GET("user-booking-list.php?")
    Call<ResponseBody>
    bookingList(
            @Query("userid") String username
    );


    @GET("cancel-ride.php?")
    Call<ResponseBody>
    cancelRide(
            @Query("userid") String userid,
            @Query("ride_id") String ride_id
    );



    @Multipart
    @POST("uploadfile.php?")
    Call<ResponseBody> uploadFile(
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("mobile") RequestBody mobile,
            @Part MultipartBody.Part file
    );



    @Multipart
    @POST("login")
    Call<ResponseBody> uploadFile1(
            @Part("csrf_quaiq_insurance") RequestBody user_id,
            @Part("user_login") RequestBody name,
            @Part("user_password") RequestBody mobile
    );


    @Multipart
    @POST("chk_apiresponse")
    Call<ResponseBody> uploadFile1(@Part("csrf_quaiq_insurance") RequestBody csrf_quaiq_insurance, @Part ArrayList<MultipartBody.Part> hashMap);


    @Multipart
    @POST("chk_apiresponse")
    Call<ResponseBody> uploadFile1(@Part ArrayList<MultipartBody.Part> hashMap);


    @Multipart
    @POST("update-profile.php?")
    Call<ResponseBody>
    updateProfile(
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("mobile") RequestBody mobile
            ,
            @Part MultipartBody.Part bodyrofileURL
    );


    @GET("forgot-password.php?")
    Call<ResponseBody> forgotPassword(
            @Query("email_address") String userid
    );




    @GET("ride-detail.php?")
    Call<ResponseBody> rideDetail(
            @Query("userid") String driverid
    );




    @GET("driver-rating.php?")
    Call<ResponseBody>
    feedback(
            @Query("userid") String userid,
            @Query("driverid") String driverid,
            @Query("trip_id") String rideId,
            @Query("msg") String message,
            @Query("rating") String value
    );


    @GET("customer-feedback.php?")
    Call<ResponseBody> customerFeedback(
            @Query("driverid") String driverid
    );




    @GET("contact-us.php?")
    Call<ResponseBody>
    contactUs(
            @Query("fullname") String name,
            @Query("email") String emailid,
            @Query("message") String message
    );



    @GET("driver-info.php?")
    Call<ResponseBody>
    driverInfo(
            @Query("driverid") String full_name
    );




    @GET("paypal-add-card/paypal/rest-api-sdk-php/sample/vault/addCreditCard.php?")
    Call<ResponseBody>
    addCard(
            @Query("userid") String driver_id,
            @Query("username") String username,
            @Query("useremail") String useremail,
            @Query("cardName") String cardName,
            @Query("cardNumber") String cardNumber,
            @Query("cardType") String cardType,
            @Query("ExpireMonth") String ExpireMonth,
            @Query("ExpireYear") String ExpireYear,
            @Query("CVV") String CVV
    );




    @GET("user-payment-history.php?")
    Call<ResponseBody>
    userPaymentHistory(
            @Query("userid") String userid);


    @GET("user-card-list.php?")
    Call<ResponseBody>
    userCardList(
            @Query("userid") String userid);




    @GET("user-remove-card.php?")
    Call<ResponseBody>
    removeCard(
            @Query("userid") String userid,
            @Query("card_id") String card_id
    );




    @GET("chat.php?")
    Call<ResponseBody>
    userChat(
            @Query("senderid") String senderid,
            @Query("recieverid") String recieverid,
            @Query("trip_id") String trip_id,
            @Query("msg") String msg,
            @Query("keyvalue") String keyvalue);



   // @Multipart
   // @FormUrlEncoded
    @POST("request-fire-department.php")
    Call<ResponseBody> uploadMultiFile(@Body RequestBody file);
*/



    // Call<LiveClassesCategoryModel> getLiveClassesCategory(String youtubeApiKey, String channelId, String s, String date, String s1);
}
