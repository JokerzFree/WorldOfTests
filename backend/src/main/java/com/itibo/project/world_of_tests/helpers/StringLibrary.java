package com.itibo.project.world_of_tests.helpers;

import com.itibo.project.world_of_tests.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

/**
 * Created by Andrew on 20.05.2017.
 */
public class StringLibrary {

    /**
     * Build file name depend on prefix, filename and date
     * @param user user for which file uploaded
     * @param additional prefix for filename
     * @param file file itself
     * @return string with builded name
     */
    public static String buildFileName(User user, String additional, MultipartFile file){
        String uid = user.getId().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String[] fileNameParts = file.getOriginalFilename().split("\\.");
        String format = fileNameParts[fileNameParts.length-1];
        return uid+"_"+additional+"_"+timestamp.getTime()+"."+format;
    }
}
