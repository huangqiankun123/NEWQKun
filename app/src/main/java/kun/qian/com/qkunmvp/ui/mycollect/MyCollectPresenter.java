package kun.qian.com.qkunmvp.ui.mycollect;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import kun.qian.com.qkunmvp.base.BasePresenter;
import kun.qian.com.qkunmvp.bean.CollectArticleBean;
import kun.qian.com.qkunmvp.constant.LoadType;
import kun.qian.com.qkunmvp.net.ApiService;
import kun.qian.com.qkunmvp.net.BaseResponse;
import kun.qian.com.qkunmvp.net.RetrofitManager;
import kun.qian.com.qkunmvp.net.RxSchedulers;

/**
 * Created by QKun on 2018/4/25.
 */

public class MyCollectPresenter extends BasePresenter<MyCollectContract.View> implements MyCollectContract.Presenter {
    private int mPage = 0;
    private boolean mIsRefresh;

    @Inject
    public MyCollectPresenter() {
        this.mIsRefresh = true;
    }

    @Override
    public void loadMyCollectArticles() {

        RetrofitManager.createApi(ApiService.class)
                .getCollectList(mPage)
                .compose(RxSchedulers.<BaseResponse<CollectArticleBean>>applySchedulers())
                .compose(mView.<BaseResponse<CollectArticleBean>>bindToLife())
                .subscribe(new Consumer<BaseResponse<CollectArticleBean>>() {
                    @Override
                    public void accept(BaseResponse<CollectArticleBean> collectArticleBeanBaseResponse) throws Exception {
                        if (collectArticleBeanBaseResponse.isSuccess()) {
                            int loadType = mIsRefresh ? LoadType.TYPE_LOAD_MORE_SUCCESS : LoadType.TYPE_REFRESH_SUCCESS;
                            mView.getMyCollectArticlesSuccess(collectArticleBeanBaseResponse.getData(), loadType);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.getMyCollectArticlesSuccess(new CollectArticleBean(), loadType);
                    }
                });

    }

    @Override
    public void refresh() {
        mPage = 0;
        mIsRefresh = true;
        loadMyCollectArticles();
    }

    @Override
    public void loadMore() {
        mPage++;
        mIsRefresh = false;
        loadMyCollectArticles();
    }

//    @Override
//    public void unCollectArticle(int position, CollectArticleBean.DatasBean bean) {
//        RetrofitManager.createApi(ApiService.class)
//                .getunCollectList(bean.getId(),-1)
//                .compose(RxSchedulers.<BaseResponse<CollectArticleBean>>applySchedulers())
//                .compose(mView.<BaseResponse<CollectArticleBean>>bindToLife())
//                .subscribe(new Consumer<BaseResponse<CollectArticleBean>>() {
//                    @Override
//                    public void accept(BaseResponse<CollectArticleBean> collectArticleBeanBaseResponse) throws Exception {
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });
//    }
}
