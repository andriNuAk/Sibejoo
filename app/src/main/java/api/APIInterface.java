package api;

import helper.KomenHelper;
import model.BabLearningLineResponse;
import model.BabResponse;
import model.Comment;
import model.CommentResponse;
import model.EduDriveResponse;
import model.LearningLineLog;
import model.LearningLineLogResponse;
import model.LearningLineModulResponse;
import model.LearningLineResponse;
import model.LearningLineSoal;
import model.LearningLineSoalResponse;
import model.LearningLineVideoResponse;
import model.MapelLearningLineResponse;
import model.MataPelajaranResponse;
import model.MateriResponse;
import model.SiswaResponse;
import model.TopikLearningLineResponse;
import model.UpdateEMailProfil;
import model.UpdatePhotoProfil;
import model.UpdatePhotoProfilResponse;
import model.UpdateProfil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by M on 10/11/2017.
 */

public interface APIInterface {

    //ambil data siswa
    @GET("/getSiswa")
    Call<SiswaResponse> getSiswa(@Query("kataSandi") String kataSandi,
                                 @Query("namaPengguna") String namaPengguna,
                                 @Query("eMail") String eMail);
    //update profil
    @POST("/updateProfil")
    @FormUrlEncoded
    Call<UpdateProfil> updateProfil(@Field("namaDepan") String namaDepan, @Field("namaBelakang") String namaBelakang,
                                    @Field("alamat") String alamat, @Field("noKontak") String noKontak,
                                    @Field("biografi") String biografi, @Field("namaSekolah") String namaSekolah,
                                    @Field("alamatSekolah") String alamatSekolah, @Field("penggunaID") int penggunaID);


    //upload foto
    @Multipart
    @POST("/uploadFoto")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);

    //update profil
    @POST("/updatePhotoProfil")
    @FormUrlEncoded
    Call<UpdateProfil> updatePhotoProfil(@Field("penggunaID") int penggunaID);

    //update profil
    @POST("/updateEMailProfil")
    @FormUrlEncoded
    Call<UpdateEMailProfil> updateEMailProfil(@Field("id") int id, @Field("eMail") String eMail);

    //update profil
    @POST("/updatePasswordProfil")
    @FormUrlEncoded
    Call<UpdateEMailProfil> updatePassowrdProfil(@Field("id") int id, @Field("kataSandi") String kataSandi);


    //get new photo
    @GET("/getPhoto")
    Call<UpdatePhotoProfilResponse> getPhoto(@Query("penggunaID") int penggunaID);

    //get mapel
    @GET("/getMapel")
    Call<MataPelajaranResponse> getMapel(@Query("tingkatID") int tingkatID);

    //get materi
    @GET("/getMateri")
    Call<MateriResponse> getMateri(@Query("id") int id);

    //get materi
    @GET("/getMateriByJenisVideo")
    Call<MateriResponse> getMateriByJenisVideo(@Query("id") int id, @Query("jenis_video") int jenis_video);


    //get materi
    @GET("/getComments")
    Call<CommentResponse> getComments(@Query("id") int id);

    //insert komen
    @POST("/insertKomen")
    @FormUrlEncoded
    Call<KomenHelper> insertKomen(@Field("isiKomen") String isiKomen, @Field("date_created") String date_created,
                                  @Field("videoID") int videoID, @Field("userID") int userID, @Field("status") String status,
                                  @Field("read_status") int read_status);

    //insert komen
    @POST("/insertLog")
    @FormUrlEncoded
    Call<LearningLineLog> insertLog(@Field("penggunaID") int penggunaID, @Field("stepID") int stepID,
                                      @Field("statusStep") String statusStep5);

    //get materi
    @GET("/getBab")
    Call<BabResponse> getBab(@Query("id") int id);

    //get materi
    @GET("/getMateriBab")
    Call<MateriResponse> getMateriBab(@Query("id") int id, @Query("babID") int babID);

    //get materi
    @GET("/getEduDrive")
    Call<EduDriveResponse> getEduDrive();

    //get materi
    @GET("/getEduDriveFav")
    Call<EduDriveResponse> getEduDriveFav();

    //get materi
    @GET("/getEduDriveByJudul")
    Call<EduDriveResponse> getEduDriveByJudul(@Query("judul") String judul);

    //get materi
    @GET("/getEduDriveByTingkat")
    Call<EduDriveResponse> getEduDriveByTingkat(@Query("tingkatID") int tingkatID);

    //get materi
    @GET("/getMapelLearningLine")
    Call<MapelLearningLineResponse> getMapelLearningLine(@Query("tingkatID") int tingkatID);

    //get materi
    @GET("/getBabLearningLine")
    Call<BabLearningLineResponse> getBabLearningLine(@Query("tingkatID") int tingkatID, @Query("keterangan") String keterangan);

    //get topik
    @GET("/getTopikLearningLine")
    Call<TopikLearningLineResponse> getTopikLearningLine(@Query("babID") int babID);

    //get learning line
    @GET("/getLearningLine")
    Call<LearningLineResponse> getLearningLine(@Query("id") int id);

    //get log learning line
    @GET("/getLogLearningLine")
    Call<LearningLineLogResponse> getLearningLineLog(@Query("penggunaID") int penggunaID, @Query("stepID") int stepID);

    //get video learning line
    @GET("/getVideoLearningLine")
    Call<LearningLineVideoResponse> getLearningLineVid(@Query("UUID") String UUID);

    //get modul learning line
    @GET("/getModulLearningLine")
    Call<LearningLineModulResponse> getLearningLineMod(@Query("UUID") String UUID);

    //get soal learning line
    @GET("/getSoalLearningLine")
    Call<LearningLineSoalResponse> getLearningLineSoal(@Query("latihanID") String latihanID);
}
