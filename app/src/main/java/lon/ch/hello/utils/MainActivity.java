package lon.ch.hello.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import api.MyContants;
import bean.HomeBean;
import network.BaseObserver1;
import network.RetrofitManager;
import view.CustomProgressDialog;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomProgressDialog mDialog;
    private RecyAdapter adapter;
    private List<String> picList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadView();

    }

    private void loadView() {
        RetrofitManager.get(MyContants.BASEURL + "s=Home/home", new BaseObserver1<HomeBean>("") {

            @Override
            public void onSuccess(HomeBean result, String tag) {
                if (result.getCode() == 200) {
                    picList.clear();
                    List<HomeBean.DatasEntity.BestEntity> bestEntityList = new ArrayList<HomeBean.DatasEntity.BestEntity>();
                    for (int i = 1; i < result.getDatas().getBest().size(); i++) {
                        bestEntityList.add(result.getDatas().getBest().get(i));
                    }
                    recyclerView = (RecyclerView) findViewById(R.id.recyview);
                    adapter = new RecyAdapter(R.layout.home_changxiao_item, bestEntityList);
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailed(int code){
                    Toast.makeText(MainActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();

            }
        });
    }


}
