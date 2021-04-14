package com.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "files")
public class File {

    @Id
    private String id;

    private String title;

    private String type;

    private Binary file;

    public File(String title, Binary file, String type) {
        this.title = title;
        this.file = file;
        this.type = type;
    }

    public String binaryToImage(Binary file) {
        return Base64.getEncoder()
                .encodeToString(file.getData());
    }

//    public String getFile() {
//        return Base64.getEncoder()
//                .encodeToString(file.getData());
//    }
}
