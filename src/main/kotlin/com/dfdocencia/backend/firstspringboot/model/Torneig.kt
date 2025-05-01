package com.dfdocencia.backend.firstspringboot.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("Tornejos")
data class Torneig (
    @Id var id: ObjectId =ObjectId.get(),
    var nom: String? = null,
    var data: Instant? = null,
    var lloc: String? = null,
    var numjugadors: Int? = null
)