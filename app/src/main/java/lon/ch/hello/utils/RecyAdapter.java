package lon.ch.hello.utils;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bean.HomeBean;

/**
 * 作者：${chenlong} on 2017/12/15 10:26
 */
public class RecyAdapter extends BaseQuickAdapter<HomeBean.DatasEntity.BestEntity, BaseViewHolder>{

    public RecyAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DatasEntity.BestEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DatasEntity.BestEntity item) {
        helper.setText(R.id.tv_main_title, item.getMain_title())
                .setText(R.id.tv_sub_title, item.getSub_title());
        if (item.getPro_pic() != null && item.getPro_pic().size() > 0) {
            Glide.with(mContext).load(item.getSingle_pic())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }





}
