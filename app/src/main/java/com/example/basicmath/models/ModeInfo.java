package com.example.basicmath.models;

import android.os.Bundle;

import java.io.Serializable;

public class ModeInfo implements Serializable {
    private String title;
    private String description;
    private String longDescription;

    private int iconResId;

    private Class<?> targetActivity;
    private Bundle info;
    private Boolean requiresInfo = false;

    @Override
    public String toString() {
        return "ModeInfo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", iconResId=" + iconResId +
                ", targetActivity=" + targetActivity +
                ", info=" + info +
                ", requiresInfo=" + requiresInfo +
                '}';
    }

    public ModeInfo(String title, String description, String longDescription, int iconResId, Class<?> targetActivity, Boolean requiresInfo) {
        this.title = title;
        this.description = description;
        this.longDescription = longDescription;
        this.iconResId = iconResId;
        this.targetActivity = targetActivity;
        this.requiresInfo = requiresInfo;
    }

    public Boolean getRequiresInfo() {
        return requiresInfo;
    }

    public void setRequiresInfo(Boolean requiresInfo) {
        this.requiresInfo = requiresInfo;
    }

    public Bundle getInfo() {
        return info;
    }

    public void setInfo(Bundle info) {
        this.info = info;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public ModeInfo(String title, String description, String longDescription, int iconResId, Class<?> target) {
        this.title = title;
        this.description = description;
        this.longDescription = longDescription;
        this.iconResId = iconResId;
        this.targetActivity = target;
    }

    public String getTitle() {
        return title;
    }

    public Class<?> getTargetActivity() {
        return targetActivity;
    }

    public void setTargetActivity(Class<?> targetActivity) {
        this.targetActivity = targetActivity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
