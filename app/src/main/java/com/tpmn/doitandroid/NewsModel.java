package com.tpmn.doitandroid;

import java.util.List;

public class NewsModel {
    private String status;
    private float totalResults;
    private List<NewsItemModel> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(float totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsItemModel> getItems() {
        return articles;
    }

    public void setItems(List<NewsItemModel> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NewsModel{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                '}');
        for (int i = 0; i < articles.size(); ++i) {
            builder.append(articles.get(i).toString());
        }

        return builder.toString();
    }
}