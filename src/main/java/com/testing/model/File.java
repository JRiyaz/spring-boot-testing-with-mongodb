package com.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "files")
public class File {

    @Id
    private String id;

    @NotEmpty(message = "Title field cannot be empty")
    private String title;

    @NotEmpty(message = "Type field cannot be empty")
    private String type;

    @NotEmpty(message = "File cannot be empty")
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
}
