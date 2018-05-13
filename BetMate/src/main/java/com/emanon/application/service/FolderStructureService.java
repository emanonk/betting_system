/*
 * Copyright Camelot Global. All rights reserved
 */
package com.emanon.application.service;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author mmkamm
 */
@Service
public class FolderStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(FolderStructureService.class);

    @Value("${output.folder}")
    private String outputFolder;

    @Value("${output.folder.data}")
    private String dataFolder;

    @Value("${output.folder.exported_files}")
    private String exportedFolder;

    public void initialiseOutputFolders() {

        LOG.info("Creating application folders.");

        File main = new File(outputFolder);
        if (!main.exists()) {
            main.mkdir();
        }

        File data = new File(dataFolder);
        if (!data.exists()) {
            data.mkdir();
        }

        File temptickets = new File(exportedFolder);
        if (!temptickets.exists()) {
            temptickets.mkdir();
        }

    }
}
