package com.example.qsr.fav_deal.database;

/**************************************
 * FileName : com.example.qsr.fav_deal.database
 * Author : qsr
 * Time : 2016/7/27 10:17
 * Description :
 **************************************/
public interface DBDao {
    /**
     * 添加本地缓存
     * @param url 插入的数据的url
     * @param content 插入的数据的json串
     */
    public void insertData(String url,String content,String time);

    /**
     * 删除指定的本地存储数据
     * @param url 指定url
     */
    public void deleteData(String url);

    /**
     * 更新本地缓存数据
     * @param url 指定url
     * @param content 需要更新的内容
     * @param time 更新的时间
     */
    public void updateData(String url,String content,String time);

    /**
     * 查询指定url的缓存数据
     * @param url 指定的url
     * @return 返回缓存数据content
     */
    public String queryData(String url);
    /**
     * 查询指定url的缓存数据
     * @param url 指定的url
     * @return 返回缓存数据的存储时间
     */
    public String queryDataTime(String url);
}
