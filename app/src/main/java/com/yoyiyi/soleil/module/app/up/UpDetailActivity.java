package com.yoyiyi.soleil.module.app.up;

import com.yoyiyi.soleil.R;
import com.yoyiyi.soleil.bean.user.UpDetail;
import com.yoyiyi.soleil.module.region.BaseRegionActivity;
import com.yoyiyi.soleil.mvp.contract.app.UpDetailContract;
import com.yoyiyi.soleil.mvp.presenter.app.UpDetailPresenter;

import java.util.Arrays;

import javax.annotation.Nullable;

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/6/16 14:50
 * 描述:up主详情街界面
 */

public class UpDetailActivity extends BaseRegionActivity<UpDetailPresenter, Nullable> implements UpDetailContract.View {
    private UpDetail mUpDetail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_detail;
    }


    @Override
    public void showUpDetail(UpDetail upDetail) {
        mUpDetail = upDetail;
        finishTask();
    }

    @Override
    protected void loadData() {
        mPresenter.getUpDetailData();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void finishTask() {
        mTitles.addAll(Arrays.asList(
                "主页",
                "投稿(" + mUpDetail.data.archive.count + ")",
                "收藏(" + mUpDetail.data.favourite + ")",
                "追番",
                "兴趣圈",
                "投币",
                "游戏"
        ));
        mFragments.add(ArchiveFragment.newInstance(1));
        mFragments.add(SubmitedVideoFragment.newInstance(mUpDetail.data.setting.submited_video));
        mFragments.add(FavouriteFragment.newInstance(mUpDetail.data.setting.fav_video));
        mFragments.add(BangumiFragment.newInstance(mUpDetail.data.setting.bangumi));
        mFragments.add(GroupFragment.newInstance(mUpDetail.data.setting.groups));
        mFragments.add(CoinsVideoFragment.newInstance(mUpDetail.data.setting.coins_video));
        mFragments.add(PlayGamesFragment.newInstance(mUpDetail.data.setting.played_game));

        mViewPager.setOffscreenPageLimit(mTitles.size());
        mViewPager.setAdapter(new BaseRegionTypeAdapte(getSupportFragmentManager(), mTitles, mFragments));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }
}