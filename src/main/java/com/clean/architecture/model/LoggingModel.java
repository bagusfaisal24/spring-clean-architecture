package com.clean.architecture.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashMap;

@Data
@NoArgsConstructor
@Document("logging")
public class LoggingModel implements Serializable {
    private static final long serialVersionUID = -2735289910888223636L;

    @Id
    private String id;

    private HashMap<String, Object> data;

    private String type;

}
