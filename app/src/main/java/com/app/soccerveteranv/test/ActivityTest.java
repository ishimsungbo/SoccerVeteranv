package com.app.soccerveteranv.test;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.app.soccerveteranv.R;
import com.app.soccerveteranv.common.Common;
import com.app.soccerveteranv.common.Tmessage;
import com.app.soccerveteranv.network.MisstionListService;
import com.app.soccerveteranv.util.PrefUtil;
import com.app.soccerveteranv.widget.DialogBuilder;
import com.app.soccerveteranv.widget.VeteranToast;
import com.app.soccerveteranv.widget.WaitingDialog;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sungbo on 2016-04-05.
 * SharedPreferences Test
 */
public class ActivityTest extends Activity {

    private PrefUtil prefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        ButterKnife.bind(this);
        prefUtil = new PrefUtil(ActivityTest.this);
    }

    @OnClick(R.id.btn_test)
    void btnTest(){

        WaitingDialog.cancelWaitingDialog();

        new DialogBuilder(ActivityTest.this)
                .setTitle("Title")
                .setMessage("MEssage")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
/*        new DialogBuilder(ActivityTest.this)
                .setTitle("어찌?")
                .setMessage("안녕하세요")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        VeteranToast.makeToast(getApplicationContext(), "취소?", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();*/
    }

    //로그인 테스트용 서버에 post 메서드
    @OnClick(R.id.btn_gettoken)
    void getToken(){
        String firstName ="shim";
        String lastName = "sungbo";

        final Call<Tmessage> m = ActivityTest.RetrofitServiceImplFactory.service().sendName(firstName, lastName);
        m.enqueue(new Callback<Tmessage>() {
            @Override
            public void onResponse(Response<Tmessage> response, Retrofit retrofit) {
                Tmessage message = response.body();
                VeteranToast.makeToast(getApplicationContext(),message.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                VeteranToast.makeToast(getApplicationContext(),"문제 발생"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickLogout() {
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
            }
        });
    }

    static class RetrofitServiceImplFactory {
        private static Retrofit getretrofit() {

            return new Retrofit.Builder()
                    .baseUrl(Common.SERVER_ADRESS)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        public static MisstionListService service() {
            return getretrofit().create(MisstionListService.class);
        }
    }


}
