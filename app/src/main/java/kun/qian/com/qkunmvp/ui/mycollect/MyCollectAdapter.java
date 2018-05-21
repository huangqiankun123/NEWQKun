package kun.qian.com.qkunmvp.ui.mycollect;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kun.qian.com.qkunmvp.R;
import kun.qian.com.qkunmvp.bean.CollectArticleBean;
import kun.qian.com.qkunmvp.bean.HomeBean;

/**
 * Created by QKun on 2018/3/23.
 */

public class MyCollectAdapter extends BaseQuickAdapter<CollectArticleBean.DatasBean, BaseViewHolder> {
    public MyCollectAdapter(int layoutResId, @Nullable List<CollectArticleBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectArticleBean.DatasBean item) {
        helper.setText(R.id.item_search_pager_title,item.getTitle());
        helper.setText(R.id.item_search_pager_author, "作者："+item.getAuthor());
        helper.setText(R.id.item_search_pager_chapterName, "分类："+item.getChapterName());
        helper.setText(R.id.item_search_pager_niceDate, "时间："+item.getNiceDate());
    }
}
