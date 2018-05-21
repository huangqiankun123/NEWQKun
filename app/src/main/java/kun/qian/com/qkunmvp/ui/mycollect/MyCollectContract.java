package kun.qian.com.qkunmvp.ui.mycollect;

import kun.qian.com.qkunmvp.base.BaseContract;
import kun.qian.com.qkunmvp.bean.CollectArticleBean;
import kun.qian.com.qkunmvp.constant.LoadType;

/**
 * Created by QKun on 2018/4/25.
 */

public interface MyCollectContract {

    interface View extends BaseContract.BaseView {
        //
        void getMyCollectArticlesSuccess(CollectArticleBean collectArticleBean, @LoadType.checker int loadType);

        void unCollectArticleSuccess(int position);
    }

    interface Presenter extends BaseContract.BasePresent<MyCollectContract.View> {
        //加载
        void loadMyCollectArticles();

        void refresh();

        void loadMore();

        //取消收藏 先不处理
//        void unCollectArticle(int position, CollectArticleBean.DatasBean bean);
    }
}
