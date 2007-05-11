package com.gerbildrop.config;

import java.sql.ResultSet;

import com.gerbildrop.xml.DxoDoc;

public interface Config {
    public String getConfigFile();

    public void setResources(DxoDoc doc);

    public void setResources(ResultSet rs);
}
