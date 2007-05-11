package com.gerbildrop.engine.config;

import java.sql.ResultSet;

import com.gerbildrop.engine.xml.DxoDoc;

public interface Config {
    public String getConfigFile();

    public void setResources(DxoDoc doc);

    public void setResources(ResultSet rs);
}
