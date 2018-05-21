package kun.qian.com.qkunmvp.ui.mycollect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import kun.qian.com.qkunmvp.R;
import kun.qian.com.qkunmvp.base.BaseActivity;
import kun.qian.com.qkunmvp.bean.CollectArticleBean;

/**
 * Created by QKun on 2018/4/25.
 * 我的收藏列表界面
 */

public class MyCollectActivity extends BaseActivity<MyCollectPresenter> implements MyCollectContract.View, OnRefreshLoadmoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private MyCollectAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.my_activity_collect;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

        mRefreshLayout.setRefreshHeader(new PhoenixHeader(this));
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyCollectAdapter(R.layout.item_article, new ArrayList<CollectArticleBean.DatasBean>());
        mRecyclerView.setAdapter(mAdapter);
        View empty_view = LayoutInflater.from(MyCollectActivity.this).inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.setEmptyView(empty_view);

        mPresenter.loadMyCollectArticles();

        mRefreshLayout.setOnRefreshLoadmoreListener(this);

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void getMyCollectArticlesSuccess(CollectArticleBean collectArticleBean, int loadType) {
        setLoadDataResult(mAdapter, mRefreshLayout, collectArticleBean.getDatas(), loadType);
    }

    @Override
    public void unCollectArticleSuccess(int position) {

    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.refresh();
    }


}
