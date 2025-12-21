package com.example.basicmath.models;

public class ModeInfo {
    private String title;
    private String description;
    private String longDescription;

    private int iconResId;

    private Class<?> targetActivity;
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
