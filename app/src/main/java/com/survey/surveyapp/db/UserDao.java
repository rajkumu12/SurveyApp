package com.survey.surveyapp.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.survey.surveyapp.Models.Result_Push;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    Result_Push getLoginUser();


    @Query("SELECT * FROM user")
    List<Result_Push> getAllLoginUser();


    @Query("SELECT * FROM user where uid LIKE :id")
    List<Result_Push> getAll(String id);


   @Query("UPDATE user SET fullname = :fullname , mobile = :mobile , email = :email , profile_pic = :profile_pic , about_us = :about_us , gender = :gender , city = :city , phone = :phone , token = :token WHERE uid = :tid")
 int updateLoginUser(int tid, String fullname, String mobile, String email, String profile_pic, String about_us, String gender, String city, String phone, String token);
   // LoginDataModel updateLoginUser();



//    @Query("SELECT * FROM user where id LIKE  :search AND id LIKE :id")
//    User findByName(String search, String id);
//
//    @Query("SELECT * FROM user where uid LIKE  :uid AND ")
//    User findById(int uid);
//
//    @Query("SELECT * FROM user where search LIKE  :search")
//    User findByName(String search);
//
//    @Query("SELECT COUNT(*) from user")
//    int countUsers();

    @Insert
    void insertAll(Result_Push... users);

    @Delete
    void delete(Result_Push user);

//    @Query("DELETE FROM user where uid LIKE  :uid")
//    void deleteById(int uid);



}