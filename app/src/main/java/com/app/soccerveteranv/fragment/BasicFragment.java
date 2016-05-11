package com.app.soccerveteranv.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.soccerveteranv.ContentActivity;
import com.app.soccerveteranv.R;
import com.app.soccerveteranv.adapter.MittionAdapter;
import com.app.soccerveteranv.common.Common;
import com.app.soccerveteranv.network.MisstionListService;
import com.app.soccerveteranv.vo.MisstionVo;
import com.app.soccerveteranv.widget.VeteranToast;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sungbo on 2016-03-29.
 * 기초미션 스테이지 프래그먼트
 */
public class BasicFragment extends Fragment {


    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    //영상 촬영시, 영상 업로드시
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;

    MittionAdapter adapter;
    ListView    lv_mlist;

    public static BasicFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BasicFragment fragment = new BasicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ProgressDialog dialog;

        View rootview = null;

        if(mPage == 1){

            dialog = ProgressDialog.show(getActivity(), "Video Loading...", "리프팅 기초미션 영상을 불러오는 중입니다...", true);

            rootview =  inflater.inflate(R.layout.middle_layout_lifting_basic, container, false);
            lv_mlist = (ListView) rootview.findViewById(R.id.nomal_lifting_listview);

            final Call<ArrayList<MisstionVo>> mList = RetrofitServiceImplFactory.misstionListService().getMlist();

            mList.enqueue(new Callback<ArrayList<MisstionVo>>() {
                @Override
                public void onResponse(Response<ArrayList<MisstionVo>> response, Retrofit retrofit) {

                    try{
                        final ArrayList<MisstionVo> misstionVoList = (ArrayList<MisstionVo>) response.body();

                        adapter = new MittionAdapter(getActivity(), R.layout.mission_youtube_view_item, misstionVoList);
                        lv_mlist.setAdapter(adapter);

                        //프로그래스 죽이기
                        dialog.dismiss();

                        //리스트를 클릭시 이벤트
                        lv_mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getContext(), ContentActivity.class);
                                intent.putExtra("VIDEOID",misstionVoList.get(position).getVideoid());
                                startActivity(intent);
                            }
                        });
                    }catch (Exception e){
                        VeteranToast.makeToast(getContext(), "데이터를 불러오는 도중 에러가 발생했습니다", Toast.LENGTH_SHORT).show();
                        e.getStackTrace();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    VeteranToast.makeToast(getContext(), "서버에서의 응답이 없습니다", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

        }else if(mPage == 2){
            rootview =  inflater.inflate(R.layout.middle_layout_dribble_basic, container, false);

            // if using android for spring
            // new VideoList().execute();
        }else if(mPage == 3){
            rootview =  inflater.inflate(R.layout.middle_layout_trapping_basic, container, false);
        }

        return rootview;
    }

    public static class RetrofitServiceImplFactory {
        private static Retrofit getretrofit(){
            return new Retrofit.Builder()
                    .baseUrl(Common.SERVER_ADRESS)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        public static MisstionListService misstionListService(){
            return getretrofit().create(MisstionListService.class);
        }

    }
}