package com.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "files")
public class File {

    @Id
    private String id;

    private String title;

    private Binary file;

    public File(String title, Binary file) {
        this.title = title;
        this.file = file;
    }
}
